
public abstract class Game {
	
	public abstract int getLevel();
	
	public abstract String checkValue(String guess);
	
	public abstract boolean gameOver();
	
	public abstract int getGuesses();
	
	public abstract int getMax();
	
	public abstract String getClue();
	
	public abstract int getRand();
}
