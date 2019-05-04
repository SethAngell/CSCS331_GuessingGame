import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
import javax.swing.SwingConstants;

public class GameGUI extends JFrame {
	private static final long serialVersionUID = 70625052682885262L;

	public JPanel gameDisplay;
	public DefaultListModel<String> guesses;
	public DefaultListModel<String> clues;
	public JTextField textGuess;
	public JLabel playerLbl;
	public JLabel levelLbl;

//	public Statistics statsOBJ;

	public JTextField name = new JTextField();
	public String[] levels = {"Level 1", "Level 2", "Level 3", "Level 4", "Level 5"};
	public JComboBox<String> lvl = new JComboBox<String>(levels);
	public Object[] fields = {"Player Name:", name, "Level:", lvl};

	public Game currentGame;
	public Player currentPlayer;
	public ArrayList<Player> playerList = new ArrayList<Player>();

	public GameGUI(String title) throws IOException {
		super(title);
//		statsOBJ = new Statistics();
		setSize(500, 500);
		setLocation(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		playerList = statsOBJ.GetPlayerList();

		lvl.setSelectedIndex(-1);
		while(name.getText().trim().equals("") || name.getText().length() < 3 || name.getText().length() > 15 || lvl.getSelectedIndex() == -1) {
			int dialogResult = JOptionPane.showConfirmDialog(null, fields, "Enter name & select difficulty", JOptionPane.OK_CANCEL_OPTION); // Prompts user for a player name before starting the game.
			if(dialogResult == JOptionPane.CANCEL_OPTION)
				System.exit(0);
			else if(name.getText().trim().equals("") || name.getText().length() < 3 || name.getText().length() > 15) {
				JOptionPane.showMessageDialog(null, "Please create a valid name (3-15 characters).");
				name.setText("");
			}
			else if(lvl.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(null, "Please select a valid level.");
				lvl.setSelectedIndex(-1);
			}
		}
		currentPlayer = new Player(name.getText());
		playerList.add(currentPlayer);
		
		Integer lNum = Integer.parseInt(lvl.getSelectedItem().toString().substring(lvl.getSelectedItem().toString().length()-1));
		if (lNum == 1) {
			String maxInt = JOptionPane.showInputDialog("Please enter the upper bound: ");
			Integer upperBound = 0;
			upperBound += upperBound.parseInt(maxInt);
			currentGame = new OneGame(upperBound);
		}
		else
			currentGame = new OtherGame(lNum);
		createDisplay();
		setVisible(true);
	}
	public class LvlOneListener implements ActionListener { // Action listener for New Game -> Level 1
		public void actionPerformed(ActionEvent ae) {

//			currentPlayer.AddLevel(currentGame.getLevel());

			String maxInt = JOptionPane.showInputDialog("Please enter the upper bound: ");
			Integer upperBound = 0;
			upperBound += upperBound.parseInt(maxInt);
			currentGame = new OneGame(upperBound);
			levelLbl.setText("Level 1");
			textGuess.setText("");
			guesses.clear();
			clues.clear(); }}

	public class LvlTwoListener implements ActionListener { // Action listener for New Game -> Level 2
		public void actionPerformed(ActionEvent ae) {
			currentGame = new OtherGame(2);
			levelLbl.setText("Level " + currentGame.getLevel());
			textGuess.setText("");
			guesses.clear();
			clues.clear(); }}

	public class LvlThreeListener implements ActionListener { // Action listener for New Game -> Level 3
		public void actionPerformed(ActionEvent ae) {
			currentGame = new OtherGame(3);
			levelLbl.setText("Level " + currentGame.getLevel());
			textGuess.setText("");
			guesses.clear();
			clues.clear(); }}

	public class LvlFourListener implements ActionListener { // Action listener for New Game -> Level 4
		public void actionPerformed(ActionEvent ae) {
			currentGame = new OtherGame(4);
			levelLbl.setText("Level " + currentGame.getLevel());
			textGuess.setText("");
			guesses.clear();
			clues.clear(); }}

	public class LvlFiveListener implements ActionListener { // Action listener for New Game -> Level 5
		public void actionPerformed(ActionEvent ae) {
			currentGame = new OtherGame(5);
			levelLbl.setText("Level " + currentGame.getLevel());
			textGuess.setText("");
			guesses.clear();
			clues.clear(); }}

	public class StatsListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			StatsGUI statsDisplay = new StatsGUI("Statistics", playerList, currentPlayer);
			statsDisplay.addPanels();
		}

	}

	public class NewPlayerListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			name.setText("");
			lvl.setSelectedIndex(-1);
			while(name.getText().trim().equals("") || name.getText().length() < 3 || name.getText().length() > 15 || lvl.getSelectedIndex() == -1) {
				int dialogResult = JOptionPane.showConfirmDialog(null, fields, "Enter name & select difficulty", JOptionPane.OK_CANCEL_OPTION); // Prompts user for a player name before starting the game.
				if(dialogResult == JOptionPane.CANCEL_OPTION)
					return;
				else if(name.getText().trim().equals("") || name.getText().length() < 3 || name.getText().length() > 15) {
					JOptionPane.showMessageDialog(null, "Please create a valid name (3-15 characters).");
					name.setText("");
				}
				else if(lvl.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Please select a valid level.");
					lvl.setSelectedIndex(-1);
				}
			}
			currentPlayer = new Player(name.getText());
			playerLbl.setText(currentPlayer.getPlayerName());
			playerList.add(currentPlayer);
			Integer lNum = Integer.parseInt(lvl.getSelectedItem().toString().substring(lvl.getSelectedItem().toString().length()-1));
			if (lNum == 1) {
				String maxInt = JOptionPane.showInputDialog("Please enter the upper bound: ");
				Integer upperBound = 0;
				upperBound += upperBound.parseInt(maxInt);
				currentGame = new OneGame(upperBound);
			}
			else
				currentGame = new OtherGame(lNum);
			levelLbl.setText("Level " + currentGame.getLevel());
			guesses.clear();
			clues.clear();
		}
	}

	public class ClearListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			guesses.clear();
			clues.clear();
		}
	}


	public class EnterListener implements ActionListener { // Action listener for the Enter button.
		public void actionPerformed(ActionEvent ae) {
			String guess = textGuess.getText();

			if (currentGame instanceof OtherGame) {

				try {

				String result = currentGame.checkValue(guess);
				String clue = currentGame.getClue();
				guesses.addElement(guess);
				clues.addElement(clue);

				if (currentGame.gameOver()) {
					String endMessage = "You win! You took ";
					String guessesMessage = currentGame.getGuesses() + " guesses.";
					JOptionPane.showMessageDialog(null, endMessage + guessesMessage);

					int currentLevel = currentGame.getLevel();

					if (currentPlayer.levelsList.size() > 0){

					for (Levels lvl : currentPlayer.levelsList){

						if (lvl.level == currentLevel){
							lvl.addStats(currentGame.getGuesses());
							return;
							}
						}
					}

					Levels newLevel = new Levels(currentLevel);
					newLevel.addStats(currentGame.getGuesses());
					currentPlayer.levelsList.add(newLevel);

				}
				} catch (Exception NumberFormatException){
					JOptionPane.showMessageDialog(null, "Please enter the guess in the format #,#,.,.,.   ");
				}
			}
			else {

				try {

				currentGame.checkValue(guess);

				guesses.addElement(guess);
				clues.addElement(currentGame.getClue());

				if (currentGame.gameOver()) {
					String endMessage = "You win! You took ";
					String guessesMessage = currentGame.getGuesses() + " guesses.";
					JOptionPane.showMessageDialog(null, endMessage + guessesMessage);

					if (currentPlayer.levelsList.size() > 0){

					for (Levels lvl : currentPlayer.levelsList){

						if (lvl.level == 1){
							lvl.addStats(currentGame.getGuesses());
							return;
							}
						}
					}
					Levels newlvl = new Levels(1);
					newlvl.addStats(currentGame.getGuesses());
					currentPlayer.levelsList.add(newlvl);
			}
				}catch(Exception NumberFormatException) {
					JOptionPane.showMessageDialog(null, "Please enter valid input");
				}
			}
			textGuess.setText("");
		}
	}


	public void createDisplay() {

		JMenuBar mainMenuBar = new JMenuBar(); // Creates the menu bar which houses all menu options.
		JMenu menuNew = new JMenu("New"); // Creates the two sub menus that branch directly from the main menu bar.
		mainMenuBar.add(menuNew);
		JMenu menuStats = new JMenu("Statistics");
		mainMenuBar.add(menuStats);

		JMenu subMenuNewGame = new JMenu("New Game..."); // Creates and the menu and menu items within "New."
		menuNew.add(subMenuNewGame);
		JMenuItem newPlayer = new JMenuItem("New Player");
		newPlayer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
		menuNew.add(newPlayer);
		newPlayer.addActionListener(new NewPlayerListener());
		JMenuItem clear = new JMenuItem("Clear");
		clear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		clear.addActionListener(new ClearListener());
		menuNew.add(clear);

		JMenuItem lvlOne = new JMenuItem("Level 1"); // Creates sub-items within "New Game..."
		subMenuNewGame.add(lvlOne);
		//add action listener for lvlOne
		lvlOne.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		lvlOne.addActionListener(new LvlOneListener());
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

		JMenuItem stats = new JMenuItem("Show Stats..."); // Creates the menu and menu items within "Statistics."
		menuStats.add(stats);

		gameDisplay = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints(); // Sets constraints, must adjust to liking before adding to display.

		JLabel guessesLbl = new JLabel("Guesses:");
		guessesLbl.setHorizontalAlignment(SwingConstants.CENTER);
		guessesLbl.setFont(new Font("Comic Sans", Font.PLAIN, 25));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		gameDisplay.add(guessesLbl, c);

		JLabel cluesLbl = new JLabel("Clues:");
		cluesLbl.setHorizontalAlignment(SwingConstants.CENTER);
		cluesLbl.setFont(new Font("Comic Sans", Font.PLAIN, 25));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		gameDisplay.add(cluesLbl, c);


		guesses = new DefaultListModel<String>(); // Actual list that guesses are stored in.
		JList<String> listGuesses = new JList<String>(guesses); // listGuesses component contains the guesses list model.
		listGuesses.setFont(new Font("Arial", Font.BOLD, 16));
		JScrollPane guessPane = new JScrollPane(listGuesses); // guessPane contains the JList and allows scroll-ability.
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 300; // Sets height value constraint
		c.gridx = 0; // Position on grid in x direction
		c.gridy = 1; // Position on grid in y direction
		gameDisplay.add(guessPane, c); // Adds listGuesses to display, adhering to the constraints of the GridBag

		clues = new DefaultListModel<String>();
		JList<String> listClues = new JList<String>(clues);
		listClues.setFont(new Font("Arial", Font.BOLD, 16));
		JScrollPane cluePane = new JScrollPane(listClues);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		gameDisplay.add(cluePane, c);

		textGuess = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0; // Sets height value constraint
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		gameDisplay.add(textGuess, c);

		JButton enter = new JButton("Enter");
		enter.addActionListener(new EnterListener());
		getRootPane().setDefaultButton(enter);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		gameDisplay.add(enter, c);

		playerLbl = new JLabel(currentPlayer.getPlayerName());
		playerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		playerLbl.setFont(new Font("Comic Sans", Font.PLAIN, 25));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		gameDisplay.add(playerLbl, c);

		levelLbl = new JLabel("Level " + currentGame.getLevel());
		levelLbl.setHorizontalAlignment(SwingConstants.CENTER);
		levelLbl.setFont(new Font("Comic Sans", Font.PLAIN, 25));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		gameDisplay.add(levelLbl, c); // End of GridBag formatting.


		stats.addActionListener(new StatsListener());

//		addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				statsOBJ.CloseCurrentPlayer(currentPlayer);
//				currentPlayer = statsOBJ.NewPlayer( "Dumbo" );
//				try {
//					statsOBJ.SaveStatistics();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//
//		});


		add(gameDisplay);
		setJMenuBar(mainMenuBar);
	}

	public static void main(String[] args) throws IOException {
		GameGUI ui = new GameGUI("Numbers Game");
	}

}
