package factory;

import agents.Agent;
import agents.AgentAction;
import agents.ColorAgent;

public abstract class AgentFactory {

	public abstract Agent createAgent(int x, int y, AgentAction agentAction, char type, ColorAgent color, boolean isInvincible,boolean isSick);
}
