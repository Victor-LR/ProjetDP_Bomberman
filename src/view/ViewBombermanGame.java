package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controleur.InterfaceController;
import game.BombermanGame;
import game.Observable;
import game.SimpleGame;
import map.Map;

public class ViewBombermanGame implements Observer {
	
	
	private JFrame jframe_bbm = new JFrame();
	private PanelBomberman Plateau_jeu;
	
	private InterfaceController controleur;

	public ViewBombermanGame(InterfaceController control, BombermanGame Jeu, String filename) {
		this.controleur = control;
		Jeu.registerObserver(this);
		Map map_jeu;
		try {
			map_jeu = new Map(filename);
			this.Plateau_jeu = new PanelBomberman(map_jeu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jframe_bbm.setTitle("Game");
		jframe_bbm.setSize(new Dimension(700, 500));
		Dimension windowSize = jframe_bbm.getSize();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();
		int dx = centerPoint.x - windowSize.width / 2 ;
		int dy = centerPoint.y - windowSize.height / 2 ;
		jframe_bbm.setLocation(dx, dy);
		jframe_bbm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		
		jframe_bbm.setLayout(new BorderLayout());
		
		jframe_bbm.add("Center",Plateau_jeu);
		jframe_bbm.setVisible(true);
		
	}

	@Override
	public void update(Observable obs) {
		BombermanGame jeu_bbm = (BombermanGame) obs;
		this.Plateau_jeu.repaint();
		//this.turn.setText("Tour n° :"+ simple_jeu.getTurn());
	}

}