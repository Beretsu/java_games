import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Marco extends JFrame {

    private char turno = 'x';
    private final Casilla[][] casilla = new Casilla[6][7];
    private final JLabel status = new JLabel("turno de x");

    public Marco() {
        JPanel panel = new JPanel(new GridLayout(6, 7));
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                panel.add(casilla[i][j] = new Casilla(i, j, this));
            }
        }
        panel.setBorder(new LineBorder(Color.RED));
        status.setBorder(new LineBorder(Color.YELLOW));
        add(panel, BorderLayout.CENTER);
        add(status, BorderLayout.SOUTH);
    }

    public void setTurno(char turno) {
        this.turno = turno;
    }

    public char getTurno() {
        return turno;
    }

    public JLabel getStatus() {
        return status;
    }

    public boolean isLleno() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (casilla[i][j].getToken() == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isGanado(char token) {
        for (int i = 0; i < 6; i++) {
            int horizontal = 0;
            for (int j = 0; j < 7; j++) {
                if (casilla[i][j].getToken() == token) {
                    horizontal++;
                    if (horizontal > 3) {
                        return true;
                    }
                } else {
                    horizontal = 0;
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            int vertical = 0;
            for (int j = 0; j < 6; j++) {
                if (casilla[j][i].getToken() == token) {
                    vertical++;
                    if (vertical > 3) {
                        return true;
                    }
                } else {
                    vertical = 0;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (casilla[i][j].getToken() == token && casilla[i + 1][j + 1].getToken() == token && casilla[i + 2][j + 2].getToken() == token && casilla[i + 3][j + 3].getToken() == token) {
                    return true;
                }
            }
        }
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (casilla[i][j].getToken() == token && casilla[i - 1][j + 1].getToken() == token && casilla[i - 2][j + 2].getToken() == token && casilla[i - 3][j + 3].getToken() == token) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isValido(int fila, int columna, char token) {
        if (fila == 5) {
            return true;
        } else {
            return casilla[fila + 1][columna].getToken() != ' ';
        }
    }
}
