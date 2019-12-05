package game;

import java.util.ArrayList;

import agents.Agent;
import agents.AgentAction;
import agents.Agent_Bird;
import agents.Agent_Bomberman;
import agents.Agent_Rajion;
import agents.ColorAgent;
import factory.AgentFactory;
import factory.BombermanFactory;
import factory.EnnemyFactory;
import map.Map;
import objects.InfoBomb;
import objects.StateBomb;
import view.ViewBombermanGame;

public class BombermanGame extends Game implements Observable {

    private ArrayList<Agent> agentList;
    private ArrayList<Agent> ListAgentsStart;
    private ArrayList<InfoBomb> bombes;
    
    public BombermanGame() {
		agentList = new ArrayList<Agent>();
		ListAgentsStart = new ArrayList<Agent>();
		bombes = new ArrayList<InfoBomb>();
    }

	@Override
	public boolean gameContinue() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void initializeGame() {
		// TODO Auto-generated method stub
		agentList = new ArrayList<Agent>();
		EnnemyFactory ennemyFactory=new EnnemyFactory();
		BombermanFactory bombermanFactory=new BombermanFactory();
		
		for(Agent agent : ListAgentsStart)
		{
			if(agent.getType()=='B') {
				System.out.println("Bomb");
				agentList.add(bombermanFactory.createAgent(agent.getX(), agent.getY(), agent.getAgentAction(), 'B', agent.getColor(), agent.isInvincible(), agent.isSick()));
			}
			else {
				System.out.println("Enn");
				agentList.add(ennemyFactory.createAgent(agent.getX(), agent.getY(), agent.getAgentAction(), agent.getType(), agent.getColor(), agent.isInvincible(), agent.isSick()));
			}
		}
	}

	public void setListAgentsStart(ArrayList<Agent> listAgentsStart) {
		ListAgentsStart = listAgentsStart;
	}
	
	@Override
	public void takeTurn() {
		// TODO Auto-generated method stub
		System.out.println("Tour "+this.turn+" en cours");
		for(int i = 0; i < agentList.size(); i++) {
			
			Agent agent = agentList.get(i);
			System.out.println(agent.getX()+"			"+agent.getY());
		
			AgentAction[] listaction = AgentAction.values();
			int action_random = (int) (Math.random()*listaction.length);
			
			if(ViewBombermanGame.isLegalMove(agent,listaction[action_random])) {
				moveAgent(agent,listaction[action_random]);
				if (AgentAction.PUT_BOMB == AgentAction.PUT_BOMB && agent.getType()=='B')
					placeBomb((Agent_Bomberman)agent);
			}else {
				moveAgent(agent,AgentAction.STOP);
			}
			
		}
		bombeTurn();
	}

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<Agent> getAgentList() {
		return agentList;
	}
	
	//Réalise le déplacement d'un agent
	public void moveAgent(Agent agent, AgentAction action)
	{
		int x = agent.getX();
		int y = agent.getY();
		
		switch (action) {
		//MOVE_UP,MOVE_DOWN,MOVE_LEFT,MOVE_RIGHT,STOP,PUT_BOMB
		case MOVE_UP:
			agent.setAgentAction(action);
			agent.setY(y-1);
			break;
			
		case MOVE_DOWN:
			agent.setAgentAction(action);
			agent.setY(y+1);
			break;

		case MOVE_LEFT:
			agent.setAgentAction(action);
			agent.setX(x-1);
			break;
			
		case MOVE_RIGHT:
			agent.setAgentAction(action);
			agent.setX(x+1);
			break;
			
		case STOP:
		default:
			agent.setAgentAction(AgentAction.STOP);
			break;
		}
	}
	
	public void bombExplode(InfoBomb bomb)
	{
		int x = bomb.getX();
		int y = bomb.getY();
		
		ArrayList<Agent> agents = this.agentList;
		
		// TEST RANGE EAST
		
		for(int i = x; i<= bomb.getRange(); i++){
			
			for(int j = 0; j< agents.size(); j++){
				Agent agent = agents.get(j);
				if(agent.getX() == i && agent.getY() == y){
					agents.remove(j);
					}
				}
				
			for(int j = 0; j<bombes.size(); j++){
				InfoBomb bombe = bombes.get(j);
				if(bomb != bombe)
				if(bombe.getX() == i & bombe.getY() == y){
					bombe.setStateBomb(StateBomb.Boom);
					}
				}
				
		}
		
		// TEST RANGE SOUTH
		
		for(int i = y; i<= bomb.getRange(); i++){
			
			for(int j = 0; j< agents.size(); j++){
				Agent agent = agents.get(j);
				if(agent.getX() == x && agent.getY() == i){
					agents.remove(j);
					}
				}
				
			for(int j = 0; j<bombes.size(); j++){
				InfoBomb bombe = bombes.get(j);
				if(bomb != bombe)
				if(bombe.getX() == x & bombe.getY() == i){
					bombe.setStateBomb(StateBomb.Boom);
					}
				}
				
		}
		
		// TEST RANGE WEST
		
		for(int i = x; i>= bomb.getRange(); i--){
			
			for(int j = 0; j< agents.size(); j++){
				Agent agent = agents.get(j);
				if(agent.getX() == i && agent.getY() == y){
					agents.remove(j);
					}
				}
				
			for(int j = 0; j<bombes.size(); j++){
				InfoBomb bombe = bombes.get(j);
				if(bomb != bombe)
				if(bombe.getX() == i & bombe.getY() == y){
					bombe.setStateBomb(StateBomb.Boom);
					}
				}
				
		}
		
		
		// TEST RANGE NORTH
		for(int i = y; i>= bomb.getRange(); i--){
			
			for(int j = 0; j< agents.size(); j++){
				Agent agent = agents.get(j);
				if(agent.getX() == x && agent.getY() == i){
					agents.remove(j);
					}
				}
				
			for(int j = 0; j<bombes.size(); j++){
				InfoBomb bombe = bombes.get(j);
				if(bomb != bombe)
				if(bombe.getX() == x & bombe.getY() == i){
					bombe.setStateBomb(StateBomb.Boom);
					}
				}
				
		}

		
	}
	//Réalise le tour d'une bombe
	public void bombeTurn(){
		
		for(int i = 0; i < bombes.size(); i++){

			InfoBomb bombe = bombes.get(i);			
				StateBomb etat_bombe = bombe.getStateBomb();
				
				switch(bombe.getStateBomb()) {
				case Step1:
					bombe.setStateBomb(StateBomb.Step2);
					break;
					
				case Step2:
					bombe.setStateBomb(StateBomb.Step3);
					break;
					
				case Step3:
					bombe.setStateBomb(StateBomb.Boom);
					break;
					
				case Boom:
					bombExplode(bombe);
					bombes.remove(bombe);
					break;
				}
		}
	}
	
	//place une bombe
	public void placeBomb(Agent_Bomberman bomberman)
	{
		int x = bomberman.getX();
		int y = bomberman.getY();
		
		InfoBomb bomb = new InfoBomb(x,y,bomberman.getRange(),StateBomb.Step1);
		bombes.add(bomb);
	}
	
	
	public ArrayList<InfoBomb> getBombes() {
		return bombes;
	}
	


}
