package player_rest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class PlayerController implements ActionListener, MouseListener {

	PlayerView view;

	public PlayerController() {
		view = new PlayerView();

		view.init();

		// Add action listners to all buttons/interactables
		view.getCreatePlayerButton().addActionListener(this);
		view.getViewPlayerButton().addActionListener(this);
		view.getViewPlayerByIDButton().addActionListener(this);
		view.getUpdatePlayerButton().addActionListener(this);
		view.getDeletePlayerByIDButton().addActionListener(this);
		view.getClearInputFieldsButton().addActionListener(this);

		view.getDbTable().addMouseListener(this);
	}

	// ----------INTERACTIONS CONTROL----------\\

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.getCreatePlayerButton()) {
			System.out.println("getCreatePlayerButton Clicked!");
//			view.getDebuggerConsoleArea().setText(view.getDebuggerConsoleArea().getText()+"ddsvsdvdsv");
			createPlayer();
			view.refreshDBTable();
			view.clearInputFields();
		}
		if (e.getSource() == view.getViewPlayerButton()) {
			System.out.println("getViewPlayerButton Clicked!");
			viewPlayerAll();
		}
		if (e.getSource() == view.getViewPlayerByIDButton()) {
			System.out.println("getViewPlayerByIDButton Clicked!");
			viewPlayerByID(Integer.parseInt(view.getPlayerIDTextField().getText()));
		}
		if (e.getSource() == view.getUpdatePlayerButton()) {
			System.out.println("getUpdatePlayerButton Clicked!");
		}
		if (e.getSource() == view.getDeletePlayerByIDButton()) {
			System.out.println("getDeletePlayerByIDButton Clicked!");
			deletePlayerByID(Integer.parseInt(view.getPlayerIDTextField().getText()));
			view.refreshDBTable();
			view.clearInputFields();
		}
		if (e.getSource() == view.getClearInputFieldsButton()) {
			view.clearInputFields();
		}

	}

	// ----------CRUD FUNCTONALITY ON CLIENT SIDE----------\\
	public void createPlayer() {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		HttpEntity entity = null;
		try {
			URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
					.setPath("/a00273758.mehadnadeem/rest/players/create").build();

			// System.out.println(uri.toString());

			HttpPost httpPost = new HttpPost(uri);
			httpPost.setHeader("Accept", "text/plain");

//			Form f = new Form();
//			
//			f.add("name", nameTextField.getText());
//			f.add("age", Integer.parseInt(ageTextField.getText()));
//			f.add("gender", genderTextField.getText().charAt(0));
//			f.add("nationality", nationalityTextField.getText());
//			f.add("club", clubTextField.getText());
//			f.add("app", Integer.parseInt(appearancesTextField.getText()));
//			f.add("goals", Integer.parseInt(goalsTextField.getText()));
//			f.add("assists", Integer.parseInt(assistsTextField.getText()));

			List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("player_id", null));
			params.add(new BasicNameValuePair("name", view.getPlayerNameTextField().getText()));
			params.add(new BasicNameValuePair("age", view.getPlayerAgeTextField().getText()));
			params.add(new BasicNameValuePair("gender", view.getPlayerGenderTextField().getText()));
			params.add(new BasicNameValuePair("nationality", view.getPlayerNationalityTextField().getText()));
			params.add(new BasicNameValuePair("club", view.getPlayerClubTextField().getText()));
			params.add(new BasicNameValuePair("appearances", view.getPlayerAppearancesTextField().getText()));
			params.add(new BasicNameValuePair("goals", view.getPlayerGoalsTextField().getText()));
			params.add(new BasicNameValuePair("assists", view.getPlayerAssistsTextField().getText()));

			httpPost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));

			httpClient = HttpClients.createDefault();
			httpResponse = httpClient.execute(httpPost);

			String text;

			entity = httpResponse.getEntity();

			text = EntityUtils.toString(entity);

			view.getDebuggerConsoleArea().setText(
					view.getDebugStarterLine() + "Created Player..." + view.getPlayerNameTextField().getText());
			view.getTableModel().fireTableDataChanged();

		} catch (Exception ex) {
			// notify user that player was created even though entity was giving an error
			// (saying Entity may not be null)
			if (entity == null)
				view.getDebuggerConsoleArea().setText(
						view.getDebugStarterLine() + "Created Player..." + view.getPlayerNameTextField().getText());
			else
				view.getDebuggerConsoleArea().setText(view.getDebugStarterLine() + "Error: Cannot Create Player");

			ex.printStackTrace();
		}
	}

	public void deletePlayerByID(int id) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;

		try {
			URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
					.setPath("/a00273758.mehadnadeem/rest/players/delete/" + id).build();

			// System.out.println(uri.toString());

			HttpDelete httpDelete = new HttpDelete(uri);
			httpDelete.setHeader("Accept", "text/plain");

			httpClient = HttpClients.createDefault();
			httpResponse = httpClient.execute(httpDelete);

			String text;

			HttpEntity entity = httpResponse.getEntity();

			text = EntityUtils.toString(entity);

			view.getDebuggerConsoleArea().setText(view.getDebugStarterLine() + "Deleting Player of ID = " + id);
			view.getTableModel().fireTableDataChanged();

		} catch (Exception ex) {
			view.getDebuggerConsoleArea()
					.setText(view.getDebugStarterLine() + "Error: Cannot Delete Player at ID = " + id);
			ex.printStackTrace();
		}
	}

	public void viewPlayerAll() {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;

		String text;
		try {
			URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
					.setPath("/a00273758.mehadnadeem/rest/players/view/all").build();
			// System.out.println(uri.toString());

			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader("Accept", "application/xml");

			httpClient = HttpClients.createDefault();
			httpResponse = httpClient.execute(httpGet);

			HttpEntity entity = httpResponse.getEntity();

			text = EntityUtils.toString(entity);

			PlayerXMLParser playerXMLParser = null;
			List<Player> playersList = new PlayerXMLParser().startParsing(text);

			view.getDebuggerConsoleArea().setText("------------------------------------\n");

			for (Player p : playersList) {
				view.getDebuggerConsoleArea()
						.append("Player ID: " + p.getPlayer_id() + "\nPlayer Name: " + p.getName() + "\nPlayer Age: "
								+ p.getAge() + "\nPlayer Gender: " + p.getGender() + "\nPlayer Nationality: "
								+ p.getNationality() + "\nPlayer Club: " + p.getClub() + "\nPlayer App.: "
								+ p.getAppearances() + "\nPlayer Goals: " + p.getGoals() + "\nPlayer Assists: "
								+ p.getAssists() + "\n------------------------------------\n");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void viewPlayerByID(int id) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;

		String text;
		try {
			URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
					.setPath("/a00273758.mehadnadeem/rest/players/view/" + id).build();
			// System.out.println(uri.toString());

			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader("Accept", "application/xml");

			httpClient = HttpClients.createDefault();
			httpResponse = httpClient.execute(httpGet);

			HttpEntity entity = httpResponse.getEntity();

			text = EntityUtils.toString(entity);
			System.out.println(text);

			PlayerXMLParser playerXMLParser = null;
			Player player = new PlayerXMLParser().startParsing2(text);

			view.getDebuggerConsoleArea().setText("------------------------------------\n");

			view.getDebuggerConsoleArea()
					.append("Player ID: " + player.getPlayer_id() + "\nPlayer Name: " + player.getName()
							+ "\nPlayer Age: " + player.getAge() + "\nPlayer Gender: " + player.getGender()
							+ "\nPlayer Nationality: " + player.getNationality() + "\nPlayer Club: " + player.getClub()
							+ "\nPlayer App.: " + player.getAppearances() + "\nPlayer Goals: " + player.getGoals()
							+ "\nPlayer Assists: " + player.getAssists() + "\n------------------------------------\n");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		// fill the input fields with the data from the selected row
		if (e.getSource() == view.getDbTable()) {
			view.getPlayerIDTextField()
					.setText(view.getDbTable().getValueAt(view.getDbTable().getSelectedRow(), 0).toString());
			view.getPlayerNameTextField()
					.setText(view.getDbTable().getValueAt(view.getDbTable().getSelectedRow(), 1).toString());
			view.getPlayerAgeTextField()
					.setText(view.getDbTable().getValueAt(view.getDbTable().getSelectedRow(), 2).toString());
			view.getPlayerGenderTextField()
					.setText(view.getDbTable().getValueAt(view.getDbTable().getSelectedRow(), 3).toString());
			view.getPlayerNationalityTextField()
					.setText(view.getDbTable().getValueAt(view.getDbTable().getSelectedRow(), 4).toString());
			view.getPlayerClubTextField()
					.setText(view.getDbTable().getValueAt(view.getDbTable().getSelectedRow(), 5).toString());
			view.getPlayerAppearancesTextField()
					.setText(view.getDbTable().getValueAt(view.getDbTable().getSelectedRow(), 6).toString());
			view.getPlayerGoalsTextField()
					.setText(view.getDbTable().getValueAt(view.getDbTable().getSelectedRow(), 7).toString());
			view.getPlayerAssistsTextField()
					.setText(view.getDbTable().getValueAt(view.getDbTable().getSelectedRow(), 8).toString());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
