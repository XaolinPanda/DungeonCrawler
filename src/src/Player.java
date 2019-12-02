package src;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {

	private int playerW = 32;
	private int playerH = 32;
	private float _acc = 1f;
	private float _dcc = 0.5f;
	private KeyInput input;
	
	public Player(float x, float y, ID id, KeyInput input) {
		super(x, y, id);
		this.input = input;
		
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(x + 38 > Game.WIDTH) {
			x = Game.WIDTH - 38;
			velX = 0;
		};
		if(x < 0) {
			x = 0;
			velX = 0;
		};
		if(y + 66 > Game.HEIGHT) {
			y = Game.HEIGHT - 66;
			velY = 0;
		};
		if(y < 0) {
			y = 0;
			velY = 0;
		};
			
		//horizontal
		//keys 0 = turn right
		//keys 1 = turn left
		if(input.keys[0]) velX += _acc;
		else if(input.keys[1]) velX -= _acc;
		else if(!input.keys[0] && !input.keys[1]) {
			
			if(velX > 0) velX -= _dcc;
			else if(velX < 0) velX += _dcc;
		}
		
		//vertical
		//keys 2 = turn up
		//keys 3 = turn down
		if(input.keys[2]) velY -= _acc;
		else if(input.keys[3]) velY += _acc;
		else if(!input.keys[2] && !input.keys[3]) {
			
			if(velY > 0) velY -= _dcc;
			else if(velY < 0) velY += _dcc;
		}
		
		//System.out.println("x: "+x+" | y: "+y);
		//System.out.println("velX: "+velX+" | velY: "+velY);
		
		velX = clamp(velX, 12, -12);
		velY = clamp(velY, 12, -12);
	}
		
	private float clamp(float value, float max, float min) {
		if(value >= max) value = max;
		else if(value <= min) value = min;
		
		return value;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, playerW, playerH);
		
	}
	

}
