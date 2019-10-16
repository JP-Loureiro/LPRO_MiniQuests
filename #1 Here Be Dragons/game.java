import java.util.Scanner; 
import java.util.Random;

import java.lang.Math;

public class game {
    
	private static char [][] map;
	private static int heroy, herox, keyy, keyx, dragony, dragonx, exity=5, exitx=9;
	private static boolean gameover, key;
	private static char c;
	private static Scanner input= new Scanner (System.in);
	
	private static void init() {
		gameover=false;
		key=false;
		map = new char[][] {
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{'X', 0, 0, 0, 0, 0, 0, 0, 0, 'X'},
			{'X', 0, 'X', 'X', 0, 'X', 0, 'X', 0, 'X'},
			{'X', 0, 'X', 'X', 0, 'X', 0, 'X', 0, 'X'},
			{'X', 0, 'X', 'X', 0, 'X', 0, 'X', 0, 'X'},
			{'X', 0, 0, 0, 0, 0, 0, 'X', 0, 'E'},
			{'X', 0, 'X', 'X', 0, 'X', 0, 'X', 0, 'X'},
			{'X', 0, 'X', 'X', 0, 'X', 0, 'X', 0, 'X'},
			{'X', 0, 'X', 'X', 0, 0, 0, 0, 0, 'X'},
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
		};
		
		Random randhero = new Random(), randkey = new Random(), randdragon = new Random();
		
		heroy=randhero.nextInt(10); herox=randhero.nextInt(10);
		keyy=randkey.nextInt(10); keyx=randkey.nextInt(10);
		dragony=randdragon.nextInt(10); dragonx=randdragon.nextInt(10);
		
		while (map[heroy][herox] == 'X' || map[heroy][herox] == 'E') {
			heroy=randhero.nextInt(10);
			herox=randhero.nextInt(10);
		}
		map[heroy][herox]='H';
		
		while (map[keyy][keyx] == 'X' || map[keyy][keyx] == 'E' || map[keyy][keyx] == 'H') {
			keyy=randkey.nextInt(10);
			keyx=randkey.nextInt(10);
		}
		map[keyy][keyx]='K';

		while (map[dragony][dragonx] == 'X' || map[dragony][dragonx] == 'E' || map[dragony][dragonx] == 'H' || map[dragony][dragonx] == 'K'
				|| (dragony==5 && dragonx==1) || (dragonx==exitx-1 && (dragony==exity-1 || dragony==exity+1)) //positions that cant happen because the hero will die because dragon near exit or because key/hero were at bottom left corner and dragon traps them
				|| (dragony==heroy && Math.abs(dragonx-herox)==1) || (dragonx==herox && Math.abs(dragony-heroy)==1) //if dragon and hero are in the same column/row check distance between them, it has to greater than 1
				|| (dragony==keyy && Math.abs(dragonx-keyx)==1) || (dragonx==keyx && Math.abs(dragony-keyy)==1)) { //if dragon and key are in the same column/row check distance between them, it has to greater than 1
			dragony=randdragon.nextInt(10);
			dragonx=randdragon.nextInt(10);
		}
		map[dragony][dragonx]='D';
	}
	
	private static void draw() {
		for (int i = 0; i < 10; i++) {
		    for (int j = 0; j < 10; j++) {
		        System.out.print(map[i][j] + " ");
		    }
		    System.out.println();
		}
	}
	
	private static void input() {
			c=input.next().charAt(0);
	}
	
	private static void logic() {
		if (c=='d' && key && herox==exitx && heroy==exity) {
			map[heroy][herox]=0;
			herox++;
			gameover=true;
			draw(); //draw one last time with hero gone
			System.out.println("easy");
		}
		
		else if ((c == 'w' && map[heroy-1][herox] != 'X' && map[heroy-1][herox] != 'E'  && !key) || (c == 'w' && map[heroy-1][herox] != 'X' && key)) {
			map[heroy][herox]=0;
			heroy--;
			map[heroy][herox]='H';
		}
		
		else if ((c == 's' && map[heroy+1][herox] != 'X' && map[heroy+1][herox] != 'E'  && !key) || (c == 's' && map[heroy+1][herox] != 'X' && key)) {
			map[heroy][herox]=0;
			heroy++;
			map[heroy][herox]='H';
		}
		
		else if ((c == 'a' && map[heroy][herox-1] != 'X' && map[heroy][herox-1] != 'E'  && !key) || (c == 'a' && map[heroy][herox-1] != 'X' && key)) {			
			map[heroy][herox]=0;
			herox--;
			map[heroy][herox]='H';
		}
		
		else if ((c == 'd' && map[heroy][herox+1] != 'X' && map[heroy][herox+1] != 'E'  && !key) || (c == 'd' && map[heroy][herox+1] != 'X' && key)) {
			map[heroy][herox]=0;
			herox++;
			map[heroy][herox]='H';
		}
		
		if ((heroy==dragony-1 && herox==dragonx) || (heroy==dragony+1 && herox==dragonx) || (herox==dragonx-1 && heroy==dragony) || (herox==dragonx+1 && heroy==dragony)) {
			map[heroy][herox]=0;
			gameover=true;
			draw(); //one last draw to kill the hero in the map
			System.out.println("rip");
		}
		
		if (heroy==keyy && herox==keyx) {
			key=true;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init();
		while (!gameover) {
			draw();
			input();
			logic();
		}
	}
}
