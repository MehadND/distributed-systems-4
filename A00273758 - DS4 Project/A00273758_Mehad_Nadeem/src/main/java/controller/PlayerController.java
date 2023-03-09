package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Player;
import view.PlayerGUI;

public class PlayerController extends Player implements ActionListener
{
	private PlayerGUI view;
	private ArrayList<Player> model;

	public PlayerController()
	{
		model = new ArrayList<>();

		view = new PlayerGUI();
		view.init();

		view.getAddButton().addActionListener(this);
		view.getDeleteButton().addActionListener(this);
		view.getDisplayButton().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub

	}
}
