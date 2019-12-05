package game;

import java.util.ArrayList;

import agents.Agent;
import agents.AgentAction;
import agents.ColorAgent;
import factory.AgentFactory;
import factory.BombermanFactory;
import factory.EnnemyFactory;
import map.Map;
import view.ViewBombermanGame;

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
		agentList = new ArrayList<Agent>();
		EnnemyFactory ennemyFactory=new EnnemyFactory();
		BombermanFactory bombermanFactory=new BombermanFactory();
		for(Agent agent : ListAgentsStart)
		{
			if(agent.getType()=='B') {
				System.out.println("Bomb");
				agentList.add(bombermanFactory.createAgent(agent.getX(), agent.getY(), agent.getAgentAction(), 'B', agent.getColor(), agent.isInvincible(), agent.isSick()));
			}
			else {
				System.out.println("Enn");
				agentList.add(ennemyFactory.createAgent(agent.getX(), agent.getY(), agent.getAgentAction(), agent.getType(), agent.getColor(), agent.isInvincible(), agent.isSick()));
			}
		}
	}

	public void setListAgentsStart(ArrayList<Agent> listAgentsStart) {
		ListAgentsStart = listAgentsStart;
	}
	
	@Override
	public void takeTurn() {
		// TODO Auto-generated method stub
		System.out.println("Tour "+this.turn+" en cours");
		for(int i = 0; i < agentList.size(); i++) {
			
			Agent agent = agentList.get(i);
			//System.out.println(agent.getX()+"			"+agent.getY());
		
			AgentAction[] listaction = AgentAction.values();
			int action_random = (int) (Math.random()*listaction.length);
			
//			if(ViewBombermanGame.isLegalMove(agent,listaction[action_random])) {
				moveAgent(agent,listaction[action_random]);
//			}else {
//				moveAgent(agent,AgentAction.STOP);
//			}
		}
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
			if(agent.doAction(agentList, action)!=AgentAction.STOP)
				agent.setY(y-1);
			else
				agent.setAgentAction(AgentAction.STOP);
			break;
			
		case MOVE_DOWN:
			agent.setAgentAction(action);
			if(agent.doAction(agentList, action)!=AgentAction.STOP)
				agent.setY(y+1);
			else
				agent.setAgentAction(AgentAction.STOP);
			break;

		case MOVE_LEFT:
			agent.setAgentAction(action);
			if(agent.doAction(agentList, action)!=AgentAction.STOP)
				agent.setX(x-1);
			else
				agent.setAgentAction(AgentAction.STOP);
			break;
			
		case MOVE_RIGHT:
			agent.setAgentAction(action);
			if(agent.doAction(agentList, action)!=AgentAction.STOP)
				agent.setX(x+1);
			else
				agent.setAgentAction(AgentAction.STOP);
			break;
			
		case STOP:
		default:
			agent.setAgentAction(AgentAction.STOP);
			break;
		}
	}


}
