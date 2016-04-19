package gameState;

import java.awt.*;
import java.awt.event.KeyEvent;

import tileMap.Background;

public class GameOverState extends GameState {
	
	private Background bg;
	
	public GameOverState(GameStateManager gsm) {
		this.gsm = gsm;
		
		try {
			bg = new Background("/Backgrounds/game_over.jpeg", 1);
			bg.setVector(-0.1, 0);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g) {
		// draw bg
		bg.draw(g);
	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}
	
	public void init() {
	}
	
	public void update() {
	}

	public void keyReleased(int k) {	
	}
}