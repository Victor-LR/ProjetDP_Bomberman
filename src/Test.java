import game.SimpleGame;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.JFrame;

import controleur.ControllerSimpleGame;
import controleur.InterfaceController;
import game.Game;
import view.ViewCommand;
import view.ViewSimpleGame;

public class Test {

	public static void main(String[] args) {
		SimpleGame JeuSimple = new SimpleGame();
		ControllerSimpleGame CSG = new ControllerSimpleGame(JeuSimple);
		//ViewCommand vue_command = new ViewCommand(JeuSimple);
		//ViewSimpleGame vue_jeu = new ViewSimpleGame(JeuSimple);
		
		//JeuSimple.init();
		//JeuSimple.run();
		

	}

}
