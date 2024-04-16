import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Cell extends JPanel {

    private char token = ' ';

    public Cell(TicTacToeFrame t) {
        setBorder(new LineBorder(Color.yellow));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!t.getStatus().getText().equals(t.getWhoseTurn() + " has won")) {
                    if (token == ' ') {
                        token = t.getWhoseTurn();
                        repaint();
                        if (t.isWon(token)) {
                            t.getStatus().setText(t.getWhoseTurn() + " has won");
                        } else if (t.isFull()) {
                            t.getStatus().setText("it's a tie");
                        } else {
                            if (t.getWhoseTurn() == 'x') {
                                t.setWhoseTurn('o');
                            } else {
                                t.setWhoseTurn('x');
                            }
                            t.getStatus().setText(t.getWhoseTurn() + "'s turn");
                        }
                    }
                }
            }
        });
    }

    public char getToken() {
        return token;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (token == 'x') {
            g.drawLine(10, 10, getWidth() - 10, getHeight() - 10);
            g.drawLine(getWidth() - 10, 10, 10, getHeight() - 10);
        } else if (token == 'o') {
            g.drawOval(10, 10, getWidth() - 20, getHeight() - 20);
        }
    }
}
