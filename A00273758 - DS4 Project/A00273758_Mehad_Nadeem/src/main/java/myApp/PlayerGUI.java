package myApp;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.FormParam;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Entity;

import com.sun.jersey.api.representation.Form;

public class PlayerGUI extends JFrame implements ActionListener, MouseListener {
	private Container contentPane = this.getContentPane();

	String debugLine = "  > ";
	private JTextArea debuggerOutpuTextArea = new JTextArea(debugLine);

	private JLabel viewPlayersLabel = new JLabel("List of All Players");
	private JTextArea viewPlayersTextArea = new JTextArea(10, 1);

	// input fields + labels
	private JLabel playerIDLabel = new JLabel("Player ID: ");
	private JLabel nameLabel = new JLabel("Player Name: ");
	private JLabel ageLabel = new JLabel("Player Age: ");
	private JLabel genderLabel = new JLabel("Player Gender: ");
	private JLabel nationalityLabel = new JLabel("Player Nationality: ");
	private JLabel clubLabel = new JLabel("Player Club: ");
	private JLabel appearancesLabel = new JLabel("Player App: ");
	private JLabel goalsLabel = new JLabel("Player Goals: ");
	private JLabel assistsLabel = new JLabel("Player Assists: ");

	private JTextField playerIDTextField = new JTextField(15);
	private JTextField nameTextField = new JTextField(15);
	private JTextField ageTextField = new JTextField(15);
	private JTextField genderTextField = new JTextField(15);
	private JTextField nationalityTextField = new JTextField();
	private JTextField clubTextField = new JTextField();
	private JTextField appearancesTextField = new JTextField();
	private JTextField goalsTextField = new JTextField();
	private JTextField assistsTextField = new JTextField();

	private JPanel inputFieldsPanel = new JPanel();

	private JButton createPlayerButton = new JButton("Create Player");
	private JButton viewPlayersButton = new JButton("View All Players");
	private JButton viewPlayerByIDButton = new JButton("View Player By ID");
	private JButton updatePlayerButton = new JButton("Update Player");
	private JButton deletePlayerByIDButton = new JButton("Delete Player By ID");

	private JPanel crudButtonsPanel = new JPanel();
	
	String[] columnNames = {"ID", "Name", "Age", "Gender", "Nationality", "Club", "App", "Goals", "Assists"};
	DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
	
	JTable table;
	JScrollPane scrollPane;
	
	JPanel dbPanel = new JPanel();

	public PlayerGUI() {
		this.setTitle("A00273758_Mehad_Nadeem - DS4 Project");
		this.setResizable(false);
//        this.setLayout(new BorderLayout());
		this.setLayout(new GridLayout(1, 3));
		this.setSize(1000, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void gui_init() {

		table = new JTable(tableModel);
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400,250));
		refreshDB();
		dbPanel.add(scrollPane);
		crudButtonsPanel();
		inputFieldsPanel();

		table.addMouseListener(this);
		// contentPane.add(viewPanel, BorderLayout.EAST);
		// contentPane.add(inputFieldsPanel, BorderLayout.WEST);
		contentPane.add(inputFieldsPanel);
		crudButtonsPanel.add(dbPanel);
		contentPane.add(crudButtonsPanel);

		this.setVisible(true);
	}
	
	public void refreshDB()
	{
		tableModel.setRowCount(0);

		PlayerDAO.instance.showTable(tableModel);

	}

	public void crudButtonsPanel() {
		createPlayerButton.addActionListener(this);
		viewPlayersButton.addActionListener(this);
		viewPlayerByIDButton.addActionListener(this);
		updatePlayerButton.addActionListener(this);
		deletePlayerByIDButton.addActionListener(this);

//		createPlayerButton.setAlignmentY(Component.CENTER_ALIGNMENT);
//		viewPlayersButton.setAlignmentY(Component.CENTER_ALIGNMENT);
//		viewPlayerByIDButton.setAlignmentY(Component.CENTER_ALIGNMENT);
//		updatePlayerButton.setAlignmentY(Component.CENTER_ALIGNMENT);
//		deletePlayerByIDButton.setAlignmentY(Component.CENTER_ALIGNMENT);

		crudButtonsPanel.add(createPlayerButton);
		crudButtonsPanel.add(viewPlayersButton);
		// crudButtonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		crudButtonsPanel.add(viewPlayerByIDButton);
		// crudButtonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		crudButtonsPanel.add(updatePlayerButton);
		// crudButtonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		crudButtonsPanel.add(deletePlayerByIDButton);

//		GridLayout crudPanelLayout = new GridLayout(5, 1);
		//crudButtonsPanel.setLayout(new BoxLayout(crudButtonsPanel, BoxLayout.Y_AXIS));
		// crudButtonsPanel.setAlignmentY(CENTER_ALIGNMENT);

		// crudButtonsPanel.setBorder(new EmptyBorder(250, 100, 0, 0));

		crudButtonsPanel.setVisible(true);
	}
	

