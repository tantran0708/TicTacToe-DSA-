import javax.swing.JButton;

public class GameBoard {
    private JButton[][] buttons;

    public GameBoard(JButton[][] buttons) {
        this.buttons = buttons;
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public void resetBoard() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setText("");
            }
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWinnerWithDFS(int row, int col, String player) {
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
        for (int[] dir : directions) {
            int count = 1;
            count += countConnected(row, col, dir[0], dir[1], player);
            count += countConnected(row, col, -dir[0], -dir[1], player);
            if (count >= 5) {
                return true;
            }
        }
        return false;
    }

    private int countConnected(int row, int col, int dr, int dc, String player) {
        int count = 0;
        while (true) {
            row += dr;
            col += dc;
            if (row < 0 || row >= buttons.length || col < 0 || col >= buttons[0].length) break;
            if (!buttons[row][col].getText().equals(player)) break;
            count++;
        }
        return count;
    }
}