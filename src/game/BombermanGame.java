package game;

public class BombermanGame extends Game implements Observable {

	public BombermanGame() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean gameContinue() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void initializeGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takeTurn() {
		// TODO Auto-generated method stub
		System.out.println("Tour "+this.turn+" en cours");
	}

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
		
	}

}