	public void inputFieldsPanel() {
		
		debuggerOutpuTextArea.setEditable(false);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		inputFieldsPanel.setLayout(gridBagLayout);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(20, 0, 10, 0);

		int y = 0;

		constraints.gridx = 0;
		constraints.gridy = y;
		gridBagLayout.setConstraints(playerIDLabel, constraints);
		inputFieldsPanel.add(playerIDLabel);
		constraints.gridx = 1;
		constraints.gridy = 0;
		gridBagLayout.setConstraints(playerIDTextField, constraints);
		inputFieldsPanel.add(playerIDTextField);

		y++;

		constraints.gridx = 0;
		constraints.gridy = y;
		gridBagLayout.setConstraints(nameLabel, constraints);
		inputFieldsPanel.add(nameLabel);
		constraints.gridx = 1;
		constraints.gridy = y;
		gridBagLayout.setConstraints(nameTextField, constraints);
		inputFieldsPanel.add(nameTextField);

		y++;

		constraints.gridx = 0;
		constraints.gridy = y;
		gridBagLayout.setConstraints(ageLabel, constraints);
		inputFieldsPanel.add(ageLabel);
		constraints.gridx = 1;
		constraints.gridy = y;
		gridBagLayout.setConstraints(ageTextField, constraints);
		inputFieldsPanel.add(ageTextField);

		y++;

		constraints.gridx = 0;
		constraints.gridy = y;
		gridBagLayout.setConstraints(genderLabel, constraints);
		inputFieldsPanel.add(genderLabel);
		constraints.gridx = 1;
		constraints.gridy = y;
		gridBagLayout.setConstraints(genderTextField, constraints);
		inputFieldsPanel.add(genderTextField);

		y++;

		constraints.gridx = 0;
		constraints.gridy = y;
		gridBagLayout.setConstraints(nationalityLabel, constraints);
		inputFieldsPanel.add(nationalityLabel);
		constraints.gridx = 1;
		constraints.gridy = y;
		gridBagLayout.setConstraints(nationalityTextField, constraints);
		inputFieldsPanel.add(nationalityTextField);

		y++;

		constraints.gridx = 0;
		constraints.gridy = y;
		gridBagLayout.setConstraints(clubLabel, constraints);
		inputFieldsPanel.add(clubLabel);
		constraints.gridx = 1;
		constraints.gridy = y;
		gridBagLayout.setConstraints(clubTextField, constraints);
		inputFieldsPanel.add(clubTextField);

		y++;

		constraints.gridx = 0;
		constraints.gridy = y;
		gridBagLayout.setConstraints(appearancesLabel, constraints);
		inputFieldsPanel.add(appearancesLabel);
		constraints.gridx = 1;
		constraints.gridy = y;
		gridBagLayout.setConstraints(appearancesTextField, constraints);
		inputFieldsPanel.add(appearancesTextField);

		y++;

		constraints.gridx = 0;
		constraints.gridy = y;
		gridBagLayout.setConstraints(goalsLabel, constraints);
		inputFieldsPanel.add(goalsLabel);
		constraints.gridx = 1;
		constraints.gridy = y;
		gridBagLayout.setConstraints(goalsTextField, constraints);
		inputFieldsPanel.add(goalsTextField);

		y++;

		constraints.gridx = 0;
		constraints.gridy = y;
		gridBagLayout.setConstraints(assistsLabel, constraints);
		inputFieldsPanel.add(assistsLabel);
		constraints.gridx = 1;
		constraints.gridy = y;
		gridBagLayout.setConstraints(assistsTextField, constraints);
		inputFieldsPanel.add(assistsTextField);

		y++;

		constraints.gridx = 0;
		constraints.gridy = y;
		constraints.gridwidth = 2;
		constraints.ipadx = 100;
		constraints.ipady = 40;
		debuggerOutpuTextArea.setAlignmentY(CENTER_ALIGNMENT);
		debuggerOutpuTextArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Debugger",
				TitledBorder.LEFT, TitledBorder.TOP));
		gridBagLayout.setConstraints(debuggerOutpuTextArea, constraints);
		inputFieldsPanel.add(debuggerOutpuTextArea);

		inputFieldsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

		inputFieldsPanel.setVisible(true);
	}

	public void printAllPlayers() {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;

		String text;
		try {
			URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
					.setPath("/a00273758.mehadnadeem/rest/players/view/all").build();
			// System.out.println(uri.toString());

			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader("Accept", "text/plain");

			httpClient = HttpClients.createDefault();
			httpResponse = httpClient.execute(httpGet);

			HttpEntity entity = httpResponse.getEntity();

			text = EntityUtils.toString(entity);

			debuggerOutpuTextArea.setText(debugLine + "Getting All Players...");
			viewPlayersTextArea.setText(text);

		} catch (Exception ex) {
			viewPlayersTextArea.setText(ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void viewPlayerByID(int id) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;

		try {
			URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
					.setPath("/a00273758.mehadnadeem/rest/players/view/" + id).build();

			// System.out.println(uri.toString());

			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader("Accept", "text/plain");

			httpClient = HttpClients.createDefault();
			httpResponse = httpClient.execute(httpGet);

			String text;

			HttpEntity entity = httpResponse.getEntity();

			text = EntityUtils.toString(entity);

			debuggerOutpuTextArea.setText(debugLine + "Getting Player By ID #" + id + "...");
			//viewPlayersTextArea.setText(text);

		} catch (Exception ex) {
			viewPlayersTextArea.setText(ex.getMessage());
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

			debuggerOutpuTextArea.setText(debugLine + "Deleting Player By ID #" + id + "...");
			viewPlayersTextArea.setText(text);
			tableModel.fireTableDataChanged();
			clearInputFields();

		} catch (Exception ex) {
			viewPlayersTextArea.setText(ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void createPlayer() {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;

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
			params.add(new BasicNameValuePair("player_id",  null));
	        params.add(new BasicNameValuePair("name",  nameTextField.getText()));
	        params.add(new BasicNameValuePair("age", ageTextField.getText()));
	        params.add(new BasicNameValuePair("gender", genderTextField.getText()));
	        params.add(new BasicNameValuePair("nationality", nationalityTextField.getText()));
	        params.add(new BasicNameValuePair("club", clubTextField.getText()));
	        params.add(new BasicNameValuePair("appearances", appearancesTextField.getText()));
	        params.add(new BasicNameValuePair("goals", goalsTextField.getText()));
	        params.add(new BasicNameValuePair("assists", assistsTextField.getText()));

	        httpPost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));
			
	        httpClient = HttpClients.createDefault();
			httpResponse = httpClient.execute(httpPost);

			String text;

			HttpEntity entity = httpResponse.getEntity();

			text = EntityUtils.toString(entity);

			debuggerOutpuTextArea.setText(debugLine + "Created Player..."+nameTextField.getText());
			viewPlayersTextArea.setText(text);
			tableModel.fireTableDataChanged();
			
		} catch (Exception ex) {
			viewPlayersTextArea.setText(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
//		if (e.getSource() == viewPlayersButton) {
//			printAllPlayers();
//		}
//		if (e.getSource() == viewPlayerByIDButton) {
//			if (playerIDTextField.getText() == null || playerIDTextField.getText().isBlank()
//					|| playerIDTextField.getText().isEmpty()) {
//				JOptionPane.showMessageDialog(null, "Please enter a Player ID!");
//			} else {
//				viewPlayerByID(Integer.parseInt(playerIDTextField.getText()));
//			}
//		}
		if (e.getSource() == deletePlayerByIDButton) {
			if (playerIDTextField.getText() == null || playerIDTextField.getText().isBlank()
					|| playerIDTextField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter a Player ID!");
			} else {
				deletePlayerByID(Integer.parseInt(playerIDTextField.getText()));
				refreshDB();
			}
		}
		if(e.getSource() == createPlayerButton)
		{
			createPlayer();
			refreshDB();
			clearInputFields();
		}

	}
	
	public void clearInputFields()
	{
		playerIDTextField.setText("");
		nameTextField.setText("");
		ageTextField.setText("");
		genderTextField.setText("");
		nationalityTextField.setText("");
		clubTextField.setText("");
		appearancesTextField.setText("");
		goalsTextField.setText("");
		assistsTextField.setText("");
		
	}

	public JTextArea getDebuggerOutpuTextArea() {
		return debuggerOutpuTextArea;
	}

	public JTextArea getViewPlayersTextArea() {
		return viewPlayersTextArea;
	}

	public JTextField getPlayerIDTextField() {
		return playerIDTextField;
	}

	public JTextField getNameTextField() {
		return nameTextField;
	}

	public JTextField getAgeTextField() {
		return ageTextField;
	}

	public JTextField getGenderTextField() {
		return genderTextField;
	}

	public JTextField getNationalityTextField() {
		return nationalityTextField;
	}

	public JTextField getClubTextField() {
		return clubTextField;
	}

	public JTextField getAppearancesTextField() {
		return appearancesTextField;
	}

	public JTextField getGoalsTextField() {
		return goalsTextField;
	}

	public JTextField getAssistsTextField() {
		return assistsTextField;
	}

	public JPanel getInputFieldsPanel() {
		return inputFieldsPanel;
	}

	public JButton getCreatePlayerButton() {
		return createPlayerButton;
	}

	public JButton getViewPlayersButton() {
		return viewPlayersButton;
	}

	public JButton getViewPlayerByIDButton() {
		return viewPlayerByIDButton;
	}

	public JButton getUpdatePlayerButton() {
		return updatePlayerButton;
	}

	public JButton getDeletePlayerByIDButton() {
		return deletePlayerByIDButton;
	}

	public JPanel getCrudButtonsPanel() {
		return crudButtonsPanel;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public JTable getTable() {
		return table;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JPanel getDbPanel() {
		return dbPanel;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public void setDbPanel(JPanel dbPanel) {
		this.dbPanel = dbPanel;
	}

	public void setDebuggerOutpuTextArea(JTextArea debuggerOutpuTextArea) {
		this.debuggerOutpuTextArea = debuggerOutpuTextArea;
	}

	public void setViewPlayersTextArea(JTextArea viewPlayersTextArea) {
		this.viewPlayersTextArea = viewPlayersTextArea;
	}

	public void setPlayerIDTextField(JTextField playerIDTextField) {
		this.playerIDTextField = playerIDTextField;
	}

	public void setNameTextField(JTextField nameTextField) {
		this.nameTextField = nameTextField;
	}

	public void setAgeTextField(JTextField ageTextField) {
		this.ageTextField = ageTextField;
	}

	public void setGenderTextField(JTextField genderTextField) {
		this.genderTextField = genderTextField;
	}

	public void setNationalityTextField(JTextField nationalityTextField) {
		this.nationalityTextField = nationalityTextField;
	}

	public void setClubTextField(JTextField clubTextField) {
		this.clubTextField = clubTextField;
	}

	public void setAppearancesTextField(JTextField appearancesTextField) {
		this.appearancesTextField = appearancesTextField;
	}

	public void setGoalsTextField(JTextField goalsTextField) {
		this.goalsTextField = goalsTextField;
	}

	public void setAssistsTextField(JTextField assistsTextField) {
		this.assistsTextField = assistsTextField;
	}

	public void setInputFieldsPanel(JPanel inputFieldsPanel) {
		this.inputFieldsPanel = inputFieldsPanel;
	}

	public void setCreatePlayerButton(JButton createPlayerButton) {
		this.createPlayerButton = createPlayerButton;
	}

	public void setViewPlayersButton(JButton viewPlayersButton) {
		this.viewPlayersButton = viewPlayersButton;
	}

	public void setViewPlayerByIDButton(JButton viewPlayerByIDButton) {
		this.viewPlayerByIDButton = viewPlayerByIDButton;
	}

	public void setUpdatePlayerButton(JButton updatePlayerButton) {
		this.updatePlayerButton = updatePlayerButton;
	}

	public void setDeletePlayerByIDButton(JButton deletePlayerByIDButton) {
		this.deletePlayerByIDButton = deletePlayerByIDButton;
	}

	public void setCrudButtonsPanel(JPanel crudButtonsPanel) {
		this.crudButtonsPanel = crudButtonsPanel;
	}

	public static void main(String[] args) {
		PlayerGUI clientSide = new PlayerGUI();
		clientSide.gui_init();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == table)
		{
			System.out.println("Selected Row");
			System.out.println(table.getSelectedRow());
			
			playerIDTextField.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
			nameTextField.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
			ageTextField.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
			genderTextField.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
			nationalityTextField.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
			clubTextField.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
			appearancesTextField.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
			goalsTextField.setText(table.getValueAt(table.getSelectedRow(), 7).toString());
			assistsTextField.setText(table.getValueAt(table.getSelectedRow(), 8).toString());
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
