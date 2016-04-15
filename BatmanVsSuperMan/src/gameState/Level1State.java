package gameState;

import audio.AudioPlayer;
import entity.*;
import entity.Enemies.*;
import main.GamePanel;
import tileMap.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Level1State extends GameState {
	
	private TileMap tileMap;
	private Background bg;

	Player player;
	
	private ArrayList<Enemy> enemies;
	private ArrayList<Explosion> explosions;
	
	private HUD hud;
	private HUDSuperMan hudSuperMan;
	
	private AudioPlayer bgMusic;
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init() {
		
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/urbantileset.gif");
		tileMap.loadMap("/Maps/level1-1.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		bg = new Background("/Backgrounds/night-city.gif", 0.1);
		
		player = new Player(tileMap);
		player.setPosition(100, 100);
		
		populateEnemies();
		
		explosions = new ArrayList<Explosion>();
		
		hud = new HUD(player);
		
		
		bgMusic = new AudioPlayer("/Music/level1-1.mp3");
		bgMusic.play();
		
	}
	
	private void populateEnemies() {
		
		enemies = new ArrayList<Enemy>();
		Supperman s;
		
		Point[] points = new Point[]{
				new Point(2800,100)
		};
		for(int i = 0; i < points.length; i++) {
			s = new Supperman(tileMap);
			s.setPosition(points[i].x, points[i].y);
			enemies.add(s);
			hudSuperMan = new HUDSuperMan(s);
		}
		
		
		Slugger s1;
		Point[] points1 = new Point[] {
			new Point(200, 100),
			new Point(860, 200),
			new Point(1525, 200),
			new Point(1680, 200),
			new Point(1800, 200),
			
		};
		for(int i = 0; i < points1.length; i++) {
			s1 = new Slugger(tileMap);
			s1.setPosition(points1[i].x, points1[i].y);
			enemies.add(s1);
		}
		
	}
	
	public void update() {
		if(player.isDead()){
			enemies = new ArrayList<Enemy>();
			tileMap = new TileMap(30);
			player = new Player(tileMap);
			bg = new Background("/Backgrounds/game_over.jpeg", 1);
		}
		
		// update player
		player.update();
		tileMap.setPosition(
			GamePanel.WIDTH / 2 - player.getx(),
			GamePanel.HEIGHT / 2 - player.gety()
		);
		
		// set background
		bg.setPosition(tileMap.getx(), tileMap.gety());
		
		// attack enemies
		player.checkAttack(enemies);
		
		// update all enemies
		for(int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.update();
			if(e.isDead()) {
				enemies.remove(i);
				i--;
				explosions.add(
					new Explosion(e.getx(), e.gety()));
			}
		}
		
		// update explosions
		for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
			if(explosions.get(i).shouldRemove()) {
				explosions.remove(i);
				i--;
			}
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		// draw bg
		bg.draw(g);
		
		// draw tilemap
		tileMap.draw(g);
		
		// draw player
		player.draw(g);
		
		// draw enemies
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}
		
		// draw explosions
		for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).setMapPosition(
				(int)tileMap.getx(), (int)tileMap.gety());
			explosions.get(i).draw(g);
		}
		
		// draw hud
		hud.draw(g);
		
		// draw hud
		hudSuperMan.draw(g);
		
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT && !player.isDead()) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT && !player.isDead()) player.setRight(true);
		//if(k == KeyEvent.VK_UP) player.setUp(true);
		//if(k == KeyEvent.VK_DOWN) player.setDown(true);
		if(k == KeyEvent.VK_UP && !player.isDead()) player.setJumping(true);
		if(k == KeyEvent.VK_E && !player.isDead()) player.setGliding(true);
		if(k == KeyEvent.VK_W && !player.isDead()) player.setScratching();
		if(k == KeyEvent.VK_SPACE && !player.isDead()) player.setFiring();
		if(k == KeyEvent.VK_ENTER) {
			gsm.setState(GameStateManager.MENUSTATE);
			bgMusic.stop();
		}
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT && !player.isDead()) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT && !player.isDead()) player.setRight(false);
		//if(k == KeyEvent.VK_UP) player.setUp(false);
		//if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_UP && !player.isDead()) player.setJumping(false);
		if(k == KeyEvent.VK_E && !player.isDead()) player.setGliding(false);
		if(k == KeyEvent.VK_W && !player.isDead()) player.setScratching();
		if(k == KeyEvent.VK_SPACE && !player.isDead()) player.setFiring();
	}
	
}












