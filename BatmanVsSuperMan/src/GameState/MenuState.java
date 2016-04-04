package GameState;
import java.awt.*;
import TileMap.Background;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {
	
	private Background bg; 
	
	private String[] options = {
			"Start",
			"Quit"
	};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;

	private int currentChoice;
	
	public MenuState(GameStateManager gsm){
		this.gsm = gsm;
		
		try {
			bg = new Background("/Backgrounds/gotham.jpg", 1); //33:19
			bg.setVector(-0.1, 0);
			
			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Century Gothic", Font.TYPE1_FONT, 20);
			
			font = new Font("Arial", Font.TYPE1_FONT, 12);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public void init(){}
	public void update() {
		bg.update();
	}
	public void draw(Graphics2D g){
		bg.draw(g);
		
		g.setColor(titleColor.RED);
		g.setFont(titleFont);
		g.drawString("Batman vs Superman", 60, 60);
		//draw menu options
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.RED);
			}
			else{
				g.setColor(Color.YELLOW);
			}
			g.drawString(options[i], 145, 220 + i * 25);
			
			
		}
	}
	private void select(){
		if(currentChoice == 0){
			//start
		}
		if (currentChoice == 1) {
			System.exit(0);
		}
	}
	public void keyPressed(int k){
		if (k == KeyEvent.VK_ENTER) {
			select();
		}
		if (k == KeyEvent.VK_UP) {
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
				
			}
		}
		if (k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
				
			}
		}
	}
	public void keyReleased(int k){}

}