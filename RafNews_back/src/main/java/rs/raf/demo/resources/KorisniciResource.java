package rs.raf.demo.resources;

import com.auth0.jwt.JWT;
import org.apache.commons.codec.digest.DigestUtils;
import rs.raf.demo.entities.Korisnik;
import rs.raf.demo.requests.IdRequest;
import rs.raf.demo.requests.InsertRequest;
import rs.raf.demo.requests.LoginRequest;
import rs.raf.demo.requests.UpdateRequest;
import rs.raf.demo.services.KorisniciService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/korisnici")
public class KorisniciResource {

    @Inject
    private KorisniciService korisniciService;

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getKorisnici(@QueryParam("_page") String page,@QueryParam("_limit") String limit,@Context HttpServletRequest request)
    {

        String token = request.getHeader("authorization");
        String[] split = token.split(" ");
        Integer tip = JWT.decode(split[1]).getClaims().get("tip").asInt();

        Map<String, Object> response = new HashMap<>();
        if(tip==1) {
            Map<String, Object> korisniciMap = korisniciService.getKorisniciPaginated(page, limit);
            Object[] objectArray = korisniciMap.values().toArray();
            response.put("korisnici", objectArray[0]);
            response.put("korisniciSize", objectArray[1]);
            return Response.ok(response).build();
        }
        else
        {
            response.put("msg", "Nemate pristup! Samo admin ima pristup!");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }
    }

    @PUT
    @Path("/aktivacija")
    @Produces({MediaType.APPLICATION_JSON})
    public Response activate(@Valid IdRequest idRequest,@Context HttpServletRequest request) {

        String token = request.getHeader("authorization");
        String[] split = token.split(" ");
        Integer tip = JWT.decode(split[1]).getClaims().get("tip").asInt();
        Map<String, String> response = new HashMap<>();

        if(tip==1) {
            boolean success = this.korisniciService.activateUserById(idRequest.getId());
            if (success) {
                response.put("msg", "Uspesno promenjen status korisnika!");
            } else {
                response.put("msg2", "Greska prilikom menjanja statusa!");
            }
            return Response.ok(response).build();
        }
        else
        {
            response.put("msg", "Nemate pristup! Samo admin ima pristup!");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }
    }

    @PUT
    @Path("/getKorisnik")
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(@Valid IdRequest idRequest,@Context HttpServletRequest request) {

        String token = request.getHeader("authorization");
        String[] split = token.split(" ");
        Integer tip = JWT.decode(split[1]).getClaims().get("tip").asInt();
        Map<String, Object> response = new HashMap<>();

        if(tip==1) {
            Korisnik k = this.korisniciService.getKorisnikById(idRequest.getId());
            if (k != null) {
                response.put("korisnik", k);
                return Response.ok(response).build();
            } else {
                response.put("msg2", "Korisnik sa datim id ne postoji!");
                return Response.status(422, "Unprocessable Entity").entity(response).build();
            }
        }
        else
        {
            response.put("msg", "Nemate pristup! Samo admin ima pristup!");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }
    }

    @POST
    @Path("/insert")
    @Produces({MediaType.APPLICATION_JSON})
    public Response insertKorisnik(@Valid InsertRequest insertRequest,@Context HttpServletRequest request)
    {

        String token = request.getHeader("authorization");
        String[] split = token.split(" ");
        Integer tip = JWT.decode(split[1]).getClaims().get("tip").asInt();
        Map<String, String> response = new HashMap<>();

        if(tip==1) {
            boolean found = this.korisniciService.findUserByEmail(insertRequest.getEmail());
            if (!found) {
                String hashedPassword = DigestUtils.sha256Hex(insertRequest.getLozinka());
                this.korisniciService.insertKorisnik(
                        insertRequest.getIme(), insertRequest.getPrezime(),
                        insertRequest.getEmail(), insertRequest.getTip(),
                        hashedPassword);
                response.put("msg2", "Uspesno unet korisnik!");
                return Response.ok(response).build();
            } else {
                response.put("msg", "Korisnik sa unetim email-om vec postoji!");
                return Response.status(422, "Korisnik postoji!").entity(response).build();
            }
        }else
        {
            response.put("msg", "Nemate pristup! Samo admin ima pristup!");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }
    }

    @PUT
    @Path("/update")
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateKorisnik(@Valid UpdateRequest updateRequest,@Context HttpServletRequest request)
    {
        String token = request.getHeader("authorization");
        String[] split = token.split(" ");
        Integer tip = JWT.decode(split[1]).getClaims().get("tip").asInt();
        Map<String, String> response = new HashMap<>();

        if(tip==1) {
            Korisnik k = this.korisniciService.getKorisnikById(updateRequest.getId());
            if (k != null) {
                this.korisniciService.updateKorisnik(updateRequest.getId(),
                        updateRequest.getIme(), updateRequest.getPrezime(),
                        updateRequest.getEmail(), updateRequest.getTip());
                response.put("msg2", "Uspesno izvrsen update korisnika!");
                return Response.ok(response).build();
            } else {
                response.put("msg", "Korisnik ne postoji u bazi!");
                return Response.ok(response).build();
            }
        }
        else
        {
            response.put("msg", "Nemate pristup! Samo admin ima pristup!");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }
    }

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@Valid LoginRequest loginRequest,@Context HttpServletRequest request)
    {
        Map<String, String> response = new HashMap<>();

        String jwt = this.korisniciService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (jwt == null) {
            response.put("msg", "Pogrešni kredencijali! Proverite email i password.");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }

        String ime = JWT.decode(jwt).getClaims().get("ime").asString();
        String prezime = JWT.decode(jwt).getClaims().get("prezime").asString();

        Integer id = JWT.decode(jwt).getClaims().get("id").asInt();

        int tip = JWT.decode(jwt).getClaims().get("tip").asInt();
        int stat = JWT.decode(jwt).getClaims().get("status").asInt();

        response.put("id", String.valueOf(id));
        response.put("jwt", jwt);
        response.put("ime",ime+" "+prezime);
        response.put("tip", String.valueOf(tip));
        response.put("statk", String.valueOf(stat));

        return Response.ok(response).build();
    }

}
