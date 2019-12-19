package perceptron;

import java.util.ArrayList;

import agents.Agent;
import agents.AgentAction;
import controleur.ControleurBombermanGame;
import game.BombermanGame;
import perceptron.SparseVector;
import map.Map;
import strategie.Comportement;

public class Reward {
	
	public void getAverageReward(int nb_simulations, int max_turn, Comportement strategie, Map map ) {
		
		ArrayList<ControleurBombermanGame> List_Control = new ArrayList<ControleurBombermanGame>();
		
		for (int i = 0 ; i < nb_simulations ; i++){
			ControleurBombermanGame Control_Percep = new ControleurBombermanGame(true);
			Control_Percep.setMap(map);
			Control_Percep.getJeu_bomberman().setListAgentsStart(Control_Percep.getMap().getStart_agents());
			ArrayList<String> nom_strats = new ArrayList();
			nom_strats.add("Aléatoire");
			Control_Percep.getJeu_bomberman().setNom_strats(nom_strats);
			Control_Percep.run();
			
			
			List_Control.add(Control_Percep);
		}
		
		for(int j = 0 ; j < List_Control.size(); j++){
			try {
				List_Control.get(j).getJeu_bomberman().getThread().join();
				System.out.println("	Attente Thread n°"+j);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("erreur !");
			}
			
			System.out.println(" Fin des Thread");
		}
		getVector(List_Control.get(0).getJeu_bomberman(),List_Control.get(0).getJeu_bomberman().getAgentList().get(0));

	}
	
	
	public void vizualize(int max_turn, Comportement strategie, Map map, int time_wait) {
		
	}
	
	public SparseVector getVector(BombermanGame Jeu_bbm , Agent agent)
	{
		SparseVector vec1 = new SparseVector(75*5+1);
		
		//Première position du SparceVector à 1
		vec1.setValue(0, 1);
		
		//position agent (bomberman) 
		int x = agent.getX();
		int y = agent.getY();
		
		int ind_actions = 0;
		
		
		//Positionne les informations dans le Sparcevector		
		for(int k = 0 ; k <5 ; k++) {
			
			
			for(int i = x-2; i < x+2 ; i++) {
				for(int j = y-2; j < y+2 ; j++) {
	
					//vec1.setValue(j+1*i+1, 1);
					
					if(Jeu_bbm.Isennemie(i, j))
						vec1.setValue(ind_actions+j+1*i+1, 1);
					
					if(Jeu_bbm.IsBombe(i, j))
						vec1.setValue(ind_actions+25+j+1*i+1, 1);
					
					if(Jeu_bbm.IsMur(i, j))
						vec1.setValue(ind_actions+50+j+1*i+1, 1);
					
				}
			}
			
			ind_actions+=75;
		
		}
		
		System.out.println(vec1.toString());
		return(vec1);
	}
}
