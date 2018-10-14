package screens;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import entities.colectable;
import entities.enemy;
import entities.level;
import entities.maps;
import entities.player;
import yengine.yengine;
import yengine.yentity;
import yengine.yworld;

public class game extends yworld {


	
	public static float tile_width=47,tile_height=54;
	public static float margin_left=10,margin_up=100;
	public static int stage = 1;
	private static int max_stages=5;
	private static int[][] current_map;
	private static int[][] current_map_start;
	public static level l;
	
	
	public boolean pause = false;
	public game(int state) throws SlickException {
		super(state);
	
	}
	
	@Override
	public void yinit(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		mc = new ArrayList<yentity>();//reset everything
		
		
		String ph = "blue_box.png";//place holder
		player p;
		p = new player(5,5);
		
		enemy e = new enemy(2,4,0.05f);
		
		colectable c = new colectable(2,4);
		
		set_level();
		//add game entytis
		game.l = new level(0,110);
		add(game.l);
		game.l.genrate_map(game.current_map,true);//true means level started so render as if level started
	//	add(p);
	//	add(e);
		//add(c);
		
	
	
	}//end yinit

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		

		
		
		super.update(gc, sbg, delta);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		//yengine.ds(g, "text", 20, 20);
		super.render(arg0, arg1, g);
	}
	
	
	public void set_level() 
	{
		if(game.stage>game.max_stages) 
		{
			game.stage = 1;
		}
		//set_level
		if(game.stage==1) 
		{
			set_map(maps.one);
			
		}
		//set_level
		if(game.stage==2) 
		{
			set_map(maps.two);
		
		}
		if(game.stage==3) 
		{
			//game.stage=1;
			set_map(maps.three);
		}
		if(game.stage==4) 
		{
			//game.stage=1;
			set_map(maps.four);
		}
		if(game.stage==5) 
		{
			//game.stage=1;
			set_map(maps.five);
		}
	}//end set_level
	
	public void set_map(int[][] map2) 
	{

	
		game.current_map = maps.deepCopyIntMatrix(map2);	
		//game.current_map_start = Arrays.copyOf(map2, map2.length);
		game.current_map_start = maps.deepCopyIntMatrix(map2);
	}
	

}
