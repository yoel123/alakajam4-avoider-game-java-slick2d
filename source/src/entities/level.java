package entities;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import screens.game;
import yengine.yentity;

public class level extends yentity{
	public float tile_width=47,tile_height=54;
	public int[][] map;
	public static int player_x,player_y;//save player x and y

	public level(float x, float y) throws SlickException {
		super(x, y, 0, "");
		type = "level";
		
	}//end constructor
	
	@Override
	public void update() throws SlickException {

		super.update();
		//is_compleate();
	}//end update
	
	public void genrate_map(int[][] map2,boolean start_level) throws SlickException 
	{
		
		//remove_old_tiles();	//remove old tles
		
		if(start_level) {map = maps.deepCopyIntMatrix(map2);}else {map=map2;}
		
		int map_height,map_width;
		float colx,coly;//tilmap cordenates holder
		int ts;//tile symbole
		//set w h
		map_height = map2.length;
		map_width = map2[0].length;
		
		//create tilemap
		for(int i = 0; i < map_height; i++) //loop rows
		{
		    for(int j = 0; j < map_width; j++) //loop cols
		    {
		    	ts = map2[i][j];//the tile symbol
		        colx = j*game.tile_width+game.margin_left;//x pos for tle is tile col number * tile width
		        coly = i*game.tile_height+game.margin_up;//same as with col x
		       
		        if(ts ==6) //if its 6 its player elst its just a tile
		        {
		        	create_player(colx,coly,ts);
		        	ts = 0;//create empthy floor after
		        }
		        if(ts ==7) //if 7 its enemy
		        {
		        	create_enemy(j,i,ts,"patrol_left");
		        	ts = 0;
		        }
		        if(ts ==8) //if 7 its enemy
		        {
		        	create_enemy(j,i,ts,"patrol_up");
		        	ts = 0;
		        }
		        if(ts ==9) //if 9 is collectable
		        {
		        	create_collect(j,i,ts);
		        	ts = 0;
		        }
		        if(ts ==10) //if 7 its enemy
		        {
		        	create_enemy(j,i,ts,"homing");
		        	ts = 0;
		        }
		        create_tile(colx,coly,ts);
		        
		        
		    }//end for cols
		}//end for rows
		
	}//end genrate_map
	
	public void create_tile(float x,float y, int ts) throws SlickException 
	{
	
		
		//if not player add tile
		tile t = new tile(x,y);//create tile and pass pos
		t.type_tile = Integer.toString(ts);//set type
		    
		t.set_tile_img(ts);//change tile img
		world.add(t);//add tile to world
		
	}//end create tile
	
	public void create_player(float x2,float y2, int ts) throws SlickException 
	{
		float[] cam = world.get_camera();
    	
    	cam[0] = x2;
    	cam[1] = y2;
    	//System.out.println(cam[0]+" "+colx);
    	player p = new player(5,5);
    	world.add(p);
	}//end create_player
	
	public void create_enemy(int x,int y, int ts,String move_t) throws SlickException 
	{
		enemy e = new enemy(x,y,0.05f);
		e.move_type = move_t;
		world.add(e);
	}//end create_enemy
	
	public void create_collect(int x,int y, int ts) throws SlickException 
	{
		
		colectable c = new colectable(x,y);
		world.add(c);
		
	}//end create_collect
	
	public void remove_old_tiles() 
	{
		ArrayList<yentity> mcs = get_by_type("tile");
		for(yentity e : mcs)
		{
			world.remove(e);
		}
		
		mcs = get_by_type("player");
		for(yentity e : mcs)
		{
			world.remove(e);
		}
		mcs = get_by_type("enemy");
		for(yentity e : mcs)
		{
			world.remove(e);
		}
		
	}//end remove_old_tiles
	
	public void reset() throws SlickException 
	{
		//easiest way to do this
		world.yinit(gc, sbg);//reset game world...
		
	}//end reset
	
	public void is_compleate() throws SlickException 
	{
		
	}//end is_compleate


}//end level
