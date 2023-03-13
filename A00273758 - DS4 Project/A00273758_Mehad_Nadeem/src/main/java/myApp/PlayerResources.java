package myApp;

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

// create endpoints
@Path("/players")
public class PlayerResources {
	@Path("/view/all")
	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	public String getAllPlayers() {
		return PlayerDAO.instance.viewAllPlayers();
	}

	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	@Path("/view/{playerID}")
	public String getPlayerByID(@PathParam("playerID") String id) {
		return PlayerDAO.instance.viewPlayerByID(Integer.parseInt(id));
	}

	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/delete/{playerID}")
	public String deletePlayerByID(@PathParam("playerID") String id) {
		PlayerDAO.instance.deletePlayerbyID(Integer.parseInt(id));
		return "Successfully deleted player with id = " + id;
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/create")
	public void createPlayer(@FormParam("name") String name, @FormParam("age") String age,
			@FormParam("gender") String gender, @FormParam("nationality") String nationality,
			@FormParam("club") String club, @FormParam("app") String app, @FormParam("goals") String goals,
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
		System.out.println("Player Created");
	}
}
