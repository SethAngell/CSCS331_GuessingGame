import java.util.ArrayList;

public class Levels {
	
	ArrayList<Integer> scores = new ArrayList<Integer>();
	
	Integer level;
	Integer lowestScore = 100;
	Integer highestScore = 0;
	Integer totalGuesses = 0;
	double avgGuesses = 0.0;
	
	public Levels(int level) {
		this.level = level;
	}
	
	public void addStats(int guesses) {
		System.out.println(guesses);
		this.scores.add(guesses);
		System.out.println(scores.size());
	}
	
	public int getLowestScore() {
		
		for (Integer i : scores) {
			if(i < lowestScore) {
				lowestScore = i;
			}
		}
		
		return lowestScore;
	}
	
	public int getHighestScore() {
		for(Integer i : scores) {
			if(i > highestScore) {
				highestScore =i;
			}
		}
		return highestScore;
	}
	
	public double getAverage() {
		
		totalGuesses = 0;
		
		for(int s : scores) {
			totalGuesses = totalGuesses + s;
		}
	
		
		int size = scores.size();
		
		Double dbl = 0.0;
		
		dbl += size;
		
		avgGuesses = totalGuesses / dbl;
		
		return avgGuesses;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void setScores ( ArrayList<Integer> newScores ) {
		this.scores = newScores;
	}

}
