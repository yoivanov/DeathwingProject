package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class SupermanHud {
	
	private Player Supperman;
	
	private BufferedImage image;
	private Font font;
	
	public SupermanHud(Player p) {
		Supperman = p;
		try {
			image = ImageIO.read(
				getClass().getResourceAsStream(
					"/HUD/hudSuperMan.gif"
				)
			);
			font = new Font("Arial", Font.BOLD, 14);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(image, 0, 10, null);
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString(
			Supperman.getHealth() + "/" + Supperman.getMaxHealth(),
			30,
			25
		);
		
		
	}
	
}