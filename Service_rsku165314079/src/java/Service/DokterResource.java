/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.google.gson.Gson;
import helper.dokterHelper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;
import pojos.Dokter;

/**
 * REST Web Service
 *
 * @author user
 */
@Path("Dokter")
public class DokterResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public DokterResource() {
    }

    /**
     * Retrieves representation of an instance of Service.UserResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getJson() {
        //TODO return proper representation object
        dokterHelper d = new dokterHelper();
        Gson gson = new Gson();
        return Response.status(Response.Status.OK)
                .entity(gson.toJson(d.bacaSemuaLokasi()))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods",
                        "GET,POST,HEAD,OPTIONS,PUT")
                .header("Access-Control-Allow-Headers",
                        "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers")
                .header("Access-Exposed-Headers",
                        "Access-Control-Allow-Origin,Access-Control-Allow-Credentials")
                .header("Access-Support-Credentials",
                        "true")
                .header("Access-Control-Max-Age", "20")
                .header("Access-Preflight-Maxage", "20")
                .build();
    }

    /**
     * PUT method for updating or creating an instance of lokasiResource
     *
     * @param content representation for the resource
     */
//    @PUT
//    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
//    public void putJson(String content) {
//    }
    @POST
    @Path("addDokter")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewDokter(String data) {
        Gson gson = new Gson();
        Dokter dokter = gson.fromJson(data, Dokter.class);
        dokterHelper helper = new dokterHelper();
        helper.addNewDokter(
                dokter.getNama(),
                dokter.getSpesialis());

        return Response
                .status(200)
                .entity(dokter)
                .build();
    }

}
