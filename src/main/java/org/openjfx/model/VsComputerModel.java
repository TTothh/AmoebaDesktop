package org.openjfx.model;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import org.openjfx.DAO.StepsDAOImpl;

import java.util.Random;

public class VsComputerModel {
	StepsDAOImpl steps = new StepsDAOImpl();

	private static boolean isPlayer1 = true;
	private int[][] board = new int[15][15];
	private int clickedX;
	private int clickedY;
	private int AIX;
	private int AIY;

	public void setboardTile() {
		board[clickedY][clickedX] = (isPlayer1 ? 1 : 2);
		steps.RecordStep(1, clickedX, clickedY, "X");
	}

	public void switchPlayer() {
		isPlayer1 = !isPlayer1;
	}

	public boolean checkWin() {
		return isVert1Win() || isVert2Win() || isHorizontalWin() || isVerticalWin();
	}

	public void placeAITile() {
		Random r = new Random();
		int x = r.nextInt(15);
		int y = r.nextInt(15);

		while(board[y][x] == 0) {
			if(board[y][x] == 0) {
				board[y][x] = 2;
				isPlayer1 = true;

				AIX = x;
				AIY = y;

				steps.RecordStep(3, AIX, AIY, "O");

				break;
			}
			x = r.nextInt(15);
			y = r.nextInt(15);
		}
	}

	public Node getNode(GridPane board) {
		ObservableList<Node> children = board.getChildren();

		for (Node node : children) {
			Integer columnIndex = GridPane.getColumnIndex(node);
			Integer rowIndex = GridPane.getRowIndex(node);

			if (columnIndex == null)
				columnIndex = 0;
			if (rowIndex == null)
				rowIndex = 0;

			if (columnIndex == AIX && rowIndex == AIY) {
				return node;
			}
		}

		return null;
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

	public int getAIX() {
		return AIX;
	}

	public void setAIX(int AIX) {
		this.AIX = AIX;
	}

	public int getAIY() {
		return AIY;
	}

	public void setAIY(int AIY) {
		this.AIY = AIY;
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
