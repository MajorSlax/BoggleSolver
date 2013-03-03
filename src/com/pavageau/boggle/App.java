package com.pavageau.boggle;

/**
 * @author pavageau
 * 
 *         Boggle Solver app, packageable as a runnable JAR
 */
public class App {

	/**
	 * @param args
	 *            should contain 2 arguments:
	 * 
	 *            args[0] should be a 16 character string containing only
	 *            characters a-z (case-insensitive) representing the Boggle
	 *            board. First 4 characters are the first line of the grid from
	 *            left to right, the next 4 are the second line, and so on.
	 * 
	 *            args[1] should be the file path of the text file to use a
	 *            dictionary. See Dictionary class for more details
	 */
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out
					.println("usage: java -jar BoggleSolver.jar <bogglegrid> <pathtodictionaryfile>\n\nbogglegrid: 16 letter string, representing the Boggle game grid. First 4 letters are the first line of the grid, second 4 letters the second line, etc. for example, \"abcdefghijklmnop\" represents the grid:\n[a,b,c,d]\n[e,f,g,h]\n[i,j,k,l]\n[m,n,o,p]\n\npathToDictionaryFile: the path to the file to use as dictionary. Dictionary should be a text file with one word per line. Words containing special characters (other than a-z) will be ignored.");
			return;
		}

		BoggleBoard board = new BoggleBoard(args[0]);
		System.out.println(board.toString());

		Dictionary dict = new Dictionary(args[1]);
		System.out.println(new Solver(board, dict).solve());
	}
}
