package rs.raf.demo.resources;

import com.auth0.jwt.JWT;
import rs.raf.demo.entities.*;
import rs.raf.demo.requests.FormRequest;
import rs.raf.demo.requests.IdRequest;
import rs.raf.demo.requests.InsertVestRequest;
import rs.raf.demo.requests.UpdateVestRequest;
import rs.raf.demo.services.KategorijeService;
import rs.raf.demo.services.KorisniciService;
import rs.raf.demo.services.VestiService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.time.Year;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/vesti")
public class VestiResource {

    @Inject
    private VestiService vestiService;
    @Inject
    private KategorijeService kategorijeService;
    @Inject
    private KorisniciService korisniciService;

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getVesti(@QueryParam("_page") String page, @QueryParam("_limit") String limit)
    {
        Map<String,Object> response = new HashMap<>();
        Map<String,Object> vestiMap = vestiService.getVestiPaginated(page,limit);

        Object[] objectArray = vestiMap.values().toArray();

        response.put("vesti", objectArray[0]);
        response.put("vestiSize", objectArray[1]);

        return Response.ok(response).build();
    }

    @GET
    @Path("/allHP")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getVestiHomePage(@QueryParam("_page") String page, @QueryParam("_limit") String limit)
    {
        Map<String,Object> response = new HashMap<>();
        Map<String,Object> vestiMap = vestiService.getVestiPaginatedHomePage(page,limit);
        Object[] objectArray = vestiMap.values().toArray();
        response.put("vesti", objectArray[0]);

        return Response.ok(response).build();
    }

    @GET
    @Path("/{vestId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getVestById(@PathParam("vestId") Integer vestId)
    {
        Map<String,Object> response = new HashMap<>();
        Vest vest = vestiService.getVestById(vestId);
        if(vest!=null)
        {
            response.put("naslov",vest.getNaslov());
            response.put("tekst",vest.getTekst());
            response.put("vreme_kreiranja",vest.getVreme_kreiranja());
            response.put("autor",vest.getAutor());
        }
        else
        {
            response.put("msg","Trazena vest ne postoji!");
        }

        return Response.ok(response).build();
    }

    @GET
    @Path("/tags/{vestId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getVestTags(@PathParam("vestId") Integer vestId)
    {
        Map<String,Object> response = new HashMap<>();
        String tag = vestiService.getTag(vestId);
        List<Komentar> komentari = this.vestiService.getCommentsById(vestId);
        if(tag!=null)
        {
            response.put("tag",tag);
        }
        else
        {
            response.put("msg","Ova vest nema tag!");
        }

        if(komentari!=null)
        {
            response.put("komentari",komentari);
        }
        else
        {
            response.put("msg2","Ova vest nema komentare");
        }

        return Response.ok(response).build();
    }

    @GET
    @Path("/kategorija/{imeKategorije}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getByKategorija(@QueryParam("_page") String page, @QueryParam("_limit") String limit,@PathParam("imeKategorije") String kategorija)
    {
        Map<String,Object> response = new HashMap<>();
        Kategorija k = kategorijeService.getKategorijaByName(kategorija);
        Map<String,Object> vestiMap = vestiService.getVestiPaginatedByKategorija(page,limit,k.getId());
        Object[] objectArray = vestiMap.values().toArray();
        response.put("vesti", objectArray[0]);
        response.put("vestiSize", objectArray[1]);
        return Response.ok(response).build();
    }

    @GET
    @Path("/search/{query}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response search(@QueryParam("_page") String page, @QueryParam("_limit") String limit,@PathParam("query") String query)
    {
        Map<String,Object> response = new HashMap<>();
        Map<String,Object> vestiMap = vestiService.getVestiPaginatedByQuery(page,limit,query);
        Object[] objectArray = vestiMap.values().toArray();
        response.put("vesti", objectArray[0]);
        response.put("vestiSize", objectArray[1]);
        return Response.ok(response).build();
    }


    @POST
    @Path("/insertComment")
    @Produces({MediaType.APPLICATION_JSON})
    public Response insertVest(@Valid FormRequest form) {
        Map<String, String> response = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(Year.now().getValue() - 1900, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        this.vestiService.insertComment(form.getId(),form.getIme(),form.getTekst(),date);
        response.put("msg","Uspesno dodat komentar");
        return Response.ok(response).build();
    }

    @POST
    @Path("/insert")
    @Produces({MediaType.APPLICATION_JSON})
    public Response insertVest(@Valid InsertVestRequest insertRequest,@Context HttpServletRequest request) {
        Map<String, String> response = new HashMap<>();

        Tag tag = this.vestiService.insertTag(insertRequest.getTag());

        String token = request.getHeader("authorization");
        String[] split = token.split(" ");
        Integer id = JWT.decode(split[1]).getClaims().get("id").asInt();

        Calendar calendar = Calendar.getInstance();
        Date date = new Date(Year.now().getValue() - 1900, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        Kategorija found = this.kategorijeService.findKategorijaByName(insertRequest.getKategorija());
        Korisnik autor = this.korisniciService.getKorisnikById(id);

        // && autor !=null , poziv vise ka bazi, ne mora
        if (found != null && autor != null) {
            Vest vest = this.vestiService.insertVest(insertRequest.getNaslov(), insertRequest.getTekst(), date, 0, insertRequest.getAutor(),insertRequest.getKategorija(),found.getId(), autor.getId());
            response.put("msg2", "Uspesno uneta Vest!");
            this.vestiService.insert_Vest_Tag(vest.getId(),tag.getId());
            return Response.ok(response).build();
        } else
        {
            response.put("msg", "Kategorija ne postoji! Autor ne postoji?");
            return Response.status(422, "Greska!").entity(response).build();
        }
    }


    @PUT
    @Path("/getVest")
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(@Valid IdRequest idRequest) {
        Map<String, Object> response = new HashMap<>();
        Vest v = this.vestiService.getVestById(idRequest.getId());
        if (v!=null)
        {
            response.put("vest", v );
            return Response.ok(response).build();
        }
        else
        {
            response.put("msg2", "Vest sa datim id ne postoji!");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }
    }

    @PUT
    @Path("/update")
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateVest(@Valid UpdateVestRequest updateRequest)
    {
        System.out.println(updateRequest);
        System.out.println(updateRequest.getId());

        Map<String, String> response = new HashMap<>();
        Vest v  = this.vestiService.getVestById(updateRequest.getId());
        if(v != null)
        {
          Kategorija k = this.kategorijeService.findKategorijaByName(updateRequest.getKategorija());

            this.vestiService.updateVest(updateRequest.getId(),
                    updateRequest.getNaslov(),updateRequest.getTekst(),
                    k.getId(),updateRequest.getTag());
            response.put("msg2", "Uspesno izvrsen update Vesti!");
            return Response.ok(response).build();
        }
        else
        {
            response.put("msg", "Vest ne postoji u bazi!");
            return Response.ok(response).build();
        }

    }

    @DELETE
    @Path("/delete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response obrisiVest(@PathParam("id") Integer id)
    {
        Map<String, String> response = new HashMap<>();
        boolean success = this.vestiService.deleteVestById(id);
        if(success) {
            response.put("msg", "Uspesno uradjen delete");
            return Response.ok(response).build();
        }
        else
        {
            response.put("msg", "Greska!");
            return Response.ok(response).build();
        }
    }

}
