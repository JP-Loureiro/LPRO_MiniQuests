package dkeep.logic;

import java.util.Random;

public class Sword extends GameElement {
	Random rand_pos;
	
	public Sword(Map newMap) {
		rand_pos = new Random();
		int swordx = 0, swordy=0;
		
		swordy=rand_pos.nextInt(10);
		swordx=rand_pos.nextInt(10);
		
		while (newMap.getEntry(swordy, swordx) == 'X' || newMap.getEntry(swordy, swordx) == 'E' || newMap.getEntry(swordy,swordx) == 'H') {
			swordy=rand_pos.nextInt(10);
			swordx=rand_pos.nextInt(10);
		}

		super.setPosition(swordy, swordx);
	}

}
