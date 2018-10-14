package screens;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import entities.btn;

import yengine.yengine;
import yengine.yworld;

public class credits extends yworld {

	public btn play_btn;
	public credits(int state) throws SlickException {
		super(state);
		
	}

	@Override
	public void yinit(GameContainer gc, StateBasedGame sbg) throws SlickException {
		//create play btn
		play_btn= new btn(150.0f,200.0f,"playNow.png",250.0f,50.0f);

		add(play_btn);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		//if btn is clicked change world to game world
		if(play_btn.is_clicked)
		{
			sbg.getState( 1 ).init(gc, sbg);
			sbg.enterState(1);
		}
		super.update(gc, sbg, delta);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {


		super.render(arg0, arg1, g);
		yengine.ds(g, "controls: wasd or arrow keys", 20, 120);
		yengine.ds(g, "collect yellow orb avoid enemies ", 20, 140);
		//yengine.ds(g, "space to shot ", 20, 160);
		yengine.ds(g, "created by yoel fisher ", 20, 175);
	}
	
	



}
