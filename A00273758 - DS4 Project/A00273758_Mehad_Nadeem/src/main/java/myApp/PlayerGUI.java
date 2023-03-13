package myApp;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class PlayerGUI extends JFrame implements ActionListener
{
	private Container contentPane = this.getContentPane();
	
	private JButton viewPlayersButton = new JButton("View Players");
	private JLabel viewPlayersLabel = new JLabel("List of All Players");
	private JTextArea viewPlayersTextArea = new JTextArea();
	private JTextField viewPlayersTextField = new JTextField();
	
	JPanel viewPanel = new JPanel();
	
	public PlayerGUI()
	{		
		this.setTitle("A00273758_Mehad_Nadeem - DS4 Project");
        this.setResizable(false);
        this.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void gui_init()
	{
		viewPlayersButton.addActionListener(this);
		
		viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
		viewPanel.add(viewPlayersButton);
		viewPanel.add(viewPlayersTextField);
		viewPanel.add(viewPlayersLabel);
		viewPanel.add(viewPlayersTextArea);
		
		contentPane.add(viewPanel);
		this.setVisible(true);
	}
	
	public void printAllPlayers() throws Exception
	{
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;

		URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080).setPath("/a00273758.mehadnadeem/rest/players").build();
		
		System.out.println(uri.toString());
		
		HttpGet httpGet = new HttpGet(uri);
		httpGet.setHeader("Accept", "text/plain");
		
		httpClient = HttpClients.createDefault();
		httpResponse = httpClient.execute(httpGet);
		
		String text;
		
		HttpEntity entity = httpResponse.getEntity();
		
		text = EntityUtils.toString(entity);
		//System.out.println(text);
		viewPlayersTextArea.setText(text);
	}
	
	public void printPlayerByID(int id) throws Exception
	{
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;

		URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080).setPath("/a00273758.mehadnadeem/rest/players/"+id).build();
		
		System.out.println(uri.toString());
		
		HttpGet httpGet = new HttpGet(uri);
		httpGet.setHeader("Accept", "text/plain");
		
		httpClient = HttpClients.createDefault();
		httpResponse = httpClient.execute(httpGet);
		
		String text;
		
		HttpEntity entity = httpResponse.getEntity();
		
		text = EntityUtils.toString(entity);
		//System.out.println(text);
		viewPlayersTextArea.setText(text);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == viewPlayersButton)
		{
			if(viewPlayersTextField.getText().isBlank() || viewPlayersTextField.getText().isEmpty())
			{
				try {
					printAllPlayers();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else 
			{
				try {
					printPlayerByID(Integer.parseInt(viewPlayersTextField.getText()));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
						
		}
		
	}
	
	public static void main(String[] args) {
		PlayerGUI clientSide = new PlayerGUI();
		clientSide.gui_init();
	}

}
