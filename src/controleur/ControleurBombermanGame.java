package controleur;

import game.BombermanGame;
import view.ViewBombermanGame;
import view.ViewCommand;

public class ControleurBombermanGame implements InterfaceController {

	private BombermanGame Jeu_bomberman;
	
	public ControleurBombermanGame(BombermanGame Jeu) {
		
		ViewCommand vue_commande = new ViewCommand(this,Jeu);
		ViewBombermanGame vue_jeu = new ViewBombermanGame(this,Jeu,"layouts/exemple.lay");
		this.Jeu_bomberman = Jeu;
	}

	@Override
	public void step() {
		this.Jeu_bomberman.step();		
	}

	@Override
	public void start() {
		if (this.Jeu_bomberman.getAgentList().size() == 0) 
			this.Jeu_bomberman.init();
		this.Jeu_bomberman.launch();
	}

	@Override
	public void run() {
		this.Jeu_bomberman.init();
		this.Jeu_bomberman.launch();
	}

	@Override
	public void stop() {
		this.Jeu_bomberman.stop();

	}

	@Override
	public void setTime(double time) {
		// TODO Auto-generated method stub

	}

}
