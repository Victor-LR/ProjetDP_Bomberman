import controleur.ControleurBombermanGame;
import map.Map;
import perceptron.Reward;
import strategie.Strategie_Aleatoire;

public class Main_Reward {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ControleurBombermanGame Control_Percep = new ControleurBombermanGame(true);
		Reward R = new Reward();
		Map map = null;
		try {
			map = new Map("layouts/exemple.lay");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		R.getAverageReward(100, 100, new Strategie_Aleatoire(),map );
		//R.vizualize(100, new Strategie_Aleatoire(), map, 200);
	}
	

}
