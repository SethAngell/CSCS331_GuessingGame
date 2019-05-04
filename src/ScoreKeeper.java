import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScoreKeeper {
	
	List<String[]> HighScores = new ArrayList<String[]>();
	List<String[]> LowScores = new ArrayList<String[]>();
	String[] TimePlayed = new String[2];
	List<String[]> AverageScores = new ArrayList<String[]>();
	List<String[]> TotalGuesses = new ArrayList<String[]>();
	
	
	
	public ScoreKeeper( Player CurrentPlayer ) {
		
		String[] TimePlayedList = { CurrentPlayer.getPlayerName(), CurrentPlayer.timePlayed.toString()};
		TimePlayed = TimePlayedList;
		ArrayList<Levels> Scores = CurrentPlayer.levelsList;
		
		for ( Levels L : Scores ) {
			L.getAverage();
			L.getHighestScore();
			L.getLowestScore();
			
			String[] LowScore = { CurrentPlayer.getPlayerName(), new Integer(L.lowestScore).toString() };
			String[] HighScore = { CurrentPlayer.getPlayerName(), new Integer(L.highestScore).toString() };
			String[] AverageScore = { CurrentPlayer.getPlayerName(), new Double(L.avgGuesses).toString() };
			String[] TotalGuess = { CurrentPlayer.getPlayerName(), new Integer(L.totalGuesses).toString() };
			
			HighScores.add(L.level - 1, HighScore);
			LowScores.add(L.level -1, LowScore);
			AverageScores.add(L.level-1, AverageScore);
			TotalGuesses.add(L.level-1, TotalGuess);
			
		}
			
		}
	
	public ScoreKeeper( ArrayList<Player> PlayerList ) {
		for ( int x = 1 ; x < 6 ; x ++ ) {
			try {
				HighScores.add(x -1, FindHighest(PlayerList, x));
				LowScores.add(x - 1 ,FindLowest(PlayerList, x));
				TimePlayed = LongestPlayed(PlayerList);
				AverageScores.add(x - 1, ComputeAverage(PlayerList, x));
				TotalGuesses.add(x -1 , HighestTotal(PlayerList, x));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private String[] FindHighest( ArrayList<Player> Players, int TargetedLevel ) {
		String[] CurrentWinner = { "Frank", "0" };
		
		for ( Player P : Players ) {
			for ( Levels L : P.levelsList ) {
				if ( ( L.level == TargetedLevel ) && ( L.highestScore > Integer.parseInt( CurrentWinner[1] ) ) ) {
					CurrentWinner[0] = P.getPlayerName();
					CurrentWinner[1] = new Integer(L.highestScore).toString();
				}
			}
		}
		
		return CurrentWinner;
	}
	
	private String[] FindLowest( ArrayList<Player> Players, int TargetedLevel ) {
		String[] CurrentWinner = { "Frank", "100000000" };
		
		for ( Player P : Players ) {
			for ( Levels L : P.levelsList ) {
				if ( ( L.level == TargetedLevel ) && ( L.lowestScore < Integer.parseInt( CurrentWinner[1] ) ) ) {
					CurrentWinner[0] = P.getPlayerName();
					CurrentWinner[1] = new Integer(L.lowestScore).toString();
				}
			}
		}
		
		return CurrentWinner;
	}
	private String[] ComputeAverage( ArrayList<Player> Players, int TargetedLevel ) {
		String[] CurrentWinner = { "Null", "0" };
		Double Average = (double) 0;
		double index = 0;
		
		for ( Player P : Players ) {
			for ( Levels L : P.levelsList ) {
				if ( L.level == TargetedLevel  ) {
					Average+=L.avgGuesses;
					index++;
				}
			}
		}
		
		if ( index > 0 ) { Average = Average / index; };
		CurrentWinner[1] = Average.toString();
		
		return CurrentWinner;
	}
	
	private String[] HighestTotal( ArrayList<Player> Players, int TargetedLevel ) {
		String[] CurrentWinner = { "Frank", "0" };
		
		for ( Player P : Players ) {
			for ( Levels L : P.levelsList ) {
				if ( ( L.level == TargetedLevel ) && ( L.totalGuesses > Integer.parseInt( CurrentWinner[1] ) ) ) {
					CurrentWinner[0] = P.getPlayerName();
					CurrentWinner[1] = new Integer(L.totalGuesses).toString();
				}
			}
		}
		
		return CurrentWinner;
	}
	
	private String[] LongestPlayed( ArrayList<Player> Players) {
		String CurrentName = "Frank";
		Long CurrentLongest = (long) 0;
		
		for ( Player P : Players ) {
			if ( P.timePlayed > CurrentLongest ) { CurrentName = P.getPlayerName(); CurrentLongest = P.timePlayed; };
		}
		
		String[] Results = { CurrentName, CurrentLongest.toString() };
		
		return Results;
	}
	
	public ArrayList<String[]> UniversalGetter( int DesiredLevel ) {
		
		ArrayList<String[]> StatsPackage = new ArrayList<String[]>();
		
		try {
			StatsPackage.add(HighScores.get(DesiredLevel));
			StatsPackage.add(LowScores.get(DesiredLevel));
			StatsPackage.add(AverageScores.get(DesiredLevel));
			StatsPackage.add(TimePlayed);
		} catch ( Exception e ) {
			e.printStackTrace();
			String[] NullPointer = {"No level data found", "No level data found"};
			for ( int x = 0; x < 5; x++ ) {
				StatsPackage.add(NullPointer);
			}
		}
		return StatsPackage;
	}
}
