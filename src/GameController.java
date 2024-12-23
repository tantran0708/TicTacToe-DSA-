import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class GameController {
    private GameBoard gameBoard;
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private boolean hasSwapped;

    public GameController(GameBoard gameBoard, Player playerX, Player playerO) {
        this.gameBoard = gameBoard;
        this.playerX = playerX;
        this.playerO = playerO;
        this.currentPlayer = playerO;
        this.hasSwapped = false;
    }

    public void choosePlayer() {
        currentPlayer = currentPlayer == playerX ? playerO : playerX;
    }

    public void chooseWinner() {
        JButton[][] buttons = gameBoard.getButtons();
        boolean isBoardFull = gameBoard.isBoardFull();

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                String player = buttons[i][j].getText();
                if (!player.isEmpty() && gameBoard.checkWinnerWithDFS(i, j, player)) {
                    JOptionPane.showMessageDialog(null, "Player " + player + " Wins!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
                    gameBoard.resetBoard();
                    if (player.equals("X")) {
                        playerX.incrementScore();
                    } else {
                        playerO.incrementScore();
                    }
                    // Gọi phương thức cập nhật giao diện
                    gameBoard.getParentFrame().updateScoreDisplay();
                    return;
                }
            }
        }

        if (isBoardFull) {
            JOptionPane.showMessageDialog(null, "It's a Tie!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
            gameBoard.resetBoard();
        }
    }


    public void buttonClicked(JButton button) {
        if (button.getText().isEmpty()) {
            button.setText(currentPlayer.getName());
            button.setForeground(currentPlayer.getName().equalsIgnoreCase("X") ? Color.red : Color.magenta);
            choosePlayer();
            chooseWinner();
        }
    }
}
