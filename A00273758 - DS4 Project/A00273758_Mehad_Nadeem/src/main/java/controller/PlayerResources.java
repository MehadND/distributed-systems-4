package controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/players")
public class PlayerResources
{

	@Path("test")
	@GET
	@Produces( {MediaType.TEXT_PLAIN} )
	public String testDBGetBooks(){
		return PlayerDAO.instance.testDB_getAllPlayers();
	}
}
