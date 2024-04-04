package com.svi.main;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.svi.config.ConfigInitiator;
import com.svi.dao.RecordsImpl;
import com.svi.model.SaveRequest;

@Path("saveMove")
public class Save {

	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveMove(SaveRequest data) throws IOException {
		
		try {
//			if (!initializeConfig()) {
//				System.out.println("Problem in initializing the config file");
//			}
			
			RecordsImpl records = new RecordsImpl();
            records.saveRecords(data);

			return Response
                    .status(Response.Status.OK)
                    .entity("Success")
                    .build();
        } catch (Exception e) {
            // Handle other unexpected exceptions
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to save move: " + e.getMessage()).build();
        }
	}
	
	private static Boolean initializeConfig() {

		try {
			System.out.println("CONSOLE IS WORKING");
			ConfigInitiator.initialize("config/config.ini");
			System.out.println("Config Initialized");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("CONFIG FILE NOT FOUND!");
			return false;
		}
	}
}