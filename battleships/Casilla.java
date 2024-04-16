import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Casilla extends JPanel {

    private Submarino submarino;
    private char token = ' ';

    public Casilla(Marco m, int fila, int columna) {
        setBorder(new LineBorder(Color.yellow));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (m.getSubmarinos() != 0) {
                    if (token == ' ') {
                        if (submarino != null) {
                            token = 't';
                            submarino.disminuirVida();
                            if (submarino.estaMuerto()) {
                                m.disminuirSubmarinos();
                                if (m.getSubmarinos() != 0) {
                                    m.getLabel().setText("te falta derrumbar a " + m.getSubmarinos() + " submarinos");
                                } else {
                                    m.getLabel().setText("Haz ganado! Felicidades");
                                }
                            }
                        } else {
                            token = 'f';
                        }
                        repaint();
                    }
                }
            }
        });
    }

    public Submarino getSubmarino() {
        return submarino;
    }

    public void setSubmarino(Submarino submarino) {
        this.submarino = submarino;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (token == 't') {
            g.drawOval(10, 10, getWidth() - 20, getHeight() - 20);
        } else if (token == 'f') {
            g.drawLine(10, 10, getWidth() - 10, getHeight() - 10);
            g.drawLine(getWidth() - 10, 10, 10, getHeight() - 10);
        }
    }
}
