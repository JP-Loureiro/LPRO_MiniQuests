package dkeep.logic;

public class Game {
	Map map;
	Sword sword;
	Hero hero;
	Dragon dragon;
	int userHasSword;
	int userExited;
	int state;
	public final int [] exit = {5, 9};
	
	public Game() {
		map = new Map();
		hero = new Hero(map);
		map.setEntry(hero.getPosition(), 'H');
		map.setHeroPos(hero.getPosition());
		sword = new Sword(map);
		map.setEntry(sword.getPosition(), 'S');
		map.setSwordPos(sword.getPosition());
		dragon = new Dragon(map);
		map.setEntry(dragon.getPosition(), 'D');
		map.draw();
	}//end of constructor()	
	
	public int getState() {
		return state;
	}//end of getState()
	
	public void moveHero(char userInput) {//this method has, basically, the game logic
		char heroChar;
		if (userHasSword==1) {
			heroChar = 'A'; 
		}
		else {
			heroChar = 'H';
		}
		map.setEntry(hero.getPosition(), ' ');//Check this.....
		hero.move(map, userInput); 
		map.setEntry(hero.getPosition(), heroChar);
		int flag = 0;
		//debugging:
		//System.out.printf("\nCurrent Hero's Position: [%d, %d]\n", hero.getPosition()[0], hero.getPosition()[1]);
		
		if(userHasSword == 0 && sword.getPosition()[0] != dragon.getPosition()[0] && sword.getPosition()[1] != dragon.getPosition()[1]) {
			map.setEntry(sword.getPosition(), 'S');
		}
		if(userHasSword == 0 && sword.getPosition()[0] == hero.getPosition()[0] && sword.getPosition()[1] == hero.getPosition()[1]) {
			userHasSword = 1;
			map.setEntry(hero.getPosition(), 'A');
			//System.out.println("oi");
			//map.setEntry(sword.getPosition(), ' ');
		}
		if(!dragon.checkIfAlive() && hero.getPosition()[0] == exit[0] && hero.getPosition()[1] == exit[1]) {
			state = 1;
			map.setEntry(hero.getPosition(), 'E');
		}
		else if(dragon.checkIfAlive() && hero.getPosition()[0] == exit[0] && hero.getPosition()[1] == exit[1]) {
			map.setEntry(hero.getPosition(), 'E');
			hero.setPosition(hero.getPosition()[0], hero.getPosition()[1]-1);
			map.setEntry(hero.getPosition(), heroChar);
			flag--;
		}
		if((userHasSword == 0) && (hero.getPosition()[0] == dragon.getPosition()[0] &&  hero.getPosition()[1] == dragon.getPosition()[1] || hero.getPosition()[0]+1 == dragon.getPosition()[0] && hero.getPosition()[1] == dragon.getPosition()[1] || hero.getPosition()[0]-1 == dragon.getPosition()[0] && hero.getPosition()[1] == dragon.getPosition()[1] || hero.getPosition()[1]+1 == dragon.getPosition()[1] && hero.getPosition()[0] == dragon.getPosition()[0] || hero.getPosition()[1]-1 == dragon.getPosition()[1] && hero.getPosition()[0] == dragon.getPosition()[0])) { //Is this complete?
			state = -1;
			map.setEntry(hero.getPosition(), ' ');
			map.setEntry(dragon.getPosition(), 'D');//let's keep the Dragon, so it seems like the dragon ate the Hero :D
		}
		else if((userHasSword == 1) && (hero.getPosition()[0] == dragon.getPosition()[0] &&  hero.getPosition()[1] == dragon.getPosition()[1] || hero.getPosition()[0]+1 == dragon.getPosition()[0] && hero.getPosition()[1] == dragon.getPosition()[1] || hero.getPosition()[0]-1 == dragon.getPosition()[0] && hero.getPosition()[1] == dragon.getPosition()[1] || hero.getPosition()[1]+1 == dragon.getPosition()[1] && hero.getPosition()[0] == dragon.getPosition()[0] || hero.getPosition()[1]-1 == dragon.getPosition()[1] && hero.getPosition()[0] == dragon.getPosition()[0])) {
			map.setEntry(dragon.getPosition(), ' ');//Dragon is dead
			map.setEntry(hero.getPosition(), 'A');
			dragon.isDead();//isto vai ser para usar mais à frente, quando existirem vários dragões, trust me varges
		}
		if(state == 0 && dragon.checkIfAlive()) {
			map.setEntry(dragon.getPosition(), ' ');
			dragon.move(map);
			map.setEntry(dragon.getPosition(), 'D');//Dragon is dead
			if(userHasSword == 0 && sword.getPosition()[0] == dragon.getPosition()[0] && sword.getPosition()[1] == dragon.getPosition()[1]) {
				map.setEntry(sword.getPosition(), 'F');
			}
			if((userHasSword == 0) && (hero.getPosition()[0] == dragon.getPosition()[0] &&  hero.getPosition()[1] == dragon.getPosition()[1] || hero.getPosition()[0]+1 == dragon.getPosition()[0] && hero.getPosition()[1] == dragon.getPosition()[1] || hero.getPosition()[0]-1 == dragon.getPosition()[0] && hero.getPosition()[1] == dragon.getPosition()[1] || hero.getPosition()[1]+1 == dragon.getPosition()[1] && hero.getPosition()[0] == dragon.getPosition()[0] || hero.getPosition()[1]-1 == dragon.getPosition()[1] && hero.getPosition()[0] == dragon.getPosition()[0])) { //Is this complete?
				state = -1;
				map.setEntry(hero.getPosition(), ' ');
				map.setEntry(dragon.getPosition(), 'D');//let's keep the Dragon, so it seems like the dragon ate the Hero :D
			}
			else if((userHasSword == 1) && (hero.getPosition()[0] == dragon.getPosition()[0] &&  hero.getPosition()[1] == dragon.getPosition()[1] || hero.getPosition()[0]+1 == dragon.getPosition()[0] && hero.getPosition()[1] == dragon.getPosition()[1] || hero.getPosition()[0]-1 == dragon.getPosition()[0] && hero.getPosition()[1] == dragon.getPosition()[1] || hero.getPosition()[1]+1 == dragon.getPosition()[1] && hero.getPosition()[0] == dragon.getPosition()[0] || hero.getPosition()[1]-1 == dragon.getPosition()[1] && hero.getPosition()[0] == dragon.getPosition()[0])) {
				map.setEntry(dragon.getPosition(), ' ');//Dragon is dead
				map.setEntry(hero.getPosition(), 'A');
				dragon.isDead();//isto vai ser para usar mais à frente, quando existirem vários dragões, trust me varges
			}
		}
		map.draw();
		if(flag != 0) {
			System.out.println("\nWARNING:\nYou can't Exit without catching the Sword (S) and killing the Dragon (D)!\nGet the sword, kill the Dragon and then get out.\n");
		}
	}
}
