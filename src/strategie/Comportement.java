package strategie;

import java.util.ArrayList;

import agents.Agent;
import agents.AgentAction;

public interface Comportement {
	public AgentAction doAction(Agent agent, ArrayList<Agent> agents);

}
