import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class StatsGUI extends JFrame {
	private static final long serialVersionUID = -6194704301528590576L;
	
	
	boolean gameLevelPlayed;
	
	int globalHighest = 0;
	String highestUser= "";
	int globalLowest = 100;
	String lowestUser= "";
	double globalLowestAverage = 100.0;
	String lowestAvgUser= "";
	double globalHighestAverage = 0.0;
	String highestAvgUser= "";

	ArrayList<Player> playerList;
	Player currentPlayer;

	ButtonGroup leftBG = new ButtonGroup();
	ButtonGroup midBG = new ButtonGroup();
	
	JRadioButton currentUserButt = new JRadioButton("Current Player Stats");
	JRadioButton globalButt = new JRadioButton("All Players Stats");


	JRadioButton level1 = new JRadioButton("Level 1");
	JRadioButton level2 = new JRadioButton("Level 2");
	JRadioButton level3 = new JRadioButton("Level 3");
	JRadioButton level4 = new JRadioButton("Level 4");
	JRadioButton level5 = new JRadioButton("Level 5");


	DefaultListModel<String> stats = new DefaultListModel<String>();
	JScrollPane scroll = new JScrollPane();
	JList<String> statsList = new JList<String>(stats);


	public StatsGUI(String title, ArrayList<Player> playersList, Player currentPlayer) {

		super(title);
		setSize(500,425);
		this.playerList = playersList;
		this.currentPlayer = currentPlayer;

//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(1,3));
		setResizable(false);



	}

	public class currentPlayerStatsListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			
			
			JRadioButton check = getSelectedRadioButton(leftBG);
			
			if(check.getText().equals(currentUserButt.getText())) {

//			ScoreKeeper currentPlayerScoreKeeper = new ScoreKeeper(currentPlayer);

			System.out.println("Action Listnener engaged");
			System.out.println(level1.isSelected());

			JRadioButton levelCheck = getSelectedRadioButton(midBG);
			System.out.println(levelCheck.getText());
			if(levelCheck.getText().equals(level1.getText())) {
				
				stats.clear();

				String number = level1.getText().substring(6,7);
				Integer num=0;
				num += num.parseInt(number);
				
				stats.addElement(currentPlayer.getPlayerName());
				
				for(Levels l : currentPlayer.levelsList) {
					
					if (l.level == 1) {
						stats.addElement("Level 1: \n");
						stats.addElement("Highest Score: " + l.highestScore.toString());
						stats.addElement("Lowest Score: " + l.lowestScore.toString());
						stats.addElement("Average Guesses: " + l.getAverage());
					}
					
				}


			}else if (levelCheck.getText().equals(level2.getText())) {
				
				stats.clear();
				
				String number = level2.getText().substring(6,7);
				Integer num=0;
				num += num.parseInt(number);
				

				stats.addElement(currentPlayer.getPlayerName());
				
				for(Levels l : currentPlayer.levelsList) {
					
					if (l.level == 2) {
						stats.addElement("Level 2: \n");
						stats.addElement("Highest Score: " + l.highestScore.toString());
						stats.addElement("Lowest Score: " + l.lowestScore.toString());
						stats.addElement("Average Guesses: " + l.getAverage());
					}
					
				}			
				
			}else if(levelCheck.getText().equals(level3.getText())) {
				stats.clear();
				
				String number = level3.getText().substring(6,7);
				Integer num=0;
				num += num.parseInt(number);
				
				stats.addElement(currentPlayer.getPlayerName());
				
				for(Levels l : currentPlayer.levelsList) {
					
					if (l.level == 3) {
						stats.addElement("Level 3: \n");
						stats.addElement("Highest Score: " + l.highestScore.toString());
						stats.addElement("Lowest Score: " + l.lowestScore.toString());
						stats.addElement("Average Guesses: " + l.getAverage());
					}
					
				}
				
				
				
			}else if (levelCheck.getText().equals(level4.getText())) {
				stats.clear();
				String number = level4.getText().substring(6,7);
				Integer num=0;
				num += num.parseInt(number);
				
				stats.addElement(currentPlayer.getPlayerName());
				
				for(Levels l : currentPlayer.levelsList) {
					
					if (l.level == 4) {
						stats.addElement("Level 4: \n");
						stats.addElement("Highest Score: " + l.highestScore.toString());
						stats.addElement("Lowest Score: " + l.lowestScore.toString());
						stats.addElement("Average Guesses: " + l.getAverage());
					}
					
				}
				
				
			}else if (levelCheck.getText().equals(level5.getText())) {
				stats.clear();
				String number = level5.getText().substring(6,7);
				Integer num=0;
				num += num.parseInt(number);
				
				
				stats.addElement(currentPlayer.getPlayerName());
				
				for(Levels l : currentPlayer.levelsList) {
					
					if (l.level == 5) {
						stats.addElement("Level 5: \n");
						stats.addElement("Highest Score: " + l.highestScore.toString());
						stats.addElement("Lowest Score: " + l.lowestScore.toString());
						stats.addElement("Average Guesses: " + l.getAverage());
					}
					
				}	
			}
			
			}else if(check.getText().equals(globalButt.getText())){
				
				System.out.println("Global Butt touched");
				
				JRadioButton levelCheck = getSelectedRadioButton(midBG);
				System.out.println(levelCheck.getText());
				
				
				if(levelCheck.getText().equals(level1.getText())) {
					
					
					gameLevelPlayed = false;
					
					stats.clear();
					
//					globalHighest = 0;
//					highestUser= "";
//					globalLowest = 100;
//					lowestUser= "";
//					globalLowestAverage = 100.0;
//					lowestAvgUser= "";
//					globalHighestAverage = 0.0;
//					highestAvgUser= "";

					String number = level1.getText().substring(6,7);
					Integer num=0;
					num += num.parseInt(number);
					
					
					System.out.println(playerList.size());
					
					for(Player p : playerList) {
						for(Levels lvl : p.levelsList) {
							if(lvl.level == 1) {
								gameLevelPlayed = true;
								if(lvl.getHighestScore() > globalHighest) {
									globalHighest = lvl.highestScore;
									highestUser = p.getPlayerName();
									System.out.println("player name inside for each" + p.getPlayerName());
									System.out.println("new high score" + lvl.highestScore);
								}
								if(lvl.getLowestScore() < globalLowest) {
									globalLowest = lvl.lowestScore;
									lowestUser = p.getPlayerName();
								}
								if(lvl.getAverage() < globalLowestAverage) {
									globalLowestAverage = lvl.getAverage();
									lowestAvgUser = p.getPlayerName();
								}
								if(lvl.getAverage() > globalHighestAverage) {
									globalHighestAverage = lvl.getAverage();
									highestAvgUser = p.getPlayerName();
								}
								
							}
						}
					}
					if (gameLevelPlayed = true){
						stats.addElement("Level 1");
						stats.addElement("Worst Score: \n" + highestUser +" - "+ globalHighest);
						stats.addElement("Best Score: \n" + lowestUser + " - " + globalLowest);
						stats.addElement("Lowest Average: \n" + lowestAvgUser + " - " + globalLowestAverage);
						stats.addElement("Highest Average: \n" + highestAvgUser + " - "+ globalHighestAverage);
					}
					
					
				}else if (levelCheck.getText().equals(level2.getText())) {
					
					stats.clear();
					
					gameLevelPlayed = false;
					
					globalHighest = 0;
					highestUser= "";
					globalLowest = 100;
					lowestUser= "";
					globalLowestAverage = 100.0;
					lowestAvgUser= "";
					globalHighestAverage = 0.0;
					highestAvgUser= "";

					

					String number = level2.getText().substring(6,7);
					Integer num=0;
					num += num.parseInt(number);
					
					
					
					for(Player p : playerList) {
						for(Levels lvl : p.levelsList) {
							if(lvl.level == 2) {
								gameLevelPlayed = true;
								if(lvl.getHighestScore() > globalHighest) {
									globalHighest = lvl.highestScore;
									highestUser = p.getPlayerName();
								}
								if(lvl.getLowestScore() < globalLowest) {
									globalLowest = lvl.lowestScore;
									lowestUser = p.getPlayerName();
								}
								if(lvl.getAverage() < globalLowestAverage) {
									globalLowestAverage = lvl.getAverage();
									lowestAvgUser = p.getPlayerName();
								}
								if(lvl.getAverage() > globalHighestAverage) {
									globalHighestAverage = lvl.getAverage();
									highestAvgUser = p.getPlayerName();
								}
								
							}
						}
					}
					
					if (gameLevelPlayed != true) {
					stats.addElement("Level 2");
					stats.addElement("Worst Score: \n" + highestUser +" - "+ globalHighest);
					stats.addElement("Best Score: \n" + lowestUser + " - " + globalLowest);
					stats.addElement("Lowest Average: \n" + lowestAvgUser + " - " + globalLowestAverage);
					stats.addElement("Highest Average: \n" + highestAvgUser + " - "+ globalHighestAverage);
					}
					
				}else if(levelCheck.getText().equals(level3.getText())) {
					stats.clear();
					
					
					gameLevelPlayed = false;
					
					globalHighest = 0;
					highestUser= "";
					globalLowest = 100;
					lowestUser= "";
					globalLowestAverage = 100.0;
					lowestAvgUser= "";
					globalHighestAverage = 0.0;
					highestAvgUser= "";

					
					
					String number = level3.getText().substring(6,7);
					Integer num=0;
					num += num.parseInt(number);
					
					
					
					for(Player p : playerList) {
						for(Levels lvl : p.levelsList) {
							if(lvl.level == 3) {
								gameLevelPlayed = true;
								if(lvl.getHighestScore() > globalHighest) {
									globalHighest = lvl.highestScore;
									highestUser = p.getPlayerName();
								}
								if(lvl.getLowestScore() < globalLowest) {
									globalLowest = lvl.lowestScore;
									lowestUser = p.getPlayerName();
								}
								if(lvl.getAverage() < globalLowestAverage) {
									globalLowestAverage = lvl.getAverage();
									lowestAvgUser = p.getPlayerName();
								}
								if(lvl.getAverage() > globalHighestAverage) {
									globalHighestAverage = lvl.getAverage();
									highestAvgUser = p.getPlayerName();
								}
								
							}
						}
					}
					if (gameLevelPlayed != true) {
					stats.addElement("Level 3");
					stats.addElement("Worst Score: \n" + highestUser +" - "+ globalHighest);
					stats.addElement("Best Score: \n" + lowestUser + " - " + globalLowest);
					stats.addElement("Lowest Average: \n" + lowestAvgUser + " - " + globalLowestAverage);
					stats.addElement("Highest Average: \n" + highestAvgUser + " - "+ globalHighestAverage);
					}
					
				}else if (levelCheck.getText().equals(level4.getText())) {
					stats.clear();
					
					gameLevelPlayed = false;
					
					globalHighest = 0;
					highestUser= "";
					globalLowest = 100;
					lowestUser= "";
					globalLowestAverage = 100.0;
					lowestAvgUser= "";
					globalHighestAverage = 0.0;
					highestAvgUser= "";

					
					
					String number = level4.getText().substring(6,7);
					Integer num=0;
					num += num.parseInt(number);
					
					
					
					for(Player p : playerList) {
						for(Levels lvl : p.levelsList) {
							if(lvl.level == 4) {
								gameLevelPlayed = true;
								if(lvl.getHighestScore() > globalHighest) {
									globalHighest = lvl.highestScore;
									highestUser = p.getPlayerName();
								}
								if(lvl.getLowestScore() < globalLowest) {
									globalLowest = lvl.lowestScore;
									lowestUser = p.getPlayerName();
								}
								if(lvl.getAverage() < globalLowestAverage) {
									globalLowestAverage = lvl.getAverage();
									lowestAvgUser = p.getPlayerName();
								}
								if(lvl.getAverage() > globalHighestAverage) {
									globalHighestAverage = lvl.getAverage();
									highestAvgUser = p.getPlayerName();
								}
								
							}
						}
					}
					
					if (gameLevelPlayed != true) {
					stats.addElement("Level 4");
					stats.addElement("Worst Score: \n" + highestUser +" - "+ globalHighest);
					stats.addElement("Best Score: \n" + lowestUser + " - " + globalLowest);
					stats.addElement("Lowest Average: \n" + lowestAvgUser + " - " + globalLowestAverage);
					stats.addElement("Highest Average: \n" + highestAvgUser + " - "+ globalHighestAverage);
					}
					
				}else if (levelCheck.getText().equals(level5.getText())) {
					stats.clear();
					
					gameLevelPlayed = false;
					
					globalHighest = 0;
					highestUser= "";
					globalLowest = 100;
					lowestUser= "";
					globalLowestAverage = 100.0;
					lowestAvgUser= "";
					globalHighestAverage = 0.0;
					highestAvgUser= "";
										
					String number = level5.getText().substring(6,7);
					Integer num=0;
					num += num.parseInt(number);
					
					for(Player p : playerList) {
						for(Levels lvl : p.levelsList) {
							if(lvl.level == 5) {
								gameLevelPlayed = true;
								if(lvl.getHighestScore() > globalHighest) {
									globalHighest = lvl.highestScore;
									highestUser = p.getPlayerName();
								}
								if(lvl.getLowestScore() < globalLowest) {
									globalLowest = lvl.lowestScore;
									lowestUser = p.getPlayerName();
								}
								if(lvl.getAverage() < globalLowestAverage) {
									globalLowestAverage = lvl.getAverage();
									lowestAvgUser = p.getPlayerName();
								}
								if(lvl.getAverage() > globalHighestAverage) {
									globalHighestAverage = lvl.getAverage();
									highestAvgUser = p.getPlayerName();
								}
								
							}
						}
					}
					if (gameLevelPlayed = true) {
					stats.addElement("Level 5");
					stats.addElement("Worst Score: \n" + highestUser +" - "+ globalHighest);
					stats.addElement("Best Score: \n" + lowestUser + " - " + globalLowest);
					stats.addElement("Lowest Average: \n" + lowestAvgUser + " - " + globalLowestAverage);
					stats.addElement("Highest Average: \n" + highestAvgUser + " - "+ globalHighestAverage);
					}
				}
			}


		}

	}

	private JRadioButton getSelectedRadioButton(ButtonGroup bg) {
		Enumeration<AbstractButton> abs = bg.getElements();
		JRadioButton radButton = null;
		while(abs.hasMoreElements()) {
			radButton = (JRadioButton) abs.nextElement();
			if(radButton.isSelected())
				break;
		}
		return radButton;

	}

	public void addPanels() {

		stats = new DefaultListModel<String>();
		JList<String> statsList = new JList<String>(stats);
		JScrollPane scroll = new JScrollPane(statsList);

		JPanel leftPanel = new JPanel();
		JPanel midPanel = new JPanel();
		JPanel rightPanel = new JPanel();

		ButtonGroup rightBG = new ButtonGroup();

		leftBG.add(currentUserButt);
		leftBG.add(globalButt);

		leftPanel.add(currentUserButt);
		leftPanel.add(globalButt);


		leftPanel.setLayout(new GridLayout(2,1));
		midPanel.setLayout(new GridLayout(5,1));
		rightPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 350;
		c.ipadx = 150;

		JRadioButton level1 = new JRadioButton("Level 1");
		JRadioButton level2 = new JRadioButton("Level 2");
		JRadioButton level3 = new JRadioButton("Level 3");
		JRadioButton level4 = new JRadioButton("Level 4");
		JRadioButton level5 = new JRadioButton("Level 5");

		midBG.add(level1);
		midBG.add(level2);
		midBG.add(level3);
		midBG.add(level4);
		midBG.add(level5);

		midPanel.add(level1);
		midPanel.add(level2);
		midPanel.add(level3);
		midPanel.add(level4);
		midPanel.add(level5);

		rightPanel.add(scroll,c);

		level1.addActionListener(new currentPlayerStatsListener());
		level2.addActionListener(new currentPlayerStatsListener());
		level3.addActionListener(new currentPlayerStatsListener());
		level4.addActionListener(new currentPlayerStatsListener());
		level5.addActionListener(new currentPlayerStatsListener());

		add(leftPanel);
		add(midPanel);
		add(rightPanel);

		setVisible(true);
	}

}
