import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

/**
 * Boton para un juego de Buscaminas.
 *
 * @author Dillian
 */
public class BotonView extends JButton {

    public final int fila;
    public final int columna;

    /**
     * Crea Boton con una posicion para un tablero.
     *
     * @param fila La posicion fila del boton.
     * @param columna La posicion columna del boton.
     */
    public BotonView(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        setBackground(new Color(214, 214, 179));
        setBorder(new LineBorder(Color.GRAY, 2, true));
    }

    /**
     * Pinta mina apretada.
     */
    public void aprietaMina() {
        setBackground(Color.red);
        pintaMina();
    }

    /**
     * Pinta no-Mina apretada. En caso de ser 0, solo cambia el fondo.
     *
     * @param num El numero a pintar
     */
    public void aprietaNoMina(int num) {
        setBackground(new Color(237, 237, 237));
        if (num > 0) {
            setText("" + num);
        }
    }

    /**
     * Cambia el fondo del boton. Usar para cuando se pierda.
     */
    public void pintaBotonNoApretado() {
        setBackground(new Color(169, 169, 169));
    }

    /**
     * Pinta bandera Erronea. Usar para cuando se pierda.
     */
    public void pintaBanderaErronea() {
        setForeground(Color.red);
        pintaBandera();
    }

    /**
     * pinta una mina. Usar para minas no apretadas para cuando se pierda.
     */
    public void pintaMina() {
        setText("*");
    }

    /**
     * Pinta una bandera.
     */
    public void pintaBandera() {
        setText("|>");
    }

    /**
     * Pinta un signo de interrogacion.
     */
    public void pintaInterrogacion() {
        setText("?");
    }

    /**
     * Borra lo que sea que este pintado.
     */
    public void borraPintura() {
        setText("");
    }
}
