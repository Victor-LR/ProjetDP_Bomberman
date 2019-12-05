package agents;

import java.util.ArrayList;

import strategie.Comportement;
import view.ViewBombermanGame;

public class Agent_Bird extends Agent_Ennemi{

	public Agent_Bird(int x, int y, AgentAction agentAction, char type, ColorAgent color, boolean isInvincible,
			boolean isSick) {
		super(x, y, agentAction, type, color, isInvincible, isSick);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AgentAction doAction(ArrayList<Agent> agent, AgentAction action) {
		for(int i =0; i<agent.size(); i++) {
//			 Détecte un bomberman sur un rayon de 3 cases
			System.out.println(agent.get(i).getType() +"   "+agent.get(i).getX() + "    " + agent.get(i).getY());
			if(agent.get(i).getType()=='B' && (agent.get(i).getX()< this.getX()+3) && (agent.get(i).getX() > this.getX()-3) && (agent.get(i).getY() <= this.getY()+3) && (agent.get(i).getY() >= this.getY()-3) && ViewBombermanGame.isFlying(this, action))
			{
				return action;
			}
		}
		return action.STOP;
	}

}
