package org.poc.usecases;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/de21service")
public class DE21ServiceResource {
	@Context
	private UriInfo uriInfo;
	
	@GET
	public void sendMessage(){
		System.out.println(">>>>GET Called <<<<<<");
	}

}
