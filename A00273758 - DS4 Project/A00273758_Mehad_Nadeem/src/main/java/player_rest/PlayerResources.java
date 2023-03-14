package player_rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


@Path("/players")
public class PlayerResources {
	
	// ----------CREATE END-POINTS FOR CLIENT----------\\
	
	//----------VIEW FUNCTIONALITY----------\\
	@GET
	@Produces( {MediaType.APPLICATION_XML} )
	@Path("/view/all")
	public List<Player> getPlayers(){
		return PlayerDAO.instance.viewAllPlayers();
	}
	
//	@GET
//	@Produces({ MediaType.TEXT_PLAIN })
//	@Path("/view/all")
//	public String getAllPlayers() {
//		return PlayerDAO.instance.viewAllPlayers();
//	}
	
	@GET
	@Produces( {MediaType.APPLICATION_XML} )
	@Path("/view/{player_id}")
	public Player getPlayerByID(@PathParam("player_id") String id) {
		return PlayerDAO.instance.viewPlayerByID(Integer.parseInt(id));
	}

	//----------DELETE FUNCTIONALITY----------\\
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/delete/{player_id}")
	public void deletePlayerByID(@PathParam("player_id") String id) {
		PlayerDAO.instance.deletePlayerbyID(Integer.parseInt(id));
	}
	
	//----------CREATE FUNCTIONALITY----------\\
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/create")
	public void createPlayer(@FormParam("name") String name, @FormParam("age") String age,
			@FormParam("gender") String gender, @FormParam("nationality") String nationality,
			@FormParam("club") String club, @FormParam("appearances") String app, @FormParam("goals") String goals,
			@FormParam("assists") String assists, @Context HttpServletResponse servletResponse) {

		Player player = new Player();

		player.setName(name);
		player.setAge(Integer.parseInt(age));
		player.setGender(gender.charAt(0));
		player.setNationality(nationality);
		player.setClub(club);
		player.setAppearances(Integer.parseInt(app));
		player.setGoals(Integer.parseInt(goals));
		player.setAssists(Integer.parseInt(assists));

		PlayerDAO.instance.createPlayer(player);
	}

	//----------UPDATE FUNCTIONALITY----------\\
	
	
}
