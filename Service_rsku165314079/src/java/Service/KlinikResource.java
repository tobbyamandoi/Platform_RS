/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.google.gson.Gson;
import helper.klinikHelper;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import pojos.Klinik;

/**
 * REST Web Service
 *
 * @author user
 */
@Path("Klinik")
public class KlinikResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public KlinikResource() {
    }

    /**
     * Retrieves representation of an instance of Service.UserResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {
        klinikHelper helper = new klinikHelper();
        List<Klinik> list = helper.getKlinik();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return Response
                .status(200)
                .entity(json)
                .build();
    }

    @POST
    @Path("addKlinik")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewKlinik(String data) {
        Gson gson = new Gson();
        Klinik klinik = gson.fromJson(data, Klinik.class);
        klinikHelper helper = new klinikHelper();
        helper.addNewKlinik(
                klinik.getIdKlinik(),
                klinik.getNama(),
                klinik.getSpesialis());
        return Response
                .status(200)
                .entity(klinik)
                .build();
    }
}
