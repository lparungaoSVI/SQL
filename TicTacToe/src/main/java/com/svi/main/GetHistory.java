package com.svi.main;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.svi.dao.RecordsImpl;

@Path("history")
public class GetHistory {

    @GET
    @Path("/gameRecords/{key}")
    public Response getHistory(@PathParam("key") String key) {
        try {

        	RecordsImpl records = new RecordsImpl();
            List<String> gameUIDs = records.getGameRecords(key);
            
            return Response
                    .status(Response.Status.OK)
                    .entity(gameUIDs)
                    .build();
        } catch (Exception e) {
            // Handle other unexpected exceptions
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("The server ran into an unexpected exception: " + e.getMessage())
                    .build();
        }
    }
    
    @GET
    @Path("/gameDetails/{key}")
    public Response getGame(@PathParam("key") String key) {
        try {
           
        	RecordsImpl records = new RecordsImpl();
            List<String> gameData = records.getGameDetails(key);

            return Response
                    .status(Response.Status.OK)
                    .entity(gameData)
                    .build();
        } catch (Exception e) {
            // Handle other unexpected exceptions
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("The server ran into an unexpected exception: " + e.getMessage())
                    .build();
        }
    }
}
