package model;

import java.awt.Color;
import java.awt.Graphics;

public class Door extends GameObject{
	
	public Door(float x, float y, ID id) {
		super(x, y, id);
		
	}

	@Override
	public void tick() {
		
		//System.out.println("Coord X: "+x+", Coord Y: "+y);
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x-10, (int)y, 20, 128);
		
	}

}
