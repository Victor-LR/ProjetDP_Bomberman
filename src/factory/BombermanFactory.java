package factory;

import agents.Agent;
import agents.AgentAction;
import agents.Agent_Bomberman;
import agents.ColorAgent;
import strategie.Comportement;
import strategie.Strategie_Aleatoire;
import strategie.Strategie_Joueur1;

public class BombermanFactory extends AgentFactory{

	public Agent createAgent(int x, int y, AgentAction agentAction, char type, ColorAgent color, boolean isInvincible,boolean isSick) {
		// TODO Auto-generated method stub
		return new Agent_Bomberman(x, y, agentAction, type, color, isInvincible, isSick,new Strategie_Joueur1());
	}

}
