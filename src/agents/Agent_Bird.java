package agents;

import java.util.ArrayList;

import game.BombermanGame;
import strategie.Comportement;


public class Agent_Bird extends Agent_Ennemi{

	public Agent_Bird(int x, int y, AgentAction agentAction, char type, ColorAgent color, boolean isInvincible,
			boolean isSick,Comportement strat) {
		super(x, y, agentAction, type, color, isInvincible, isSick,strat);
		// TODO Auto-generated constructor stub
	}

//	public AgentAction doAction(ArrayList<Agent> agent, AgentAction action) {
//		for(int i =0; i<agent.size(); i++) {
////			 Détecte un bomberman sur un rayon de 3 cases
//			System.out.println(agent.get(i).getType() +"   "+agent.get(i).getX() + "    " + agent.get(i).getY());
//			if(agent.get(i).getType()=='B' && (agent.get(i).getX()< this.getX()+3) && (agent.get(i).getX() > this.getX()-3) && (agent.get(i).getY() <= this.getY()+3) && (agent.get(i).getY() >= this.getY()-3) && BombermanGame.isFlying(this, action))
//			{
//				return action;
//			}
//		}
//		return AgentAction.STOP;
//	}

}
