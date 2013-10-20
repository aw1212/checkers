package checkers;

import java.util.*;

class CheckersSpace 
{

	Node getRoot() //set up starting game board
	{
		String state[][] = new String[8][8];
		for (int r = 0; r < 8; r++) 
		{ 
		for (int c = 0; c < 8; c++) 
		{
			state[r][c] = "o";
		}
		}
		state[0][1] = "x";
		state[1][0] = "x";
		state[1][2] = "x";
		state[2][1] = "x";
		state[3][0] = "x";
		state[3][2] = "x";
		state[4][1] = "x";
		state[5][0] = "x";
		state[5][2] = "x";
		state[6][1] = "x";
		state[7][0] = "x";
		state[7][2] = "x";
		state[0][7] = "y";
		state[0][5] = "y";
		state[1][6] = "y";
		state[2][5] = "y";
		state[2][7] = "y";
		state[3][6] = "y";
		state[4][5] = "y";
		state[4][7] = "y";
		state[5][6] = "y";
		state[6][5] = "y";
		state[6][7] = "y";
		state[7][6] = "y";
		
		return new Node(state, null);
	}
	
	Node getKingState(Node parent) //set kings
	{
		String state[][] = parent.getStateCopy();
		for (int c = 0; c < 8; c++)
		{
		for (int r = 0; r < 8; r++)	
		{
			if (parent.state[0][7] == "x")
			{
				state[0][7] = "X";
			}
			if (parent.state[2][7] == "x")
			{
				state[2][7] = "X";
			}
			if (parent.state[4][7] == "x")
			{
				state[4][7] = "X";
			}
			if (parent.state[6][7] == "x")
			{
				state[6][7] = "X";
			}
			if (parent.state[1][0] == "y")
			{
				state[1][0] = "Y";
			}
			if (parent.state[3][0] == "y")
			{
				state[3][0] = "Y";
			}
			if (parent.state[5][0] == "y")
			{
				state[5][0] = "Y";
			}
			if (parent.state[7][0] == "y")
			{
				state[7][0] = "Y";
			}
		}
		}
		return new Node(state, null);
	}
	

	
	Vector<Node> getNormalSuccessors(Node parent, String player) //valid regular moves
	{
		Vector<Node> normal = new Vector<Node>();
		for (int c = 0; c < 8; c ++)
		{
		for (int r = 0; r < 8; r++) 
		{
			if (player == "A" && parent.state[r][c] == "x") 
			{
				if (r < 7 && c < 7 && parent.state[r+1][c+1] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r+1][c+1] = "x";
					state[r][c] = "o";
					normal.add(new Node(state, parent)); 
				}
				if(r > 0 && c < 7 && parent.state[r-1][c+1] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r-1][c+1] = "x";
					state[r][c] = "o";
					normal.add(new Node(state, parent)); 
				} 
			}
			else if (player == "B" && parent.state[r][c] == "y") 
			{
				if(r > 0 && c > 0 && parent.state[r-1][c-1] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r-1][c-1] = "y";
					state[r][c] = "o"; 
					normal.add(new Node(state, parent)); 
				}
				if(r < 7 && c > 0 && parent.state[r+1][c-1] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r+1][c-1] = "y";
					state[r][c] = "o"; 
					normal.add(new Node(state, parent)); 
				}
			}
		}
		} 
		return normal; 
	}
	
	Vector<Node> getCaptureSuccessors(Node parent, String player) //valid capture moves
	{
		Vector<Node> capture = new Vector<Node>();
		for (int c = 0; c < 8; c ++) 
		{
		for (int r = 0; r < 8; r++) 
		{
			if (player == "A" && parent.state[r][c] == "x")
			{
				if (r < 6 && c < 6 && parent.state[r+1][c+1] == "y" && parent.state[r+2][c+2] == "o" || r < 6 && c < 6 && parent.state[r+1][c+1] == "Y" && parent.state[r+2][c+2] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r+1][c+1] = "o";
					state[r+2][c+2] = "x";
					state[r][c] = "o";
					capture.add(new Node(state, parent)); 
				}
				if(r > 1 && c < 6 && parent.state[r-1][c+1] == "y" && parent.state[r-2][c+2] == "o" || r > 1 && c < 6 && parent.state[r-1][c+1] == "Y" && parent.state[r-2][c+2] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r-1][c+1] = "o";
					state[r-2][c+2] = "x";
					state[r][c] = "o";
					capture.add(new Node(state, parent)); 
				} 
			}
			else if (player == "B" && parent.state[r][c] == "y") 
			{
				if(r > 1 && c > 1 && parent.state[r-1][c-1] == "x" && parent.state[r-2][c-2] == "o" || r > 1 && c > 1 && parent.state[r-1][c-1] == "X" && parent.state[r-2][c-2] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r-1][c-1] = "o";
					state[r-2][c-2] = "y";
					state[r][c] = "o";
					capture.add(new Node(state, parent)); 
				} 
				if(r < 6 && c > 1 && parent.state[r+1][c-1] == "x" && parent.state[r+2][c-2] == "o" || r < 6 && c > 1 && parent.state[r+1][c-1] == "X" && parent.state[r+2][c-2] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r+1][c-1] = "o";
					state[r+2][c-2] = "y";
					state[r][c] = "o";
					capture.add(new Node(state, parent));
				} 
			}
		}
		} 
		return capture;
	}
	
	Vector<Node> getKingSuccessors(Node parent, String player) //valid king moves
	{
		Vector<Node> king = new Vector<Node>();
		for (int c = 0; c < 8; c ++)
		{
		for (int r = 0; r < 8; r++) 
		{
			if (player == "A" && parent.state[r][c] == "X") 
			{
				if (r < 7 && c < 7 && parent.state[r+1][c+1] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r+1][c+1] = "X";
					state[r][c] = "o";
					king.add(new Node(state, parent)); 
				}
				if(r > 0 && c < 7 && parent.state[r-1][c+1] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r-1][c+1] = "X";
					state[r][c] = "o";
					king.add(new Node(state, parent)); 
				} 
				if(r > 0 && c > 0 && parent.state[r-1][c-1] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r-1][c-1] = "X";
					state[r][c] = "o"; 
					king.add(new Node(state, parent)); 
				}
				if(r < 7 && c > 0 && parent.state[r+1][c-1] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r+1][c-1] = "X";
					state[r][c] = "o"; 
					king.add(new Node(state, parent)); 
				}
				if (r < 6 && c < 6 && parent.state[r+1][c+1] == "y" && parent.state[r+2][c+2] == "o" || r < 6 && c < 6 && parent.state[r+1][c+1] == "Y" && parent.state[r+2][c+2] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r+1][c+1] = "o";
					state[r+2][c+2] = "X";
					state[r][c] = "o";
					king.add(new Node(state, parent)); 
				}
				if(r > 1 && c < 6 && parent.state[r-1][c+1] == "y" && parent.state[r-2][c+2] == "o" || r > 1 && c < 6 && parent.state[r-1][c+1] == "Y" && parent.state[r-2][c+2] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r-1][c+1] = "o";
					state[r-2][c+2] = "X";
					state[r][c] = "o";
					king.add(new Node(state, parent)); 
				} 
				if(r > 1 && c > 1 && parent.state[r-1][c-1] == "y" && parent.state[r-2][c-2] == "o" || r > 1 && c > 1 && parent.state[r-1][c-1] == "Y" && parent.state[r-2][c-2] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r-1][c-1] = "o";
					state[r-2][c-2] = "X";
					state[r][c] = "o";
					king.add(new Node(state, parent)); 
				} 
				if(r < 6 && c > 1 && parent.state[r+1][c-1] == "y" && parent.state[r+2][c-2] == "o" || r < 6 && c > 1 && parent.state[r+1][c-1] == "Y" && parent.state[r+2][c-2] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r+1][c-1] = "o";
					state[r+2][c-2] = "X";
					state[r][c] = "o";
					king.add(new Node(state, parent));
				} 
			}
			else if (player == "B" && parent.state[r][c] == "Y")
			{
				if (r < 7 && c < 7 && parent.state[r+1][c+1] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r+1][c+1] = "Y";
					state[r][c] = "o";
					king.add(new Node(state, parent)); 
				}
				if(r > 0 && c < 7 && parent.state[r-1][c+1] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r-1][c+1] = "Y";
					state[r][c] = "o";
					king.add(new Node(state, parent)); 
				} 
				if(r > 0 && c > 0 && parent.state[r-1][c-1] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r-1][c-1] = "Y";
					state[r][c] = "o"; 
					king.add(new Node(state, parent)); 
				}
				if(r < 7 && c > 0 && parent.state[r+1][c-1] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r+1][c-1] = "Y";
					state[r][c] = "o"; 
					king.add(new Node(state, parent)); 
				}
				if (r < 6 && c < 6 && parent.state[r+1][c+1] == "x" && parent.state[r+2][c+2] == "o" || r < 6 && c < 6 && parent.state[r+1][c+1] == "X" && parent.state[r+2][c+2] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r+1][c+1] = "o";
					state[r+2][c+2] = "Y";
					state[r][c] = "o";
					king.add(new Node(state, parent)); 
				}
				if(r > 1 && c < 6 && parent.state[r-1][c+1] == "x" && parent.state[r-2][c+2] == "o" || r > 1 && c < 6 && parent.state[r-1][c+1] == "X" && parent.state[r-2][c+2] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r-1][c+1] = "o";
					state[r-2][c+2] = "Y";
					state[r][c] = "o";
					king.add(new Node(state, parent)); 
				} 
				if(r > 1 && c > 1 && parent.state[r-1][c-1] == "x" && parent.state[r-2][c-2] == "o" || r > 1 && c > 1 && parent.state[r-1][c-1] == "X" && parent.state[r-2][c-2] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r-1][c-1] = "o";
					state[r-2][c-2] = "Y";
					state[r][c] = "o";
					king.add(new Node(state, parent)); 
				} 
				if(r < 6 && c > 1 && parent.state[r+1][c-1] == "x" && parent.state[r+2][c-2] == "o" || r < 6 && c > 1 && parent.state[r+1][c-1] == "X" && parent.state[r+2][c-2] == "o") 
				{
					String state[][] = parent.getStateCopy();
					state[r+1][c-1] = "o";
					state[r+2][c-2] = "Y";
					state[r][c] = "o";
					king.add(new Node(state, parent));
				} 
			}
		}
		}
		return king;
	}

	
	Vector<Node> getSuccessors(Node parent, String player) //successors of all valid moves
	{
		Vector<Node> successors = new Vector<Node>();
		successors.addAll(getNormalSuccessors(parent, player));
		successors.addAll(getCaptureSuccessors(parent, player));
		successors.addAll(getKingSuccessors(parent, player));
		return successors;
	}
	

	public int countStateX(Node node) //count all X tiles on board
	{ 
		int countX = 0;
		for (int c = 0; c < 8; c++) 
		{
		for (int r = 0; r < 8; r++) 
		{
			String checker = node.state[r][c];
			if (checker.equals("x") || checker.equals("X")) //regular or king
			{
				countX = countX + 1;
			}
		}
		}
		return countX;
	}

	public int countStateY(Node node) //count all Y tiles on board
	{ 
		int countY = 0;
		for (int c = 0; c < 8; c++) 
		{
		for (int r = 0; r < 8; r++) 
		{
			String checker = node.state[r][c];
			if (checker.equals("y") || checker.equals("Y")) //regular or king
			{
				countY = countY + 1;
			}
		}
		}
		return countY;
	}
	
    public int differenceCountX(int countX, int countY)
	{
		int differenceX = countX - countY;
		return differenceX;
	}
	
	public int differenceCountY(int countX, int countY)
	{
		int differenceY = countY - countX;
		return differenceY;
	}

}