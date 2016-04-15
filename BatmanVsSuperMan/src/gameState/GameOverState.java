package gameState;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import gameState.Level1State;
import tileMap.Background;
public class GameOverState extends GameState {
	
	private Background bg;
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	public GameOverState(GameStateManager gsm) {
		this.gsm = gsm;
		try {
		bg = new Background("/Backgrounds/game_over.jpeg", 1);
		bg.setVector(-0.1, 0);
		
		//titleColor = new Color(128, 0, 0);
		//titleFont = new Font("", Font.TYPE1_FONT, 20);
		
		//font = new Font("Arial", Font.TYPE1_FONT, 12);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		bg.update();
		
	}


	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}
}
