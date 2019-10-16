package dkeep.logic;

import java.util.Random;

public class Hero extends GameElement {
	Random rand_pos;
	
	public Hero(Map newMap) {
		rand_pos = new Random();
		int herox = 0, heroy=0;
		
		heroy=rand_pos.nextInt(10);
		herox=rand_pos.nextInt(10);
		
		while (newMap.getEntry(heroy, herox) == 'X' || newMap.getEntry(heroy, herox) == 'E') {
			heroy=rand_pos.nextInt(10);
			herox=rand_pos.nextInt(10);
		}
		
		super.setPosition(heroy, herox);
	}
	
	public void move(Map map, char userInput){
		int heroy = super.getPosition()[0];
		int herox = super.getPosition()[1];
		
		if ((userInput == 'w' && map.getEntry(heroy-1, herox) != 'X')) {
			super.setPosition(heroy-1, herox);
		}
		
		else if ((userInput == 's' && map.getEntry(heroy+1, herox) != 'X')) {
			super.setPosition(heroy+1, herox);
		}
		
		else if ( (userInput == 'a' && map.getEntry(heroy, herox-1) != 'X')) {			
			super.setPosition(heroy, herox-1);
		}
		
		else if ((userInput == 'd' && map.getEntry(heroy, herox+1) != 'X')) {
			super.setPosition(heroy, herox+1);
		}
	}

}
