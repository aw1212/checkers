package checkers;

import java.applet.Applet;
import java.util.*;

public class CheckersSearch extends Applet 
{
	
	private String version; //Player v AI or AI v AI
	private String level; // set difficulty easy medium hard 
	
	public CheckersSearch(String version, String level)
	{
		this.version = version;
		this.level = level;
	}
	
	CheckersSpace space = new CheckersSpace();
	
	String name(String player) 
	{
		String s = "_";
		if (player == "A") 
		{
			s = "A"; 
		}
		else if (player == "B") 
		{
			s = "B"; 
		}
		return s;
	}
	
	void pr(String s) 
	{
		System.out.print(s);
	}
	
	void printState(Node node) //print board state
	{ 
		System.out.println("\n");
		for (int c = 0; c < 8; c++) 
		{
		for (int r = 0; r < 8; r++) 
		{
			String tile = node.state[r][c];
			if (tile.equals("o")) 
			{
				tile = "."; 
			}
			pr(tile);		
		}
			pr("\n");
		}
	}
	
	public String otherPlayer(String player) //switch player
	{
		String otherPlayer = null;
		if (player == "A") 
		{
			otherPlayer = "B"; 
		}
		else if (player == "B") 
		{
			otherPlayer = "A"; 
		}
		return otherPlayer;
	}
	
	public String currentLeader(Node node) //winner at current state
	{
		String currentLeader = null;
		int countX = space.countStateX(node);
		int countY = space.countStateY(node);
		if (countX > countY)
		{
			currentLeader = "X";
		}
		else if (countX < countY)
		{
			currentLeader = "Y";
		}
		return currentLeader;
	}
	
	public String getLevel()
	{
		return level;
	}
	
	public String getVersion()
	{
		return version;
	}
		
	public int getMaxDepth() //set difficulty
	{
		int maxDepth = 0;
		if (level == "Easy")
		{
			maxDepth = 2;
		}
		if (level == "Medium")
		{
			maxDepth = 5;
		}
		if (level == "Hard")
		{
			maxDepth = 7;
		}
		return maxDepth;
	}

	int evaluate(Node node, String player, int depth) //evaluate successors
	{
		int value = 1;
		int maxDepth = getMaxDepth();
		Vector<Node> successors = space.getSuccessors(node, player);
		int countX = space.countStateX(node);
		int countY = space.countStateY(node);
		int differenceX = space.differenceCountX(countX, countY);
		int differenceY = space.differenceCountY(countX, countY);
		if (player == "A") 
		{
			value = (differenceX + 50); //how many more pieces you have
		}
		if (player == "B") 
		{
			value = (differenceY + 50); 
		}
		if (player == "A" || player == "B")
		{
			if (successors.size() == 0) //having available moves is good
			{
				value = 10;
			}
			else
			{
				if (depth < maxDepth) //depth level increases as difficulty increases
				{
					for (int i = 0; i < successors.size(); i++) //for each possible move
					{
						Node successor = successors.get(i);
						successor.evaluation = evaluate(successor, player, depth + 1);
						if (successor.evaluation > value) 
						{
							value = successor.evaluation; 
						} 
					}
					player = otherPlayer(player);
					value = -value;
				}
			}
		}
			return(value); 
	}
	
	
	void run() 
	{
		String player = "A";
		Node node = space.getRoot();
		int countX = space.countStateX(node);
		int countY = space.countStateY(node);
		Vector<Node> bestNodes = new Vector<Node>();
		if (version == "Two") 
		{
			pr("Welcome to the game of checkers!");
		}
		printState(node);
		if (version == "Two") 
		{
		pr("\n");
		pr("The computer (letter 'x') will go first. You can move the letter 'y' only.");
		pr("\n");
		pr("The goal of the game is to have more of your letters on the board than the opponent.");
		pr("\n");
		pr("You can move diagonally into any free space, but only upwards.");
		pr("\n");
		pr("If your letter reaches the other end of the board, that letter becomes a king (capital Y).");
		pr("\n");
		pr("Kings can move diagonally forwards and backwards.");
		pr("\n");
		pr("If there is an opponent letter diagonally above you, you can jump and remove it");
		pr("\n");
		pr("as long as the space two diagonals over is empty.");
		pr("\n");
		pr("Decide which free space to move into and type the number");
		pr("\n");
		pr("that corresponds to the order of your choice.");
		pr("\n");
		pr("Moving into an empty space is counted before jump moves (removing opponent pieces)");
		pr("\n");
		pr("and moving kings is counted last. So if you want to move into the first available");
		pr("\n");
		pr("open space, type in the number zero. If want to use a jump move, count all open spaces first"); 
		pr("\n");
		pr("then type the number that corresponds to the jump. Good luck!");
		pr("\n\n");
		}
		Vector<Node> successors = space.getSuccessors(node, player);
		while (successors.size() != 0 || countX != 0 || countY != 0) 
		{
			Node node1 = space.getKingState(node);
			Vector<Node> successors2 = space.getSuccessors(node1, player);
			int maxValue = -Integer.MAX_VALUE;
			bestNodes.clear();
			for (int i = 0; i < successors2.size(); i++) 
			{
				Node newNode = successors2.get(i);
				int value = evaluate(newNode, player, 1);
				if (value == maxValue || version == "Two" && player == "B") 
				{
					bestNodes.add(newNode); 
				}
				else if (value > maxValue) 
				{
					bestNodes.clear();
					bestNodes.add(newNode);
					maxValue = value; 
				} 
			}
			if (successors2.size() == 0) 
			{
				break; 
			}
			else if (version == "Two" && player == "B")
			{
				pr("\n");
				pr("It's your turn to make a move.");
				pr("\n");
				while (player == "B") 
				{
					Scanner next = new Scanner(System.in);  
					int number;	    
					pr("Enter a number from zero to " + (bestNodes.size() - 1));
					number = next.nextInt();
					if (number < bestNodes.size()) 
					{
						node = bestNodes.get(number); 
						pr("\n");
						pr("State after move by player " + "(" + player + ")");
						printState(node);
						pr("\n");
						player = otherPlayer(player);
					}
					else 
					{
						pr("Invalid. ");
					} 
				}
			} 
			else if (version == "AI" || player == "A")
			{
				pr("\n");
				pr("State after move by player " + "(" + player + ")");
				int randomIndex = (int)(Math.random() * bestNodes.size());
				node = bestNodes.get(randomIndex); 
				printState(node); 
				player = otherPlayer(player); 
			}
		} 
		String champ = currentLeader(node);
		pr("\n");
		pr(champ == null ? "Draw" : "GAME WON FOR " + champ + "!!!" + "\n\n");  
	}
	
	public static void main(String args[]) 
	{
		new CheckersSearch("Two", "Easy").run();
	} 
}					