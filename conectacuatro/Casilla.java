import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Casilla extends JPanel {

    private char token = ' ';

    public Casilla(int fila, int columna, Marco t) {
        setBorder(new LineBorder(Color.yellow));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!t.getStatus().getText().equals(t.getTurno() + " has won")) {
                    if (token == ' ' && t.isValido(fila, columna, token)) {
                        token = t.getTurno();
                        repaint();
                        if (t.isGanado(token)) {
                            t.getStatus().setText(t.getTurno() + " has won");
                        } else if (t.isLleno()) {
                            t.getStatus().setText("it's a tie");
                        } else {
                            if (t.getTurno() == 'x') {
                                t.setTurno('o');
                            } else {
                                t.setTurno('x');
                            }
                            t.getStatus().setText(t.getTurno() + "'s turn");
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
