package com.pavageau.boggle;

import java.util.Arrays;

/**
 * @author pavageau
 * 
 *         Represents a Boggle board
 */
public class BoggleBoard {

	private final BoggleCell[][] board = new BoggleCell[4][4];

	/**
	 * Creates a BoggleBoard from a 16 character String
	 * 
	 * @param input
	 *            16 letter String to use as a source. First 4 characters
	 *            represent the first line of the grid, Next 4 characters
	 *            represent the second line, and so on. Only characters a-z
	 *            (case-insensitive) are allowed.
	 */
	public BoggleBoard(String input) {
		if (!input.matches("[a-zA-Z]{16}")) {
			throw new IllegalArgumentException(
					"Input String can only contain letters a-z and must contain 16 characters.");
		}

		int index = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				this.board[i][j] = new BoggleCell(input.charAt(index++), i, j);
			}
		}
	}

	/**
	 * @return the BoggleCell at position (x,y)
	 */
	public BoggleCell get(int x, int y) {
		return board[x][y];
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			sb.append(Arrays.toString(board[i]) + "\n");
		}
		return sb.toString();
	}
}
