package org.openjfx.model;

import org.openjfx.DAO.StepsDAOImpl;

public class VsPlayerModel {
	StepsDAOImpl steps = new StepsDAOImpl();

	private static boolean isPlayer1 = true;
	private int[][] board = new int[15][15];
	private int clickedX;
	private int clickedY;

	public void setboardTile() {
		board[clickedY][clickedX] = (isPlayer1 ? 1 : 2);
		steps.RecordStep((isPlayer1 ? 1 : 2), clickedX, clickedY, (isPlayer1 ? "X" : "O"));
	}

	public void switchPlayer() {
		isPlayer1 = !isPlayer1;
	}

	public boolean checkWin() {
		return isVert1Win() || isVert2Win() || isHorizontalWin() || isVerticalWin();
	}

	public boolean isVert1Win() {
		int x = clickedX;
		int y = clickedY;

		int type = board[y][x];
		int length = 0;

		for (int i = y - 4, j = x - 4; i < y + 4 && j < x + 4; i++, j++) {
			if (i < 0 || i > 14 || j < 0 || j > 14) {
				continue;
			}

			if (board[i][j] != type && length > 0) {
				length = 0;
			}

			if (board[i][j] == type) {
				length++;
			}

			if (length == 5) {
				return true;
			}
		}
		return false;
	}

	public boolean isVert2Win() {
		int x = clickedX;
		int y = clickedY;

		int type = board[y][x];
		int length = 0;

		for (int i = y - 4, j = x + 4; i <= y + 4 && j >= x - 4; i++, j--) {
			if (i < 0 || i > 14 || j < 0 || j > 14) {
				continue;
			}

			if (board[i][j] != type && length > 0) {
				length = 0;
			}

			if (board[i][j] == type) {
				length++;
			}

			if (length == 5) {
				return true;
			}
		}
		return false;
	}

	public boolean isHorizontalWin() {
		int x = clickedX;
		int y = clickedY;

		int type = board[y][x];
		int length = 0;

		for (int j = x - 4; j <= x + 4; j++) {
			if (j < 0 || j > 14) {
				continue;
			}

			if (board[y][j] != type && length > 0) {
				length = 0;
			}

			if (board[y][j] == type) {
				length++;
			}

			if (length == 5) {
				return true;
			}
		}
		return false;
	}

	public boolean isVerticalWin() {
		int x = clickedX;
		int y = clickedY;

		int type = board[y][x];
		int length = 0;

		for (int i = x - 4; i < x + 4; i++) {
			if (i < 0 || i > 14) {
				continue;
			}

			if (board[i][x] != type && length > 0) {
				length = 0;
			}

			if (board[i][x] == type) {
				length++;
			}

			if (length == 5) {
				return true;
			}
		}
		return false;
	}

	public boolean isPlayer1() {
		return isPlayer1;
	}

	public void setClickedX(int clickedX) {
		this.clickedX = clickedX;
	}

	public void setClickedY(int clickedY) {
		this.clickedY = clickedY;
	}

	public int getClickedX() {
		return clickedX;
	}

	public int getClickedY() {
		return clickedY;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				sb.append(board[i][j]).append("\t");
			}
			sb.append("\n");
		}

		sb.append("\n--------------------------------------------------------");
		return sb.toString();
	}
}
