package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;

public class PlayerGUI extends JFrame
{
	//Menu type buttons at top
    JButton displayButton = new JButton("Display");
    JButton addButton = new JButton("Add");
    //JButton editButton = new JButton("Edit");
    JButton deleteButton = new JButton("Delete");

    //JPanels for menu type buttons
    JPanel menuButtonPanel = new JPanel();

    private Container contentPane = this.getContentPane();

    public PlayerGUI()
    {
    	this.setTitle("Mehad_Nadeem_Research");
        this.setResizable(false);
        this.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        this.setSize(900, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void init()
    {
    	menuButtonPanel.setLayout(new FlowLayout());
        menuButtonPanel.add(displayButton);
        menuButtonPanel.add(addButton);
        //menuButtonPanel.add(editButton);
        menuButtonPanel.add(deleteButton);
        
        contentPane.add(menuButtonPanel);
        
        this.setVisible(true);
    }

	public JButton getDisplayButton()
	{
		return displayButton;
	}

	public void setDisplayButton(JButton displayButton)
	{
		this.displayButton = displayButton;
	}

	public JButton getAddButton()
	{
		return addButton;
	}

	public void setAddButton(JButton addButton)
	{
		this.addButton = addButton;
	}

	public JButton getDeleteButton()
	{
		return deleteButton;
	}

	public void setDeleteButton(JButton deleteButton)
	{
		this.deleteButton = deleteButton;
	}

	public JPanel getMenuButtonPanel()
	{
		return menuButtonPanel;
	}

	public void setMenuButtonPanel(JPanel menuButtonPanel)
	{
		this.menuButtonPanel = menuButtonPanel;
	}

    
}