/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.google.gson.Gson;
import helper.lokasiHelper;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;
import pojos.Location;

/**
 * REST Web Service
 *
 * @author user
 */
@Path("Lokasi")
public class LokasiResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public LokasiResource() {
    }

    /**
     * Retrieves representation of an instance of Service.UserResource
     * @return an instance of java.lang.String
     */
     @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getJson() {
        //TODO return proper representation object
        lokasiHelper l = new lokasiHelper();
        Gson gson = new Gson();
        return Response.status(Response.Status.OK)
                .entity(gson.toJson(l.bacaSemuaLokasi()))
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
    @GET
    @Path("getLocation")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLocation() {
        lokasiHelper helper = new lokasiHelper();
        List<Location> list = helper.getLocation();
        Gson gson = new Gson();
        return Response.status(200)
                .entity(gson.toJson(list))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods",
                        "GET,POST,HEAD,OPTIONS,PUT")
                .header("Access-Control-Allow-Headers",
                        "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers")
                .header("Access-Exposed-Headers",
                        "Access-Control-Allow-Origin,Access-Control-Allow-Credentials")
                .header("Access-Support-Credentials",
                        "true")
                .header("Access-Control-Max-Age", "2")
                .header("Access-Preflight-Maxage", "2")
                .build();
    }

    /**
     * PUT method for updating or creating an instance of lokasiResource
     *
     * @param content representation for the resource
     */
    @POST
    @Path("addLocation")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewLocation(String data) {
        Gson gson = new Gson();
        Location location = gson.fromJson(data, Location.class);
        lokasiHelper helper = new lokasiHelper();
        helper.addNewLocation(location.getLat(),
                location.getLng(),
                location.getName());

        return Response
                .status(200)
                .entity(location)
                .build();
    }
}
