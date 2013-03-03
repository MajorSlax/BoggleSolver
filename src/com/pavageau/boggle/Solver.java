package com.pavageau.boggle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author pavageau
 * 
 *         Solver class for a Boggle game
 */
public class Solver {

	private final Map<String, Boolean> dictionary;
	private final BoggleBoard board;
	private final List<String> solution = new ArrayList<String>();

	/**
	 * @param board
	 *            the Boggle board
	 * @param dict
	 *            the dictionary to use containing all valid words
	 */
	public Solver(BoggleBoard board, Dictionary dict) {
		this.board = board;
		this.dictionary = dict.getWordMap();
	}

	/**
	 * @return all valid words that are present in the board, according to
	 *         Boggle rules
	 */
	public List<String> solve() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				kickOff(board.get(i, j), new ArrayList<BoggleCell>());
			}
		}
		Collections.sort(solution, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				int compare = Integer.signum(s1.length() - s2.length());
				return compare == 0 ? s1.compareTo(s2) : compare;
			}
		});
		return solution;
	}

	/**
	 * @param current
	 *            the cell the engine is currently on
	 * @param pathToCurrent
	 *            the path of cells that were visited to reach the current cell
	 */
	private void kickOff(BoggleCell current, List<BoggleCell> pathToCurrent) {
		pathToCurrent.add(current);
		String word = toWord(pathToCurrent);
		if (pathToCurrent.size() > 1 && dictionary.containsKey(word)
				&& dictionary.get(word) && !solution.contains(word)) {
			solution.add(word);
		}

		for (BoggleCell next : validMoves(current, pathToCurrent)) {
			// only kick off next if there are words starting with the
			// sequence word+next.c
			if (dictionary.containsKey(word + next.c)) {
				// clone pathToCurrent otherwise concurrency issues
				List<BoggleCell> pathClone = new ArrayList<BoggleCell>();
				pathClone.addAll(pathToCurrent);
				kickOff(next, pathClone);
			}
		}
	}

	/**
	 * @param current
	 *            the cell the engine is currently on
	 * @param pathToCurrent
	 *            the path of cells that were visited to reach the current cell
	 * @return the list of cells that can be reached from the current cell and
	 *         haven't been visited already
	 */
	private List<BoggleCell> validMoves(BoggleCell current,
			List<BoggleCell> pathToCurrent) {
		List<BoggleCell> valid = new ArrayList<BoggleCell>();
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (i == 0 && j == 0) {
					continue;
				} else {
					int newX = current.x + i;
					int newY = current.y + j;
					if (newX >= 0 && newX < 4 && newY >= 0 && newY < 4) {
						BoggleCell nextCell = board.get(newX, newY);
						if (!pathToCurrent.contains(nextCell)) {
							valid.add(nextCell);
						}
					}
				}
			}
		}
		return valid;
	}

	/**
	 * @param list
	 *            the list of cells to convert to a String
	 * @return the word that the list of cells represents (i.e. the String made
	 *         from concatenating all the list's cell's characters, in order)
	 */
	private String toWord(List<BoggleCell> list) {
		StringBuilder sb = new StringBuilder();
		for (BoggleCell sq : list) {
			sb.append(sq.c);
		}
		return sb.toString();
	}
}
