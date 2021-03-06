package entity.Enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import audio.AudioPlayer;
import entity.Animation;
import entity.Enemy;
import tileMap.TileMap;

public class Superman extends Enemy {
	
	private BufferedImage[] sprites;
	private HashMap<String, AudioPlayer> ssx;

	public Superman(TileMap tm) {
		super(tm);
		moveSpeed = 2.2;
		maxSpeed = 2.2;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;

		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;

		health = maxHealth = 100;
		damage = 2;

		try {
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream("/Sprites/Enemies/superman.gif"));

			sprites = new BufferedImage[3];
			for (int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(300);

		right = true;
		facingRight = true;
		ssx = new HashMap<String, AudioPlayer>();
		ssx.put("win", new AudioPlayer("/SFX/Win.mp3"));
		ssx.put("bye", new AudioPlayer("/SFX/Bye.mp3"));
	}

	private void getNextPosition() {
		// movement
		if (left) {
			dx -= moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else if (right) {
			dx += moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		}

		// falling
		if (falling) {
			dy += fallSpeed;
		}
	}

	public void update() {
		if (this.health == 0) {
			ssx.get("win").play();
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					System.exit(0);
				}
			}, 2 * 2500);
		}
		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		// check flinching
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed > 400) {
				flinching = false;
			}
		}

		// if it hits a wall, go other direction
		if (right && dx == 0) {
			right = false;
			left = true;
			facingRight = false;
		} else if (left && dx == 0) {
			right = true;
			left = false;
			facingRight = true;
		}

		// update animation
		animation.update();
	}

	public void draw(Graphics2D g) {
		setMapPosition();
		super.draw(g);
	}
}