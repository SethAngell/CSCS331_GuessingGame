import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class GameGUI extends JFrame {
	private static final long serialVersionUID = 70625052682885262L;

	public JPanel display;
	public DefaultListModel<String> guesses;
	public DefaultListModel<String> clues;
	public JTextField textGuess;
	public JLabel playerLbl;
	public JLabel levelLbl;
	public JCheckBoxMenuItem theme;
	
	public JTextField name = new JTextField();
	public String[] levels = {"Level 1", "Level 2", "Level 3", "Level 4", "Level 5"};
	public JComboBox<String> lvl = new JComboBox<String>(levels);
	public Object[] fields = {"Player Name:", name, "Level:", lvl};

	public OtherGame currentGame;
	public Player currentPlayer;
	public ArrayList<Player> playerList;

	public GameGUI(String title) {
		super(title);
		setSize(500, 500);
		setLocation(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		playerList = new ArrayList<Player>();
		
		lvl.setSelectedIndex(-1);
		int dialogResult = JOptionPane.showConfirmDialog(null, fields, "Enter name & select difficulty", JOptionPane.OK_CANCEL_OPTION); // Prompts user for a player name before starting the game.
		if(dialogResult == JOptionPane.CANCEL_OPTION)
			System.exit(0);
		else {
			currentPlayer = new Player(name.getText());
			playerList.add(currentPlayer);
			Integer lNum = Integer.parseInt(lvl.getSelectedItem().toString().substring(lvl.getSelectedItem().toString().length()-1));
			currentGame = new OtherGame(lNum);
			currentPlayer.addOtherGame(currentGame);
			createDisplay();
			setVisible(true);
		}
	}

	public class LvlTwoListener implements ActionListener { // Action listener for New Game -> Level 2
		public void actionPerformed(ActionEvent ae) {
			currentGame = new OtherGame(2);
			levelLbl.setText("Level " + currentGame.getLevel());
			currentPlayer.addOtherGame(currentGame);
			guesses.clear();
			clues.clear(); }}

	public class LvlThreeListener implements ActionListener { // Action listener for New Game -> Level 3
		public void actionPerformed(ActionEvent ae) {
			currentGame = new OtherGame(3);
			levelLbl.setText("Level " + currentGame.getLevel());
			currentPlayer.addOtherGame(currentGame);
			guesses.clear();
			clues.clear(); }}

	public class LvlFourListener implements ActionListener { // Action listener for New Game -> Level 4
		public void actionPerformed(ActionEvent ae) {
			currentGame = new OtherGame(4);
			levelLbl.setText("Level " + currentGame.getLevel());
			currentPlayer.addOtherGame(currentGame);
			guesses.clear();
			clues.clear(); }}

	public class LvlFiveListener implements ActionListener { // Action listener for New Game -> Level 5
		public void actionPerformed(ActionEvent ae) {
			currentGame = new OtherGame(5);
			levelLbl.setText("Level " + currentGame.getLevel());
			currentPlayer.addOtherGame(currentGame);
			guesses.clear();
			clues.clear(); }}

	public class NewPlayerListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			name.setText("");
			lvl.setSelectedIndex(-1);
			int dialogResult = JOptionPane.showConfirmDialog(null, fields, "Enter name & select difficulty", JOptionPane.OK_CANCEL_OPTION); // Prompts user for a player name before starting the game.
			if(dialogResult == JOptionPane.CANCEL_OPTION)
				System.exit(0);
			else {
				currentPlayer = new Player(name.getText());
				playerLbl.setText(currentPlayer.getPlayerName());
				playerList.add(currentPlayer);
				Integer lNum = Integer.parseInt(lvl.getSelectedItem().toString().substring(lvl.getSelectedItem().toString().length()-1));
				currentGame = new OtherGame(lNum);
				levelLbl.setText(lvl.getSelectedItem().toString());
				currentPlayer.addOtherGame(currentGame);
				guesses.clear();
				clues.clear();
			}
		}
	}
	
	public class ClearListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			guesses.clear();
			clues.clear();
		}
	}
	
	public class ThemeListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
				if(theme.isSelected()) {
					try {
		                UIManager.setLookAndFeel("javax.swing.plaf.hifi.HiFiLookAndFeel");
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
				}
				if(!theme.isSelected()) {
					try {
			            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
				}
				SwingUtilities.updateComponentTreeUI(this);
		} 
	}

	public class EnterListener implements ActionListener { // Action listener for the Enter button.
		public void actionPerformed(ActionEvent ae) {
			String guess = textGuess.getText();

			if (currentGame instanceof OtherGame) {

				String result = currentGame.checkValue(guess);
				String clue = currentGame.getClue();
				guesses.addElement(guess);
				clues.addElement(clue);

				if (currentGame.gameOver()) {
					String endMessage = "You win! You took ";
					String guessesMessage = currentGame.getGuesses() + " guesses.";

					if (currentPlayer.levelsList.contains(currentGame.getLevel())) {
						int gameLvl = currentGame.getLevel();
						currentPlayer.levelsList.get(gameLvl).addStats(currentGame.getGuesses());
					}
					else{
						Levels lvl = new Levels(currentGame.getLevel());
						lvl.addStats(currentGame.getGuesses());
						currentPlayer.levelsList.add(lvl);
					}

					JOptionPane.showMessageDialog(null, endMessage + guessesMessage);

					JOptionPane.showMessageDialog(null, currentPlayer.levelsList.size());
//					JOptionPane.showMessageDialog(null, currentGame.getLevel());
//					currentPlayer.addOtherGame(currentGame);
				}
			}
			else {


			}

		}
	}

	public void createDisplay() {

		JMenuBar mainMenuBar = new JMenuBar(); // Creates the menu bar which houses all menu options.
		JMenu menuNew = new JMenu("New"); // Creates the two sub menus that branch directly from the main menu bar.
		mainMenuBar.add(menuNew);
		JMenu menuStats = new JMenu("Statistics");
		mainMenuBar.add(menuStats);
		JMenu menuOther = new JMenu("Other");
		mainMenuBar.add(menuOther);

		JMenu subMenuNewGame = new JMenu("New Game..."); // Creates and the menu and menu items within "New."
		menuNew.add(subMenuNewGame);
		JMenuItem newPlayer = new JMenuItem("New Player");
		newPlayer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
		menuNew.add(newPlayer);
		newPlayer.addActionListener(new NewPlayerListener());
		JMenuItem clear = new JMenuItem("Clear");
		clear.addActionListener(new ClearListener());
		menuNew.add(clear);

		JMenuItem lvlOne = new JMenuItem("Level 1"); // Creates sub-items within "New Game..."
		subMenuNewGame.add(lvlOne);
		//add action listener for lvlOne
		lvlOne.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		JMenuItem lvlTwo = new JMenuItem("Level 2"); // Create menu item.
		lvlTwo.addActionListener(new LvlTwoListener()); // Add appropriate listener.
		lvlTwo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		subMenuNewGame.add(lvlTwo); // Add to sub-menu.
		JMenuItem lvlThree = new JMenuItem("Level 3");
		lvlThree.addActionListener(new LvlThreeListener());
		lvlThree.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		subMenuNewGame.add(lvlThree);
		JMenuItem lvlFour = new JMenuItem("Level 4");
		lvlFour.addActionListener(new LvlFourListener());
		lvlFour.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
		subMenuNewGame.add(lvlFour);
		JMenuItem lvlFive = new JMenuItem("Level 5");
		lvlFive.addActionListener(new LvlFiveListener());
		lvlFive.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.ALT_MASK));
		subMenuNewGame.add(lvlFive);

		JCheckBoxMenuItem time = new JCheckBoxMenuItem("Time"); // Creates the menu and menu items within "Statistics."
		menuStats.add(time);
		JCheckBoxMenuItem numPlays = new JCheckBoxMenuItem("# of Plays");
		menuStats.add(numPlays);
		JCheckBoxMenuItem topPlayer = new JCheckBoxMenuItem("Top Player");
		menuStats.add(topPlayer);
		JCheckBoxMenuItem mostDifGame = new JCheckBoxMenuItem("Most Difficult Game");
		menuStats.add(mostDifGame);
		
		theme = new JCheckBoxMenuItem("Dark Mode"); // Creates the menu items within "Other."
		theme.addActionListener(new ThemeListener());
		menuOther.add(theme);

		display = new JPanel(); // Creates display and sets layout as GridBag, below sets constraints for formatting.
		display.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints(); // Sets constraints, must adjust to liking before adding to display.

		guesses = new DefaultListModel<String>(); // Actual list that guesses are stored in.
		JList<String> listGuesses = new JList<String>(guesses); // listGuesses component contains the guesses list model.
		JScrollPane guessPane = new JScrollPane(listGuesses); // guessPane contains the JList and allows scroll-ability.
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 300; // Sets height value constraint
		c.gridx = 0; // Position on grid in x direction
		c.gridy = 0; // Position on grid in y direction
		display.add(guessPane, c); // Adds listGuesses to display, adhering to the constraints of the GridBag

		clues = new DefaultListModel<String>();
		JList<String> listClues = new JList<String>(clues);
		JScrollPane cluePane = new JScrollPane(listClues);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		display.add(cluePane, c);

		textGuess = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0; // Sets height value constraint
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		display.add(textGuess, c);

		JButton enter = new JButton("Enter");
		enter.addActionListener(new EnterListener());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		display.add(enter, c);

		playerLbl = new JLabel(currentPlayer.getPlayerName());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		display.add(playerLbl, c); // End of GridBag formatting.

		levelLbl = new JLabel("Level " + currentGame.getLevel());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		display.add(levelLbl, c); // End of GridBag formatting.

		add(display);
		setJMenuBar(mainMenuBar);
	}
	
	public void createStatsDisplay() {
		
	}
}
