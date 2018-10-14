package entities;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import screens.game;
import yengine.yengine;
import yengine.yentity;

public class enemy extends yentity {

	public int tilex,tiley;//the position on tilmap
	public float height2,width2;
	public String move_type;
	public boolean homing;
	public float rot;//rotation
	tile last_wall;//last wall hit
	public enemy(int tx, int ty, float speed) throws SlickException {
		//super(0,0, speed, "enemy.png");
		super(0,0, speed, "enemy_s.png");
		tilex = tx;
		tiley = ty;
		z=98;
		type="enemy";
		move_type="homing";//"patrol_up";
		rot=0;
		//debug=true;
		init();
	}//end constructor

	@Override
	public void init() throws SlickException {
		
		tile.set_tile_pos(this,tilex,tiley);
		y+=11;
		x+=11;
		height2 = 20;
		width2 = 20;
		set_w_h(width2,height2);
		//dir = "right";
		set_img_type("sprite");
	
		super.init();
	}

	@Override
	public void update() throws SlickException {
		move();
		hit_player();
		super.update();
	}//end update
	
	public void move() 
	{
		if(move_type.equals("patrol_up")) 
		{
			patrol(0);
		}
		if(move_type.equals("patrol_right")) 
		{
			patrol(1);
		}
		if(move_type.equals("patrol_left")) 
		{
			patrol(2);
		}
		if(move_type.equals("homing")) 
		{
			homing_player(120);
			set_sxy(1,0);
			return;
		}
		rot+=10;
		set_rot(rot);
	}//end move
	
	public void hit_player() throws SlickException 
	{
		ArrayList<yentity> player = collide("player");
		if(player.size()>0) 
		{
			game g = (game) world;
			g.l.reset();
		}
	}//end hit_player
	
	public void patrol(int dir) 
	{
		if(homing) {return;}
		tile t;//tile for testing
		ArrayList<yentity> tils = collide("tile");
		
		for(yentity e : tils) 
		{
			t = (tile)e;
			if(t.type_tile.equals("wall") && !t.equals(last_wall)) 
			{
				speed *=-1;
				last_wall = t;
			}
		}
		//patrol dir move
		if(dir==2) {move_by(-speed,0);}//left
		if(dir==1) {move_by(speed,0);}//right
		if(dir==0)  {move_by(0,speed);}//up
		if(dir==-1)  {move_by(0,speed);}//down
	}//end patrol
	
	public void homing_player(float dist) 
	{
		homing = true;
		//
		ArrayList<yentity> player = get_by_type("player");
		
		if(player.size()<=0) {return;}
		yentity e = player.get(0);
		float[] cam = world.get_camera();
		
		if(distanse(e.x-cam[0],e.y-cam[1])<=dist) 
			move_to(e);
		
	}//end homing_player
	
	public void move_to(yentity t)
	{
		//ony folow on x axis by cam
		float[] cam = world.get_camera();
		float camx = t.x-cam[0],
			  camy = t.y-cam[1];//cam[0] = camera on x axis
		//yengine.o(camx+" "+camy);
		if(this.x>camx){move_by(-speed,0); dir="left"; }
		if(this.x<camx){move_by(speed,0); dir="right";}
		if(this.y>camy){move_by(0,-speed); dir="up"; }
		if(this.y<camy){move_by(0,speed); dir="down";}
	}//end move_to
	
	
	

}
