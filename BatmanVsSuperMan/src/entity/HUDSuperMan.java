package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import entity.Enemies.Superman;

public class HUDSuperMan {

	private Superman superman;
	private BufferedImage image;
	private Font font;

	public HUDSuperMan(Superman superManInput) {
		superman = superManInput;
		try {
			image = ImageIO.read(
					getClass().getResourceAsStream("/HUD/hudSuperman.gif"));
			font = new Font("Arial", Font.BOLD, 12);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g) {
		g.drawImage(image, 320 - image.getWidth(), 10, null);
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString(superman.getHealth() + "/" + superman.getMaxHealth(), 247, 24);
	}
}
