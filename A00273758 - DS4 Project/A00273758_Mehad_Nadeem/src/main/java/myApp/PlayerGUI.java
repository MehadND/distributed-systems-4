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
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicBorders;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class PlayerGUI extends JFrame implements ActionListener
{
	private Container contentPane = this.getContentPane();
	
	String debugLine = "  > ";
	private JTextArea debuggerOutpuTextArea = new JTextArea(debugLine);
	
	private JLabel viewPlayersLabel = new JLabel("List of All Players");
	private JTextArea viewPlayersTextArea = new JTextArea(10,1);
	
	JPanel viewPanel = new JPanel();
	
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
	
	public PlayerGUI()
	{		
		this.setTitle("A00273758_Mehad_Nadeem - DS4 Project");
        this.setResizable(false);
//        this.setLayout(new BorderLayout());
        this.setLayout(new GridLayout(1,3));
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void gui_init()
	{
		
		crudButtonsPanel();
		tablePanel();
		inputFieldsPanel();		
		
		//contentPane.add(viewPanel, BorderLayout.EAST);
		//contentPane.add(inputFieldsPanel, BorderLayout.WEST);
		contentPane.add(inputFieldsPanel);
		contentPane.add(crudButtonsPanel);
		contentPane.add(viewPanel);
		
		this.setVisible(true);
	}

	public void crudButtonsPanel()
	{
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
		//crudButtonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		crudButtonsPanel.add(viewPlayerByIDButton);
		//crudButtonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		crudButtonsPanel.add(updatePlayerButton);
		//crudButtonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		crudButtonsPanel.add(deletePlayerByIDButton);
		
//		GridLayout crudPanelLayout = new GridLayout(5, 1);
		crudButtonsPanel.setLayout(new BoxLayout(crudButtonsPanel, BoxLayout.Y_AXIS)); 
		//crudButtonsPanel.setAlignmentY(CENTER_ALIGNMENT);
		
		//crudButtonsPanel.setBorder(new EmptyBorder(250, 100, 0, 0));
		
		crudButtonsPanel.setVisible(true);
	}
	
	public void tablePanel()
	{
		viewPlayersTextArea.setLineWrap(true);
				
		viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
		viewPanel.add(viewPlayersButton);
		viewPanel.add(viewPlayersLabel);
		viewPanel.add(viewPlayersTextArea);
		
		viewPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
	}
	
	public void inputFieldsPanel()
	{
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
		debuggerOutpuTextArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Debugger", TitledBorder.LEFT, TitledBorder.TOP));
		gridBagLayout.setConstraints(debuggerOutpuTextArea, constraints);
		inputFieldsPanel.add(debuggerOutpuTextArea);
		
		inputFieldsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		
		inputFieldsPanel.setVisible(true);
	}
	
	public void printAllPlayers()
	{
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;


		String text;
		try {
			URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080).setPath("/a00273758.mehadnadeem/rest/players/view/all").build();
			//System.out.println(uri.toString());
			
			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader("Accept", "text/plain");
			
			httpClient = HttpClients.createDefault();
			httpResponse = httpClient.execute(httpGet);
			
			
			HttpEntity entity = httpResponse.getEntity();
			
			text = EntityUtils.toString(entity);
			
			debuggerOutpuTextArea.setText(debugLine+"Getting All Players...");
			viewPlayersTextArea.setText(text);
			
		} catch (Exception ex) {
			viewPlayersTextArea.setText(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public void printPlayerByID(int id)
	{
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;

		
		try {
			URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080).setPath("/a00273758.mehadnadeem/rest/players/view/"+id).build();
			
			//System.out.println(uri.toString());
			
			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader("Accept", "text/plain");
			
			httpClient = HttpClients.createDefault();
			httpResponse = httpClient.execute(httpGet);
			
			String text;
			
			HttpEntity entity = httpResponse.getEntity();
			
			text = EntityUtils.toString(entity);
			
			debuggerOutpuTextArea.setText(debugLine+"Getting Player By ID #"+id+"...");
			viewPlayersTextArea.setText(text);
			
		} catch (Exception ex) {
			viewPlayersTextArea.setText(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public void deletePlayerByID(int id)
	{
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;

		
		try {
			URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080).setPath("/a00273758.mehadnadeem/rest/players/delete/"+id).build();
			
			//System.out.println(uri.toString());
			
			HttpDelete httpDelete = new HttpDelete(uri);
			httpDelete.setHeader("Accept", "text/plain");
			
			httpClient = HttpClients.createDefault();
			httpResponse = httpClient.execute(httpDelete);
			
			String text;
			
			HttpEntity entity = httpResponse.getEntity();
			
			text = EntityUtils.toString(entity);
			
			debuggerOutpuTextArea.setText(debugLine+"Deleting Player By ID #"+id+"...");
			viewPlayersTextArea.setText(text);
			
		} catch (Exception ex) {
			viewPlayersTextArea.setText(ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == viewPlayersButton)
		{
			printAllPlayers();						
		}
		if(e.getSource() == viewPlayerByIDButton)
		{
			if(playerIDTextField.getText() == null || playerIDTextField.getText().isBlank() || playerIDTextField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Please enter a Player ID!");
			}
			else
			{
				printPlayerByID(Integer.parseInt(playerIDTextField.getText()));				
			}
		}
		if(e.getSource() == deletePlayerByIDButton)
		{
			if(playerIDTextField.getText() == null || playerIDTextField.getText().isBlank() || playerIDTextField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Please enter a Player ID!");
			}
			else
			{
				deletePlayerByID(Integer.parseInt(playerIDTextField.getText()));				
			}
		}
		
	}
	
	public static void main(String[] args) {
		PlayerGUI clientSide = new PlayerGUI();
		clientSide.gui_init();
	}

}
