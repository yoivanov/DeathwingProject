package gameState;

public class GameStateManager {
	
	private GameState[] gameStates;
	private int currentState;
	
	public static final int NUMGAMESTATES = 3;
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	public static final int GAMEOVER = 2;
	
	public GameStateManager() {	
		gameStates = new GameState[NUMGAMESTATES];
		currentState = MENUSTATE;
		loadState(currentState);
	}
	
	private void loadState(int state) {
		if(state == MENUSTATE){
			gameStates[state] = new MenuState(this);
		}
			
		if(state == LEVEL1STATE){
			gameStates[state] = new Level1State(this);
		}
		
		if(state == GAMEOVER){
			gameStates[state] = new GameOverState(this);
		}
	}
	
	private void unloadState(int state) {
		gameStates[state] = null;
	}
	
	public void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
	}
	
	public void update() {
		if (gameStates[currentState]!=null){
			try {
				gameStates[currentState].update();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void draw(java.awt.Graphics2D g) {
		if (gameStates[currentState]!=null){
			try {
				gameStates[currentState].draw(g);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void keyPressed(int k) {
		gameStates[currentState].keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates[currentState].keyReleased(k);
	}
}