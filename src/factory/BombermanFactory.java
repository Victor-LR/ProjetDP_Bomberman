package factory;

import agents.Agent;
import agents.AgentAction;
import agents.Agent_Bomberman;
import agents.ColorAgent;

public class BombermanFactory implements AgentFactory{

	public Agent createAgent(int x, int y, AgentAction agentAction, char type, ColorAgent color, boolean isInvincible,boolean isSick) {
		// TODO Auto-generated method stub
		return new Agent_Bomberman(x, y, agentAction, type, color, isInvincible, isSick);
	}

}
