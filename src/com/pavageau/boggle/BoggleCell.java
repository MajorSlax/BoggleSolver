package com.pavageau.boggle;

/**
 * @author pavageau
 * 
 *         Represents a cell in a Boggle board
 */
public class BoggleCell {

	public final char c;
	public final int x;
	public final int y;

	/**
	 * @param c
	 *            the letter whose position in the grid is (x,y)
	 * @param x
	 * @param y
	 */
	public BoggleCell(char c, int x, int y) {
		this.c = Character.toLowerCase(c);
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return Character.toString(c);
	}
}
