package src;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
	
	public static final int HEIGHT = 800;
	public static final int WIDTH = 1200;
	
	public String title = "Dungeon Depths";
	
	private Thread thread;
	private boolean isRunning = false;
	
	private Handler handler;
	private KeyInput input;
	private MouseInput mouseInput;
	
	public Game() {
		new Window(WIDTH, HEIGHT, title, this);
		start();
		
		init();
		
		
	}
	/**
	 * 
	 */
	
	private void init() {
		handler = new Handler();
		input = new KeyInput();
		mouseInput = new MouseInput(handler);
		this.addKeyListener(input);
		this.addMouseListener(mouseInput);
		
		handler.addObject(new Player(100, 100, ID.Player, input));
		mouseInput.findPlayer();
	}
	/**
	 * 
	 */
	private synchronized void start() {
		if(isRunning) return;
		
		//create and set new thread
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	/**
	 * 
	 */
	private synchronized void stop() {
		if(!isRunning) return;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	//game loop
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		//update the game
		handler.tick();
		
	}
	
	private void render() {
		//render the game
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//Core rendering
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		handler.render(g);
		
		bs.show();
		g.dispose();
		
	}
	
	public static void main(String[] args) {
		new Game();
	}

}
