import game.SimpleGame;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.JFrame;

import controleur.ControleurBombermanGame;
import controleur.ControllerSimpleGame;
import controleur.InterfaceController;
import game.BombermanGame;
import game.Game;
import view.ViewCommand;
import view.ViewSimpleGame;

public class Test {

	public static void main(String[] args) {
//		SimpleGame JeuSimple = new SimpleGame();
//		ControllerSimpleGame CSG = new ControllerSimpleGame(JeuSimple);
//		ViewCommand vue_command = new ViewCommand(JeuSimple);
//		ViewSimpleGame vue_jeu = new ViewSimpleGame(JeuSimple);
		

		BombermanGame JeuBomberman = new BombermanGame();
		ControleurBombermanGame CBG = new ControleurBombermanGame(JeuBomberman);
		

	}

}