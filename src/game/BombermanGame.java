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
import objects.InfoItem;
import objects.ItemType;
import objects.StateBomb;
import view.ViewBombermanGame;

public class BombermanGame extends Game implements Observable {

    private ArrayList<Agent> agentList;
    private ArrayList<Agent> ListAgentsStart;
    private ArrayList<InfoBomb> bombes;
    private static boolean[][] list_wall;
    private ArrayList<InfoItem> list_item;
    

	public BombermanGame() {
		agentList = new ArrayList<Agent>();
		ListAgentsStart = new ArrayList<Agent>();
		bombes = new ArrayList<InfoBomb>();
		list_item = new ArrayList<InfoItem>();
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
		list_item = new ArrayList<InfoItem>();
		
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
		
		int tailleX = ViewBombermanGame.getMap_jeu().getStart_brokable_walls().length;
		int tailleY = ViewBombermanGame.getMap_jeu().getStart_brokable_walls()[0].length;
		list_wall = new boolean[tailleX][tailleY];
		
		for(int i = 0 ; i < tailleX ; i++) {
			for(int j = 0 ; j < tailleY ; j++) {
				list_wall[i][j] = ViewBombermanGame.getMap_jeu().getStart_brokable_walls()[i][j];
			}
		}
		list_item = new ArrayList<InfoItem>();

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
			//System.out.println(agent.getX()+"			"+agent.getY());
		
			AgentAction[] listaction = AgentAction.values();
			int action_random = (int) (Math.random()*listaction.length);
			moveAgent(agent,listaction[action_random]);
			if (agent.getType()=='B') {
				itemBoost(agent.getX(), agent.getY(), (Agent_Bomberman)agent);
				if (AgentAction.PUT_BOMB == listaction[action_random] )
				{
					placeBomb((Agent_Bomberman)agent);
				}
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
			if(agent.doAction(agentList, action)!=AgentAction.STOP)
			{
				agent.setY(y-1);
			}
			else
				agent.setAgentAction(AgentAction.STOP);
			break;
			
		case MOVE_DOWN:
			agent.setAgentAction(action);
			if(agent.doAction(agentList, action)!=AgentAction.STOP)
			{
				agent.setY(y+1);
			}
			else
				agent.setAgentAction(AgentAction.STOP);
			break;

		case MOVE_LEFT:
			agent.setAgentAction(action);
			if(agent.doAction(agentList, action)!=AgentAction.STOP)
			{
				agent.setX(x-1);
			}
			else
				agent.setAgentAction(AgentAction.STOP);
			break;
			
		case MOVE_RIGHT:
			agent.setAgentAction(action);
			if(agent.doAction(agentList, action)!=AgentAction.STOP)
			{
				agent.setX(x+1);
			}
			else
				agent.setAgentAction(AgentAction.STOP);
			break;
			
		case STOP:
		default:
			agent.setAgentAction(AgentAction.STOP);
			break;
		}
	}
	/*
	if (direction == Map.EAST) {
		for(int i = 0; i<=bomb.getRange(); i++){
			if(x+i<map.getSizeX()){
				if(this.isBrokable_Wall(x+i, y) || map.isWall(x+i, y)){
					taille_range = x+i;
					break;
				}
				else taille_range = x+i;
			}
		}
	}*/
	
	//Réalise l'explosion d'une et ses conséquences
	public void bombExplode(InfoBomb bomb)
	{
		int x = bomb.getX();
		int y = bomb.getY();
		
		ArrayList<Agent> agents = this.agentList;
		
		System.out.println("Taille " + list_wall.length);
		// TEST RANGE EAST
		
		for(int i = x; i<= x + bomb.getRange(); i++){
			
			for(int j = 0; j< agents.size(); j++){
				Agent agent = agents.get(j);
				if(agent.getX() == i && agent.getY() == y && agent.getType()!='B'){
					agents.remove(j);
				}
			}
				
			for(int j = 0; j<bombes.size(); j++){
				InfoBomb bombe = bombes.get(j);
				if(bomb != bombe)
				if(bombe.getX() == i & bombe.getY() == y){
					bombe.setStateBomb(StateBomb.Boom);
					}
				bombe.setRange_wall_at(0, i-x);
				System.out.println("        EAST");
			}
			
			if(i < list_wall.length)
				if(list_wall[i][y]) {
					list_wall[i][y]=false;
					creerItem(i,y);
					break;
				}
		}
		
		// TEST RANGE SOUTH
		
		for(int i = y; i <= y +bomb.getRange(); i++){
			
			for(int j = 0; j< agents.size(); j++){
				Agent agent = agents.get(j);
				if(agent.getX() == x && agent.getY() == i && agent.getType()!='B'){
					agents.remove(j);
				}
			}
				
			for(int j = 0; j<bombes.size(); j++){
				InfoBomb bombe = bombes.get(j);
				if(bomb != bombe)
					if(bombe.getX() == x & bombe.getY() == i ){
						bombe.setStateBomb(StateBomb.Boom);
					}
				bombe.setRange_wall_at(1, i-y);
				System.out.println("        EAST");
			}
			if(i < list_wall[x].length)
				if(list_wall[x][i]){
					list_wall[x][i]=false;
					creerItem(x,i);
					break;
				}
		}
		
		// TEST RANGE WEST
		
		for(int i = x; i >= x-bomb.getRange(); i--){
			
			for(int j = 0; j< agents.size(); j++){
				Agent agent = agents.get(j);
				if(agent.getX() == i && agent.getY() == y && agent.getType()!='B'){
					agents.remove(j);
				}
			}
				
			for(int j = 0; j<bombes.size(); j++){
				InfoBomb bombe = bombes.get(j);
				if(bomb != bombe)
					if(bombe.getX() == i & bombe.getY() == y){
						bombe.setStateBomb(StateBomb.Boom);
					}
				bombe.setRange_wall_at(2, x-i);
				System.out.println("        WEST");
			}
			if(i > 0)
				if(list_wall[i][y]){
					list_wall[i][y]=false;
					creerItem(i,y);
					break;
				}
	
		}
		
		
		// TEST RANGE NORTH
		for(int i = y; i >= y-bomb.getRange(); i--){
			
			for(int j = 0; j< agents.size(); j++){
				Agent agent = agents.get(j);
				if(agent.getX() == x && agent.getY() == i && agent.getType()!='B'){
					agents.remove(j);
					}
				}
				
			for(int j = 0; j<bombes.size(); j++){
				InfoBomb bombe = bombes.get(j);
				if(bomb != bombe)
				if(bombe.getX() == x & bombe.getY() == i){
					bombe.setStateBomb(StateBomb.Boom);
					}
				bombe.setRange_wall_at(3, y-i);
				System.out.println("        NORTH");
			}
			
			if(i > 0)
				if(list_wall[x][i]){
					list_wall[x][i]=false;
					creerItem(x,i);
					break;
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
					bombExplode(bombe);
					break;
					
				case Boom:
					
					bombes.remove(bombe);
					break;
				}
		}
	}
	
