package src;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	
	private Handler handler;
	private GameObject tempPlayer = null;
	//boolean mouseDown = false;
	
	public MouseInput(Handler handler) {
		this.handler = handler;
	}

	public void findPlayer() {
		for(int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player) {
				tempPlayer = handler.object.get(i);
				break;
			}
		}
	}
	
	public void mousePressed(MouseEvent e) {
		//mouseDown = true;
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		//while (mouseDown) {
		if(tempPlayer != null) {
			GameObject tempBullet = handler.addObject(new Bullet(tempPlayer.x+16, tempPlayer.y+16, ID.Bullet));
			
			float angle = (float) Math.atan2(mouseY-tempPlayer.y-16, mouseX-tempPlayer.x-16);
			int bulletVel = 20;
			
			tempBullet.velX = (float) (bulletVel*Math.cos(angle));
			tempBullet.velY = (float) (bulletVel*Math.sin(angle));
			
		} else findPlayer();
		/*}
		
		public void mouseReleased(MouseEvent e) {
			mouseDown = false;
		}
		*/
	}

}
