package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Exit implements KeyListener{
	
	//ECHAP 
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(KeyEvent.VK_ESCAPE == arg0.getExtendedKeyCode()) {
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
			
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		if(KeyEvent.VK_ESCAPE == arg0.getExtendedKeyCode()) {
			System.exit(0);
		}
	}

}
