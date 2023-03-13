package myApp;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// create endpoints
@Path("/players")
public class PlayerResources
{
	@Path("/view/all")
	@GET
	@Produces( {MediaType.TEXT_PLAIN} )
	public String getAllPlayers(){
		return PlayerDAO.instance.viewAllPlayers();
	}
	
	@GET
	@Produces( {MediaType.TEXT_PLAIN} )
	@Path("/view/{playerID}")
	public String getPlayerByID(@PathParam("playerID") String id) {
		return PlayerDAO.instance.viewPlayerByID(Integer.parseInt(id));
	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/delete/{playerID}")
	public String deletePlayerByID(@PathParam("playerID") String id) {
		PlayerDAO.instance.deletePlayerbyID(Integer.parseInt(id));
		return "Successfully deleted player with id = "+id;
	}
}
