package strategie;

import java.io.Serializable;
import java.util.ArrayList;

import agents.Agent;
import agents.AgentAction;
import game.BombermanGame;
import key.Keys;

public class Strategie_Joueur1 implements Comportement {
	
	public static Keys key_1 ;
	
	public Strategie_Joueur1 () {
		key_1 = BombermanGame.getKey_1();
	}
	
	@Override
	public AgentAction doAction(Agent agent, ArrayList<Agent> agents) {

		
		if (BombermanGame.isLegalMove(agent,key_1.getKaction())) {
			System.out.println(key_1.getKaction());
			return key_1.getKaction();
			
		}else
			return AgentAction.STOP;

	}

}
