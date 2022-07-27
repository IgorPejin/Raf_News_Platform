package rs.raf.demo.resources;

import rs.raf.demo.entities.Kategorija;
import rs.raf.demo.requests.IdRequest;
import rs.raf.demo.requests.InsertKategorijaRequest;
import rs.raf.demo.requests.UpdateKategorijaRequest;
import rs.raf.demo.services.KategorijeService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/kategorije")
public class KategorijeResource {

    @Inject
    private KategorijeService kategorijeService;


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll()
    {
        Map<String,Object> response = new HashMap<>();
        List<String> kategorije = this.kategorijeService.getKategorijeAll();
        response.put("kategorije", kategorije);
        return Response.ok(response).build();
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getKategorije(@QueryParam("_page") String page, @QueryParam("_limit") String limit)
    {
        Map<String,Object> response = new HashMap<>();
        Map<String,Object> korisniciMap = kategorijeService.getKategorijePaginated(page,limit);

        Object[] objectArray = korisniciMap.values().toArray();

        response.put("kategorije", objectArray[0]);
        response.put("kategorijeSize", objectArray[1]);

        return Response.ok(response).build();
    }

    @POST
    @Path("/insert")
    @Produces({MediaType.APPLICATION_JSON})
    public Response insertKorisnik(@Valid InsertKategorijaRequest insertRequest)
    {
        Map<String, String> response = new HashMap<>();
        Kategorija found = this.kategorijeService.findKategorijaByName(insertRequest.getIme());
        if(found==null)
        {
            this.kategorijeService.insertKategorija (
                    insertRequest.getIme(),insertRequest.getOpis());
            response.put("msg2", "Uspesno uneta kategorija!");
            return Response.ok(response).build();
        }
        else
        {
            response.put("msg", "Kategorija sa unetim imenom vec postoji!");
            return Response.status(422, "Kategorija postoji!").entity(response).build();
        }
    }

    @PUT
    @Path("/getKategorija")
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(@Valid IdRequest idRequest) {
        Map<String, Object> response = new HashMap<>();
        Kategorija k = this.kategorijeService.getKategorijaById(idRequest.getId());
        if (k!=null)
        {
            response.put("kategorija", k );
            return Response.ok(response).build();
        }
        else
        {
            response.put("msg2", "Kategorija sa datim id ne postoji!");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }
    }

    @PUT
    @Path("/update")
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateKorisnik(@Valid UpdateKategorijaRequest updateRequest)
    {
        Map<String, String> response = new HashMap<>();
        Kategorija k  = this.kategorijeService.getKategorijaById(updateRequest.getId());
        if(k != null)
        {
            this.kategorijeService.updateKategorija(updateRequest.getId(),
                    updateRequest.getIme(),updateRequest.getOpis());
            response.put("msg2", "Uspesno izvrsen update kategorije!");
            return Response.ok(response).build();
        }
        else
        {
            response.put("msg", "Kategorija ne postoji u bazi!");
            return Response.ok(response).build();
        }
    }


    @POST
    @Path("/delete")
    @Produces({MediaType.APPLICATION_JSON})
    public Response obrisiKategoriju(@Valid IdRequest idRequest)
    {
        // ovu rutu definitivno moram autorizovati

        Map<String, String> response = new HashMap<>();
        boolean success = this.kategorijeService.deleteKategorijaById(idRequest.getId());
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
