package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import agents.Agent;
import agents.AgentAction;
import agents.InfoAgent;
import controleur.InterfaceController;
import game.BombermanGame;
import game.Observable;
import game.SimpleGame;
import map.Map;
import objects.InfoBomb;
import objects.InfoItem;

public class ViewBombermanGame implements Observer {
	
	
	private JFrame jframe_bbm = new JFrame();
	private PanelBomberman Plateau_jeu;
	private static Map map_jeu;
	private ArrayList<Agent> ListStartAgent;
	private InterfaceController controleur;

	public ViewBombermanGame(InterfaceController control, BombermanGame Jeu, String filename) {
		this.controleur = control;
		Jeu.registerObserver(this);
		try {
			map_jeu = new Map(filename);
			this.Plateau_jeu = new PanelBomberman(map_jeu);
			ListStartAgent = map_jeu.getStart_agents();
			Jeu.setListAgentsStart(ListStartAgent);
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
		//System.out.println(jeu_bbm.getAgentList().get(0).getType());
		this.Plateau_jeu.setInfoGame(map_jeu.getStart_brokable_walls(), jeu_bbm.getAgentList(), jeu_bbm.getBombes());
		this.Plateau_jeu.repaint();
		//this.turn.setText("Tour n° :"+ simple_jeu.getTurn());
	}
	
	public static boolean isLegalMove(Agent agent, AgentAction action) {
		boolean[][] list_wall = map_jeu.get_walls();
		boolean[][] list_breakable_wall = map_jeu.getStart_brokable_walls();
		
		switch (action) {
			case MOVE_DOWN:
				if (!list_wall[agent.getX()][agent.getY()+1] && !list_breakable_wall[agent.getX()][agent.getY()+1])
					return true;
				break;
			case MOVE_UP:
				if (!list_wall[agent.getX()][agent.getY()-1] && !list_breakable_wall[agent.getX()][agent.getY()-1])
					return true;
				break;
			case MOVE_RIGHT:
				if (!list_wall[agent.getX()+1][agent.getY()] && !list_breakable_wall[agent.getX()+1][agent.getY()])
					//System.out.println(!list_wall[agent.getX()+1][agent.getY()]);
					return true;
				break;
			case MOVE_LEFT:
				if (!list_wall[agent.getX()-1][agent.getY()] && !list_breakable_wall[agent.getX()-1][agent.getY()])
					return true;
				break;
			default:
				break;
		}
		return false;
	}
	
	public static boolean isFlying(Agent agent, AgentAction action) {
		boolean[][] list_wall = map_jeu.get_walls();
		
		switch (action) {
			case MOVE_DOWN:
				if (!list_wall[agent.getX()][agent.getY()+1])
					return true;
				break;
			case MOVE_UP:
				if (!list_wall[agent.getX()][agent.getY()-1])
					return true;
				break;
			case MOVE_RIGHT:
				if (!list_wall[agent.getX()+1][agent.getY()])
					//System.out.println(!list_wall[agent.getX()+1][agent.getY()]);
					return true;
				break;
			case MOVE_LEFT:
				if (!list_wall[agent.getX()-1][agent.getY()])
					return true;
				break;
			default:
				break;
		}
		return false;
	}
}
