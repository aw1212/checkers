package checkers;

class Node 
{
	String state[][] = new String[8][8]; //two-d array
	int evaluation = -1; 
	Node parent = null;

	public Node(String s[][], Node parent) 
	{
		for (int c = 0; c < 8; c++) // eight columns
		{ 
		for (int r = 0; r < 8; r++) // eight rows
		{ 
			state[r][c] = s[r][c];			
			this.parent = parent;
		}
		}
	}

	String[][] getStateCopy() //get a copy of my board
	{
		String[][] copy = new String [8][8];
		for (int c = 0; c < 8; c++) 
		{
		for (int r = 0; r < 8; r++) 
		{
			copy[r][c] = state[r][c];
		}
		}				
		return copy;
	}
}
