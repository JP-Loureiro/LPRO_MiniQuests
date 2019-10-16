package dkeep.logic;

import java.util.Random;

public class Key extends GameElement {
	Random rand_pos;
	
	public Key(Map newMap) {
		rand_pos = new Random();
		int keyx = 0, keyy=0;
		
		keyy=rand_pos.nextInt(10);
		keyx=rand_pos.nextInt(10);
		
		while (newMap.getEntry(keyy, keyx) == 'X' || newMap.getEntry(keyy, keyx) == 'E' || newMap.getEntry(keyy,keyx) == 'H') {
			keyy=rand_pos.nextInt(10);
			keyx=rand_pos.nextInt(10);
		}

		super.setPosition(keyy, keyx);
	}

}