	//place une bombe à la position du bomberman
	public void placeBomb(Agent_Bomberman bomberman)
	{
		int x = bomberman.getX();
		int y = bomberman.getY();
		if(this.bombes.size()>=0) {
		InfoBomb bomb = new InfoBomb(x,y,bomberman.getRange(),StateBomb.Step1);
		bombes.add(bomb);
		}
	}
	
//	public void agentTurn() {
//
//		ArrayList<Agent> agents = this.getAgentList();
//			
//				for(int i = 0; i < agents.size(); i++){
//					Agent agent = agents.get(i);
//		
//					if (!agent.isInvincible() && S){
//						if(isEnnemie(agent.getX(),agent.getY())) {
//							agent.setDead(true);
//						}
//						
//						if(isBird(agent.getX(),agent.getY())) {
//							agent.agent(true);
//						}
//						
//						if(isRajion(agent.getX(),agent.getY())) {
//							agent.setDead(true);
//						}
//					}
//					
//				}
//	}
	
	public ArrayList<InfoBomb> getBombes() {
		return bombes;
	}

	public boolean[][] getList_wall() {
		return list_wall;
	}
	
	public ArrayList<InfoItem> getList_item() {
		return list_item;
	}
	
	public void creerItem(int x, int y) {
		ItemType[] listitem = ItemType.values();
		int drop_random = (int) (Math.random()*100);
		System.out.println("drop " + drop_random);
		if(drop_random < 40) {
			int item_random = (int) (Math.random()*listitem.length);
			list_item.add(new InfoItem(x,y,listitem[item_random]));
		}
	}
	
	public void itemBoost(int x, int y, Agent_Bomberman agent) {
		for(int i=0; i < list_item.size();i++)
		{
			if(list_item.get(i).getX()==x && list_item.get(i).getY()==y && agent.getType()=='B')
			{
				switch(list_item.get(i).getType()) {
					case BOMB_DOWN:
						if(agent.getNbBombes()>1)
						{
							agent.setNbBombes(agent.getNbBombes()-1);
						}
						break;
					case BOMB_UP:
						agent.setNbBombes(agent.getNbBombes()+1);
						break;
					case FIRE_DOWN:
						if(agent.getRange()>1)
							agent.setRange(agent.getRange()-1);
						break;
					case FIRE_UP:
						agent.setRange(agent.getRange()+1);
						break;
					case FIRE_SUIT:
						agent.setInvincible(true);
						agent.setTourInv(this.turn);
						break;
					case SKULL:
						break;
					default:
						break;
				}
				list_item.remove(i);
			}
		}
	}
	
	public static boolean isLegalMove(Agent agent, AgentAction action) {
		boolean[][] liste_unbreakable_wall = ViewBombermanGame.getMap_jeu().get_walls();
		
		switch (action) {
			case MOVE_DOWN:
				if (!liste_unbreakable_wall[agent.getX()][agent.getY()+1] && !list_wall[agent.getX()][agent.getY()+1])
					return true;
				break;
			case MOVE_UP:
				if (!liste_unbreakable_wall[agent.getX()][agent.getY()-1] && !list_wall[agent.getX()][agent.getY()-1])
					return true;
				break;
			case MOVE_RIGHT:
				if (!liste_unbreakable_wall[agent.getX()+1][agent.getY()] && !list_wall[agent.getX()+1][agent.getY()])
					//System.out.println(!list_wall[agent.getX()+1][agent.getY()]);
					return true;
				break;
			case MOVE_LEFT:
				if (!liste_unbreakable_wall[agent.getX()-1][agent.getY()] && !list_wall[agent.getX()-1][agent.getY()])
					return true;
				break;
			default:
				break;
		}
		return false;
	}
}
