package entities;

import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import screens.game;
import yengine.yengine;
import yengine.yentity;

public class player extends yentity 
{
	public int tilex,tiley;//the position on tilmap
	public float height2,width2,cam_speed;
	
	public player(int tx, int ty) throws SlickException {
		super(0,0, 0, "p1.png");
		tilex = tx;
		tiley = ty;
		z=99;
		no_cam = true;
		cam_speed = 0.16f;
		type="player";
	//	debug = true;
		init();
	}//end constructor

	@Override
	public void init() throws SlickException 
	{

		tile.set_tile_pos(this,tilex,tiley);

		height2 = game.tile_height;
		width2 = game.tile_width;
		set_w_h(width2,height2);
		
		set_img_type("sprite");
		height=30;
		width=30;
		// TODO Auto-generated method stub
		super.init();
		
	}//end init



	@Override
	public void update() throws SlickException {
		// TODO Auto-generated method stub
		super.update();
		pause();
		move();
		collected_all();
		

	}

	public void set_tile_pos() 
	{
		
		x = tilex*game.tile_width+game.margin_left;
		y = tiley*game.tile_height+game.margin_up;

	}//end set_tile_pos

	public void move() throws SlickException 
	{
		tile is_wall;
		boolean up,down,left,right,w,a,s,d,r;
		float[] cam = world.get_camera();
		float vx=0,vy=0;
		up = yengine.key_pressed(gc,Input.KEY_UP);
		down = yengine.key_pressed(gc,Input.KEY_DOWN);
		left = yengine.key_pressed(gc,Input.KEY_LEFT);
		right = yengine.key_pressed(gc,Input.KEY_RIGHT);
		w = yengine.key_pressed(gc,Input.KEY_W);
		a = yengine.key_pressed(gc,Input.KEY_A);
		s = yengine.key_pressed(gc,Input.KEY_S);
		d = yengine.key_pressed(gc,Input.KEY_D);
		r = yengine.key_pressed(gc,Input.KEY_R);
		
		//reset
		if(r) {game g = (game) world;g.l.reset();}
		
		if((up || w)  )
		{   
			vy +=cam_speed; dir = "up";
			//whech if going to hit a wall is so stop
			is_wall = is_collide_wall(vx,vy-5);
			if(is_wall!=null  ) {vy =0;}
			
		}
		
		if((down || s) )
		{
			vy -=cam_speed; dir = "down";
			is_wall = is_collide_wall(vx,vy+5);
			if(is_wall!=null ) {vy =0;}
		}
		
		if((left || a) )
		{
			vx +=cam_speed; dir = "left";
			is_wall = is_collide_wall(vx-5,vy);
			if(is_wall!=null ) {vx =0;}
		}
		if((right || d) )
		{
		    vx -=cam_speed; dir = "right";
			is_wall = is_collide_wall(vx+5,vy);
			if(is_wall!=null ) {vx =0;}
		}

		cam[0]+=vx*delta;
		cam[1]+=vy*delta;
		
		//yengine.o(tile.get_tile_pos(this,true)[1]);
		//if all else failes slide trogh better then stuck
		is_wall = is_collide_wall(0,0);
		if(is_wall!=null ) 
		{
			cam[0]-=10;
			cam[1]-=10;
	
		}
		
	}//end move

	public tile is_collide_wall(float vx, float vy) throws SlickException 
	{
		tile t;//tile for testing
		float[] cam = world.get_camera();
		float wx = x+vx,wy = y+vy;//world xy
		ArrayList<yentity> tils = collide(wx,wy,"tile");
		//yengine.o(wx+" "+wy);
		for(yentity e : tils) 
		{
			t = (tile)e;
			if(t.type_tile.equals("wall")) 
			{
				//if(Math.signum(x)) {
				return t;
			}
		}//end for
		return null;
		
	}//end ycollide
	
	public void collected_all() throws SlickException 
	{
		ArrayList<yentity> player = get_by_type("colect");
		//if collected all
		if(player.size()<=0) 
		{
			game.stage++;
			game g = (game) world;
			g.l.reset();
		}
		
	}//end collected_all
	
	public void pause() 
	{
		boolean p;
		p= key_released(Input.KEY_P);
		
		game g = (game)world;
		//pause game
		if(p) 
		{
			g.pause =!g.pause;
		}
	}//end pause
}
