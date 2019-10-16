package dkeep.logic;

public class Game {
	Map map;
	Key key;
	Hero hero;
	Dragon dragon;
	int userHasKey;
	int userExited;
	int state;
	public final int [] exit = {5, 9};
	
	public Game() {
		map = new Map();
		hero = new Hero(map);
		map.setEntry(hero.getPosition(), 'H');
		map.setHeroPos(hero.getPosition());
		key = new Key(map);
		map.setEntry(key.getPosition(), 'K');
		map.setKeyPos(key.getPosition());
		dragon = new Dragon(map);
		map.setEntry(dragon.getPosition(), 'D');
		map.draw();
	}//end of constructor()	
	
	public int getState() {
		return state;
	}//end of getState()
	
	public void moveHero(char userInput) {
		map.setEntry(hero.getPosition(), ' ');//Check this.....
		hero.move(map, userInput); 
		map.setEntry(hero.getPosition(), 'H');
		int flag = 0;
		//debugging:
		//System.out.printf("\nCurrent Hero's Position: [%d, %d]\n", hero.getPosition()[0], hero.getPosition()[1]);
		
		if(key.getPosition()[0] == hero.getPosition()[0] && key.getPosition()[1] == hero.getPosition()[1]) {
			userHasKey = 1;
			//System.out.println("oi");
			//map.setEntry(key.getPosition(), ' ');
		}
		if(userHasKey == 1 && hero.getPosition()[0] == exit[0] && hero.getPosition()[1] == exit[1]) {
			state = 1;
			map.setEntry(hero.getPosition(), 'E');
		}
		else if(userHasKey == 0 && hero.getPosition()[0] == exit[0] && hero.getPosition()[1] == exit[1]) {
			map.setEntry(hero.getPosition(), 'E');
			hero.setPosition(hero.getPosition()[0], hero.getPosition()[1]-1);
			map.setEntry(hero.getPosition(), 'H');
			flag--;
		}
		if(hero.getPosition()[0] == dragon.getPosition()[0] &&  hero.getPosition()[1] == dragon.getPosition()[1] || hero.getPosition()[0]+1 == dragon.getPosition()[0] && hero.getPosition()[1] == dragon.getPosition()[1] || hero.getPosition()[0]-1 == dragon.getPosition()[0] && hero.getPosition()[1] == dragon.getPosition()[1] || hero.getPosition()[1]+1 == dragon.getPosition()[1] && hero.getPosition()[0] == dragon.getPosition()[0] || hero.getPosition()[1]-1 == dragon.getPosition()[1] && hero.getPosition()[0] == dragon.getPosition()[0]) { //Is this complete?
			state = -1;
			map.setEntry(hero.getPosition(), ' ');
			map.setEntry(dragon.getPosition(), 'D');//let's keep the Dragon, so it seems like the dragon ate the Hero :D
		}
		map.draw();
		if(flag != 0) {
			System.out.println("\nWARNING:\nYou can't Exit without the Key (K)! Get the key and then get out.\n");
		}
	}
}
