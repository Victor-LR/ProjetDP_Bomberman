package agents;

import java.util.ArrayList;

import strategie.Comportement;

public class Agent_Bomberman extends Agent{

	private int range;
	private int nbBombes;
	
	public Agent_Bomberman(int x, int y, AgentAction agentAction, char type, ColorAgent color, boolean isInvincible,
			boolean isSick) {
		super(x, y, agentAction, type, color, isInvincible, isSick);
		this.range = 4;
		this.nbBombes = 1;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getNbBombes() {
		return nbBombes;
	}

	public void setNbBombes(int nbBombes) {
		this.nbBombes = nbBombes;
	}


}
