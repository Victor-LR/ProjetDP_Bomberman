package factory;

import agents.Agent;
import agents.AgentAction;
import agents.ColorAgent;

public interface AgentFactory {

	public Agent createAgent(int x, int y, AgentAction agentAction, char type, ColorAgent color, boolean isInvincible,boolean isSick);
}
