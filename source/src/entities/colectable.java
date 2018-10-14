package entities;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import yengine.yentity;

public class colectable extends yentity {
	
	public int tilex,tiley;//the position on tilmap
	public float height2,width2;
	public float rot;//rotation
	public colectable(int tx, int ty) throws SlickException {
		
		super(0,0, 0, "colect_s.png");
		tilex = tx;
		tiley = ty;
		z=98;
		type="colect";
		rot=0;
		init();
	}//end constructor

	@Override
	public void init() throws SlickException {
		tile.set_tile_pos(this,tilex,tiley);
		y+=11;
		x+=11;
		height2 = 30;
		width2 = 30;
		set_w_h(width2,height2);
		//dir = "right";
		set_img_type("sprite");
		super.init();
	}//end init

	@Override
	public void update() throws SlickException {
		
		super.update();
		rot++;
		set_rot(rot);
		hit_test();
	}//end update
	
	public void hit_test() 
	{
		ArrayList<yentity> player = collide("player");
		if(player.size()>0) 
		{
			world.remove(this);
		}
	}//end hit_test
	
	

}
