package player_rest;

import java.awt.BorderLayout;
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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class PlayerView extends JFrame {

	// ----------ALL PANELS----------\\
	private JPanel inputFieldsPanel = new JPanel();
	private JPanel crudButtonsPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private JPanel debuggerPanel = new JPanel();

	// ----------PLAYER'S LABEL + INPUTFIELDS----------\\
	private JLabel playerIDLabel = new JLabel("Player ID: ");
	private JLabel playerNameLabel = new JLabel("Player Name: ");
	private JLabel playerAgeLabel = new JLabel("Player Age: ");
	private JLabel playerGenderLabel = new JLabel("Player Gender: ");
	private JLabel playerNationalityLabel = new JLabel("Player Nationality: ");
	private JLabel playerClubLabel = new JLabel("Player Club: ");
	private JLabel playerAppearancesLabel = new JLabel("Player Appearances: ");
	private JLabel playerGoalsLabel = new JLabel("Player Goals: ");
	private JLabel playerAssistsLabel = new JLabel("Player Assists: ");

	private JTextField playerIDTextField = new JTextField(25);
	private JTextField playerNameTextField = new JTextField(25);
	private JTextField playerAgeTextField = new JTextField(25);
	private JTextField playerGenderTextField = new JTextField(25);
	private JTextField playerNationalityTextField = new JTextField(25);
	private JTextField playerClubTextField = new JTextField(25);
	private JTextField playerAppearancesTextField = new JTextField(25);
	private JTextField playerGoalsTextField = new JTextField(25);
	private JTextField playerAssistsTextField = new JTextField(25);

	// ----------CRUD BUTTONS----------\\
	private JButton createPlayerButton = new JButton("Create Player");
	private JButton viewPlayerButton = new JButton("View All Players");
	private JButton viewPlayerByIDButton = new JButton("View Player By ID");
	private JButton updatePlayerButton = new JButton("Update Player");
	private JButton deletePlayerByIDButton = new JButton("Delete Player By ID");
	private JButton clearInputFieldsButton = new JButton("Clear All");

	// ----------DEBUGGER----------\\
	String debugStarterLine = "  > Hello World!";
	private JTextArea debuggerConsoleArea = new JTextArea(debugStarterLine);
	private JScrollPane scroll;

	// ----------VIEW TEXTAREA (viewPrinter)----------\\
	// private JTextArea viewArea = new JTextArea();

	// ----------DB TABLE----------\\
	private String[] tableColumns = { "ID", "Name", "Age", "Gender", "Nationality", "Club", "App", "Goals", "Assists" };
	private DefaultTableModel tableModel = new DefaultTableModel(tableColumns, 0);

	JTable dbTable = null;
	JScrollPane scrollPane = null;

	// ----------CONTENT PANE----------\\
	private Container contentPane = this.getContentPane();
	private GridBagLayout mainLayout = new GridBagLayout();
	private GridBagConstraints mainLayoutConstraints = new GridBagConstraints();

	public PlayerView() {
		// initialize + basic setup
		this.setTitle("A00273758_Mehad_Nadeem - DS4 Project");
		this.setResizable(false);
		this.setLayout(mainLayout);
		this.setSize(1200, 900);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void init() {
		PlayerDAO.instance.startDBConnection();

		dbTable = new JTable(tableModel);
		scrollPane = new JScrollPane(dbTable);
		
		refreshDBTable();

		// full UI setup
		inputsPanelSetup();
		crudButtonsPanelSetup();
		dbPanelSetup();
		debuggerPanelSetup();

		// setup contentPane UI
		mainLayoutConstraints.fill = GridBagConstraints.HORIZONTAL;
		// int y = 0;

		mainLayoutConstraints.gridx = 0;
		mainLayoutConstraints.gridy = 0;
		mainLayout.setConstraints(crudButtonsPanel, mainLayoutConstraints);
		contentPane.add(crudButtonsPanel);

		// y++;

		mainLayoutConstraints.gridx = 0;
		mainLayoutConstraints.gridy = 1;
		mainLayout.setConstraints(inputFieldsPanel, mainLayoutConstraints);
		contentPane.add(inputFieldsPanel);

		mainLayoutConstraints.gridx = 1;
		mainLayoutConstraints.gridy = 1;
		mainLayout.setConstraints(debuggerPanel , mainLayoutConstraints);
		contentPane.add(debuggerPanel );

		// y++;

		mainLayoutConstraints.gridx = 0;
		mainLayoutConstraints.gridy = 2;
		mainLayoutConstraints.gridwidth = 2;
		mainLayoutConstraints.insets = new Insets(20, 0, 0, 0);
		mainLayout.setConstraints(bottomPanel, mainLayoutConstraints);
		contentPane.add(bottomPanel);

		this.setVisible(true);
	}

	public void inputsPanelSetup() {
		// setup input fields and their corresponding labels
		BoxLayout inputsPanelLayout = new BoxLayout(inputFieldsPanel, BoxLayout.Y_AXIS);
		// inputFieldsPanel.setLayout(inputsPanelLayout);

		GridBagLayout layout = new GridBagLayout();
		inputFieldsPanel.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 10;

		int y = 0;

		gbc.gridx = 0;
		gbc.gridy = y;
		gbc.insets = new Insets(30, 0, 10, 0);
		layout.setConstraints(playerIDLabel, gbc);
		inputFieldsPanel.add(playerIDLabel);

		gbc.gridx = 1;
		gbc.gridy = y;
		layout.setConstraints(playerIDTextField, gbc);
		inputFieldsPanel.add(playerIDTextField);

		gbc.insets = new Insets(0, 0, 10, 0);
		y++;

		gbc.gridx = 0;
		gbc.gridy = y;
		layout.setConstraints(playerNameLabel, gbc);
		inputFieldsPanel.add(playerNameLabel);

		gbc.gridx = 1;
		gbc.gridy = y;
		layout.setConstraints(playerNameTextField, gbc);
		inputFieldsPanel.add(playerNameTextField);

		y++;

		gbc.gridx = 0;
		gbc.gridy = y;
		layout.setConstraints(playerAgeLabel, gbc);
		inputFieldsPanel.add(playerAgeLabel);

		gbc.gridx = 1;
		gbc.gridy = y;
		layout.setConstraints(playerAgeTextField, gbc);
		inputFieldsPanel.add(playerAgeTextField);

		y++;

		gbc.gridx = 0;
		gbc.gridy = y;
		layout.setConstraints(playerGenderLabel, gbc);
		inputFieldsPanel.add(playerGenderLabel);

		gbc.gridx = 1;
		gbc.gridy = y;
		layout.setConstraints(playerGenderTextField, gbc);
		inputFieldsPanel.add(playerGenderTextField);

		y++;

		gbc.gridx = 0;
		gbc.gridy = y;
		layout.setConstraints(playerNationalityLabel, gbc);
		inputFieldsPanel.add(playerNationalityLabel);

		gbc.gridx = 1;
		gbc.gridy = y;
		layout.setConstraints(playerNationalityTextField, gbc);
		inputFieldsPanel.add(playerNationalityTextField);

		y++;

		gbc.gridx = 0;
		gbc.gridy = y;
		layout.setConstraints(playerClubLabel, gbc);
		inputFieldsPanel.add(playerClubLabel);

		gbc.gridx = 1;
		gbc.gridy = y;
		layout.setConstraints(playerClubTextField, gbc);
		inputFieldsPanel.add(playerClubTextField);

		y++;

		gbc.gridx = 0;
		gbc.gridy = y;
		layout.setConstraints(playerAppearancesLabel, gbc);
		inputFieldsPanel.add(playerAppearancesLabel);

		gbc.gridx = 1;
		gbc.gridy = y;
		layout.setConstraints(playerAppearancesTextField, gbc);
		inputFieldsPanel.add(playerAppearancesTextField);

		y++;

		gbc.gridx = 0;
		gbc.gridy = y;
		layout.setConstraints(playerGoalsLabel, gbc);
		inputFieldsPanel.add(playerGoalsLabel);

		gbc.gridx = 1;
		gbc.gridy = y;
		layout.setConstraints(playerGoalsTextField, gbc);
		inputFieldsPanel.add(playerGoalsTextField);

		y++;

		gbc.gridx = 0;
		gbc.gridy = y;
		layout.setConstraints(playerAssistsLabel, gbc);
		inputFieldsPanel.add(playerAssistsLabel);

		gbc.gridx = 1;
		gbc.gridy = y;
		layout.setConstraints(playerAssistsTextField, gbc);
		inputFieldsPanel.add(playerAssistsTextField);

//		inputFieldsPanel.add(playerIDLabel);
//		inputFieldsPanel.add(playerIDTextField);
//		inputFieldsPanel.add(playerNameLabel);
//		inputFieldsPanel.add(playerNameTextField);
//		inputFieldsPanel.add(playerAgeLabel);
//		inputFieldsPanel.add(playerAgeTextField);
//		inputFieldsPanel.add(playerGenderLabel);
//		inputFieldsPanel.add(playerGenderTextField);
//		inputFieldsPanel.add(playerNationalityLabel);
//		inputFieldsPanel.add(playerNationalityTextField);
//		inputFieldsPanel.add(playerClubLabel);
//		inputFieldsPanel.add(playerClubTextField);
//		inputFieldsPanel.add(playerAppearancesLabel);
//		inputFieldsPanel.add(playerAppearancesTextField);
//		inputFieldsPanel.add(playerGoalsLabel);
//		inputFieldsPanel.add(playerGoalsTextField);
//		inputFieldsPanel.add(playerAssistsLabel);
//		inputFieldsPanel.add(playerAssistsTextField);

		// inputFieldsPanel.setLayout(new GridLayout(9, 2, 0, 10));
	}

	public void crudButtonsPanelSetup() {
		// setup crudButtons
		crudButtonsPanel.add(createPlayerButton);
		crudButtonsPanel.add(viewPlayerButton);
		crudButtonsPanel.add(viewPlayerByIDButton);
		crudButtonsPanel.add(updatePlayerButton);
		crudButtonsPanel.add(deletePlayerByIDButton);
		crudButtonsPanel.add(clearInputFieldsButton);
		
		crudButtonsPanel.setLayout(new GridLayout(2, 3, 10, 10));
	}

	public void debuggerPanelSetup() {
		// setup debugger
		debuggerConsoleArea.setAlignmentY(CENTER_ALIGNMENT);
		debuggerConsoleArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Debugger",
				TitledBorder.LEFT, TitledBorder.TOP));
		debuggerConsoleArea.setEditable(false);
		debuggerConsoleArea.setLineWrap(true);
		
		scroll = new JScrollPane (debuggerConsoleArea, 
				   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		scroll.setPreferredSize(new Dimension(400, 200));
		
		debuggerPanel.add(scroll);
	}

	public void viewPrinterPanelSetup() {
		// setup text area for the view functionality
		// viewAreaPanel.add(viewArea);
	}

	public void dbPanelSetup() {
		// setup DB panel
		scrollPane.setPreferredSize(new Dimension(400, 250));
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.add(scrollPane);
		// dbPanel.add(viewArea);
	}

	public void refreshDBTable() {
		// refresh the DB table
		tableModel.setRowCount(0);

		PlayerDAO.instance.fetchTable(tableModel);
	}

	public void clearInputFields()
	{
		playerIDTextField.setText("");
		playerNameTextField.setText("");
		playerAgeTextField.setText("");
		playerGenderTextField.setText("");
		playerNationalityTextField.setText("");
		playerClubTextField.setText("");
		playerAppearancesTextField.setText("");
		playerGoalsTextField.setText("");
		playerAssistsTextField.setText("");
	}
	
	// ----------GETTERS + SETTERS----------\\

	public JPanel getInputFieldsPanel() {
		return inputFieldsPanel;
	}

	public JPanel getCrudButtonsPanel() {
		return crudButtonsPanel;
	}

	public JPanel getDbPanel() {
		return debuggerPanel ;
	}

	public JLabel getPlayerIDLabel() {
		return playerIDLabel;
	}

	public JLabel getPlayerNameLabel() {
		return playerNameLabel;
	}

	public JLabel getPlayerAgeLabel() {
		return playerAgeLabel;
	}

	public JLabel getPlayerGenderLabel() {
		return playerGenderLabel;
	}

	public JLabel getPlayerNationalityLabel() {
		return playerNationalityLabel;
	}

	public JLabel getPlayerClubLabel() {
		return playerClubLabel;
	}

	public JLabel getPlayerAppearancesLabel() {
		return playerAppearancesLabel;
	}

	public JLabel getPlayerGoalsLabel() {
		return playerGoalsLabel;
	}

	public JLabel getPlayerAssistsLabel() {
		return playerAssistsLabel;
	}

	public JTextField getPlayerIDTextField() {
		return playerIDTextField;
	}

	public JTextField getPlayerNameTextField() {
		return playerNameTextField;
	}

	public JTextField getPlayerAgeTextField() {
		return playerAgeTextField;
	}

	public JTextField getPlayerGenderTextField() {
		return playerGenderTextField;
	}

	public JTextField getPlayerNationalityTextField() {
		return playerNationalityTextField;
	}

	public JTextField getPlayerClubTextField() {
		return playerClubTextField;
	}

	public JTextField getPlayerAppearancesTextField() {
		return playerAppearancesTextField;
	}

	public JTextField getPlayerGoalsTextField() {
		return playerGoalsTextField;
	}

	public JTextField getPlayerAssistsTextField() {
		return playerAssistsTextField;
	}

	public JButton getCreatePlayerButton() {
		return createPlayerButton;
	}

	public JButton getViewPlayerButton() {
		return viewPlayerButton;
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

	public String getDebugStarterLine() {
		return debugStarterLine;
	}

	public JTextArea getDebuggerConsoleArea() {
		return debuggerConsoleArea;
	}

	// public JTextArea getViewArea() {
	// return viewArea;
	// }

	public String[] getTableColumns() {
		return tableColumns;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public JTable getDbTable() {
		return dbTable;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public GridBagLayout getMainLayout() {
		return mainLayout;
	}

	public GridBagConstraints getMainLayoutConstraints() {
		return mainLayoutConstraints;
	}

	public void setInputFieldsPanel(JPanel inputFieldsPanel) {
		this.inputFieldsPanel = inputFieldsPanel;
	}

	public void setCrudButtonsPanel(JPanel crudButtonsPanel) {
		this.crudButtonsPanel = crudButtonsPanel;
	}

	public void setDbPanel(JPanel dbPanel) {
		this.debuggerPanel  = dbPanel;
	}

	public void setPlayerIDLabel(JLabel playerIDLabel) {
		this.playerIDLabel = playerIDLabel;
	}

	public void setPlayerNameLabel(JLabel playerNameLabel) {
		this.playerNameLabel = playerNameLabel;
	}

	public void setPlayerAgeLabel(JLabel playerAgeLabel) {
		this.playerAgeLabel = playerAgeLabel;
	}

	public void setPlayerGenderLabel(JLabel playerGenderLabel) {
		this.playerGenderLabel = playerGenderLabel;
	}

	public void setPlayerNationalityLabel(JLabel playerNationalityLabel) {
		this.playerNationalityLabel = playerNationalityLabel;
	}

	public void setPlayerClubLabel(JLabel playerClubLabel) {
		this.playerClubLabel = playerClubLabel;
	}

	public void setPlayerAppearancesLabel(JLabel playerAppearancesLabel) {
		this.playerAppearancesLabel = playerAppearancesLabel;
	}

	public void setPlayerGoalsLabel(JLabel playerGoalsLabel) {
		this.playerGoalsLabel = playerGoalsLabel;
	}

	public void setPlayerAssistsLabel(JLabel playerAssistsLabel) {
		this.playerAssistsLabel = playerAssistsLabel;
	}

	public void setPlayerIDTextField(JTextField playerIDTextField) {
		this.playerIDTextField = playerIDTextField;
	}

	public void setPlayerNameTextField(JTextField playerNameTextField) {
		this.playerNameTextField = playerNameTextField;
	}

	public void setPlayerAgeTextField(JTextField playerAgeTextField) {
		this.playerAgeTextField = playerAgeTextField;
	}

	public void setPlayerGenderTextField(JTextField playerGenderTextField) {
		this.playerGenderTextField = playerGenderTextField;
	}

	public void setPlayerNationalityTextField(JTextField playerNationalityTextField) {
		this.playerNationalityTextField = playerNationalityTextField;
	}

	public void setPlayerClubTextField(JTextField playerClubTextField) {
		this.playerClubTextField = playerClubTextField;
	}

	public void setPlayerAppearancesTextField(JTextField playerAppearancesTextField) {
		this.playerAppearancesTextField = playerAppearancesTextField;
	}

	public void setPlayerGoalsTextField(JTextField playerGoalsTextField) {
		this.playerGoalsTextField = playerGoalsTextField;
	}

	public void setPlayerAssistsTextField(JTextField playerAssistsTextField) {
		this.playerAssistsTextField = playerAssistsTextField;
	}

	public void setCreatePlayerButton(JButton createPlayerButton) {
		this.createPlayerButton = createPlayerButton;
	}

	public void setViewPlayerButton(JButton viewPlayerButton) {
		this.viewPlayerButton = viewPlayerButton;
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

	public void setDebugStarterLine(String debugStarterLine) {
		this.debugStarterLine = debugStarterLine;
	}

	public void setDebuggerConsoleArea(JTextArea debuggerConsoleArea) {
		this.debuggerConsoleArea = debuggerConsoleArea;
	}

	// public void setViewArea(JTextArea viewArea) {
	// this.viewArea = viewArea;
	// }

	public void setTableColumns(String[] tableColumns) {
		this.tableColumns = tableColumns;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public void setDbTable(JTable dbTable) {
		this.dbTable = dbTable;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public void setMainLayout(GridBagLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public void setMainLayoutConstraints(GridBagConstraints mainLayoutConstraints) {
		this.mainLayoutConstraints = mainLayoutConstraints;
	}

	public JPanel getBottomPanel() {
		return bottomPanel;
	}

	public JButton getClearInputFieldsButton() {
		return clearInputFieldsButton;
	}

	public void setBottomPanel(JPanel bottomPanel) {
		this.bottomPanel = bottomPanel;
	}

	public void setClearInputFieldsButton(JButton clearInputFieldsButton) {
		this.clearInputFieldsButton = clearInputFieldsButton;
	}

}
