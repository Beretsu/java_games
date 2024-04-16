import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class TicTacToeFrame extends JFrame {

    private char whoseTurn = 'x';
    private final Cell[][] grid = new Cell[3][3];
    private final JLabel status = new JLabel("x's turn");

    public TicTacToeFrame() {
        JPanel panel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                panel.add(grid[i][j] = new Cell(this));
            }
        }
        panel.setBorder(new LineBorder(Color.RED));
        status.setBorder(new LineBorder(Color.YELLOW));
        add(panel, BorderLayout.CENTER);
        add(status, BorderLayout.SOUTH);
    }

    public char getWhoseTurn() {
        return whoseTurn;
    }

    public void setWhoseTurn(char whoseTurn) {
        this.whoseTurn = whoseTurn;
    }

    public JLabel getStatus() {
        return status;
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j].getToken() == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isWon(char token) {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0].getToken() == token && grid[i][1].getToken() == token && grid[i][2].getToken() == token) {
                return true;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (grid[0][j].getToken() == token && grid[1][j].getToken() == token && grid[2][j].getToken() == token) {
                return true;
            }
        }
        if (grid[0][0].getToken() == token && grid[1][1].getToken() == token && grid[2][2].getToken() == token) {
            return true;
        }
        return grid[0][2].getToken() == token && grid[1][1].getToken() == token && grid[2][0].getToken() == token;
    }
}
