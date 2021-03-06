package dkeep.logic;

import java.util.Random;

public class Dragon extends GameElement {
	private Random rand_pos;
	public final int [] exit = {5, 9};
	
	public Dragon(Map newMap) {
		int dragonx=0, dragony=0;
		int [] heroPos = newMap.getHeroPos();
		int [] keyPos = newMap.getKeyPos();
		rand_pos = new Random();
		
		dragony=rand_pos.nextInt(10);
		dragonx=rand_pos.nextInt(10);
		
		//Testing every possibility where the Dragon can and cannot be:
		while (newMap.getEntry(dragony, dragonx) == 'X' || newMap.getEntry(dragony, dragonx) == 'E' || newMap.getEntry(dragony, dragonx) == 'H' || newMap.getEntry(dragony, dragonx) == 'K'
				|| (dragony==5 && dragonx==1) || (dragonx==exit[1]-1 && (dragony==exit[0]-1 || dragony==exit[0]+1)) //positions that cant happen because the hero will die because dragon near exit or because key/hero were at bottom left corner and dragon traps them
				|| (dragony==heroPos[0] && Math.abs(dragonx-heroPos[1])==1) || (dragonx==heroPos[1] && Math.abs(dragony-heroPos[0])==1) //if dragon and hero are in the same column/row check distance between them, it has to greater than 1
				|| (dragony==keyPos[0] && Math.abs(dragonx-keyPos[1])==1) || (dragonx==keyPos[1] && Math.abs(dragony-keyPos[0])==1)) { //if dragon and key are in the same column/row check distance between them, it has to greater than 1
			dragony=rand_pos.nextInt(10);
			dragonx=rand_pos.nextInt(10);
		}
		
		super.setPosition(dragony, dragonx);
	}

}
