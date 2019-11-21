package game;

import java.util.ArrayList;

import agents.Agent;
import agents.AgentAction;
import agents.ColorAgent;
import factory.AgentFactory;
import factory.BombermanFactory;
import factory.EnnemyFactory;

public class BombermanGame extends Game implements Observable {

    private ArrayList<Agent> agentList;
    private ArrayList<Agent> ListAgentsStart;




	public BombermanGame() {
		// TODO Auto-generated constructor stub
		super();
		agentList = new ArrayList<Agent>();
		ListAgentsStart = new ArrayList<Agent>();
	}

	@Override
	public boolean gameContinue() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void initializeGame() {
		// TODO Auto-generated method stub
		agentList = ListAgentsStart;
		
		
		/*EnnemyFactory agentfactory=new EnnemyFactory();
		BombermanFactory bombermanFactory=new BombermanFactory();
		agentList.add(agentfactory.createAgent(3, 3, AgentAction.MOVE_DOWN, 'R', ColorAgent.DEFAULT, false, false));
		agentList.add(bombermanFactory.createAgent(4, 4, AgentAction.MOVE_DOWN, 'B', ColorAgent.BLANC, false, false));
		System.out.println("Agents créé !");*/
	}

	public void setListAgentsStart(ArrayList<Agent> listAgentsStart) {
		ListAgentsStart = listAgentsStart;
	}
	
	@Override
	public void takeTurn() {
		// TODO Auto-generated method stub
		System.out.println("Tour "+this.turn+" en cours");
	}

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<Agent> getAgentList() {
		return agentList;
	}

}
