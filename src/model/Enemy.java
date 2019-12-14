package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Enemy extends GameObject {
	
	private Handler handler;
	
	float distance, diffX, diffY;
	private int width = 32;
	private int height = 32;
	private float _acc = 1f;

	public Enemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	

	@Override
	public void tick() {
		x+= velX;
		y+= velY;
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				diffX = x - tempObject.getX() - width;
				diffY = y - tempObject.getY() - height;
				distance = (float) Math.sqrt((x - tempObject.getX()) * (x - tempObject.getX()) + (y - tempObject.getY()) * (y - tempObject.getY()));
			}
		}
		
		
		
		velX += ((-1 / distance) * diffX);
		velY += ((-1 / distance) * diffY);
		
		velX = clamp(velX, 3, -3);
		velY = clamp(velY, 3, -3);
	}
	
	private float clamp(float value, float max, float min) {
		if(value >= max) value = max;
		else if(value <= min) value = min;
		
		return value;
	}
		
	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, width, height);

	}

}
