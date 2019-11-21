package game;

import java.util.ArrayList;

import agents.Agent;
import agents.AgentAction;
import agents.ColorAgent;
import factory.AgentFactory;
import factory.BombermanFactory;
import factory.EnnemyFactory;
import map.Map;

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
		agentList.add(agentfactory.creaListAgentsStartgentAction.MOVE_DOWN, 'B', ColorAgent.BLANC, false, false));
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
	
	public void moveAgent(Agent agent, AgentAction action)
	{
		int x = agent.getX();
		int y = agent.getY();
		
		switch (action) {
		//MOVE_UP,MOVE_DOWN,MOVE_LEFT,MOVE_RIGHT,STOP,PUT_BOMB
		case MOVE_UP:
			agent.setAgentAction(action);
			agent.setY(y-1);
			break;
			
		case MOVE_DOWN:
			agent.setAgentAction(action);
			agent.setY(y+1);
			break;

		case MOVE_LEFT:
			agent.setAgentAction(action);
			agent.setX(x-1);
			break;
			
		case MOVE_RIGHT:
			agent.setAgentAction(action);
			agent.setX(x+1);
			break;
			
		case STOP:
			agent.setAgentAction(action);
			break;
		}
	}

}
