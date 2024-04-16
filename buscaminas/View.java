import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Ventana con interfaz para un juego de buscaminas.
 *
 * @author Dillian
 */
public class View extends JFrame {

    private BotonView botones[][];
    private int filas;
    private int columnas;
    private JPanel tablero;
    private final JPanel panelNiveles;
    private final JPanel panelJuego;
    private final CardLayout cardLayout;
    private final JButton facil;
    private final JButton intermedio;
    private final JButton avanzado;
    private final JLabel status;
    private final JButton reiniciar;
    private final JButton niveles;
    private final JTextField tiempoRecorrido;
    private final JTextField minasRestantes;

    /**
     * Crea una ventana para un juego de buscaminas.
     */
    public View() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        panelNiveles = new JPanel(new GridLayout());
        panelNiveles.add(facil = new JButton("Facil"));
        panelNiveles.add(intermedio = new JButton("Intermedio"));
        panelNiveles.add(avanzado = new JButton("Avanzado"));
        panelJuego = new JPanel(new BorderLayout());
        JPanel panel2 = new JPanel();
        reiniciar = new JButton("Reiniciar");
        niveles = new JButton("niveles");
        tiempoRecorrido = new JTextField("0", 3);
        tiempoRecorrido.setEditable(false);
        tiempoRecorrido.setFont(new Font("Serif", Font.BOLD, 25));
        tiempoRecorrido.setBackground(Color.BLACK);
        tiempoRecorrido.setForeground(Color.RED);
        tiempoRecorrido.setBorder(BorderFactory.createLoweredBevelBorder());
        minasRestantes = new JTextField("", 3);
        minasRestantes.setEditable(false);
        minasRestantes.setFont(new Font("Serif", Font.BOLD, 25));
        minasRestantes.setBackground(Color.BLACK);
        minasRestantes.setForeground(Color.RED);
        minasRestantes.setBorder(BorderFactory.createLoweredBevelBorder());
        panel2.add(minasRestantes);
        panel2.add(reiniciar);
        panel2.add(niveles);
        panel2.add(tiempoRecorrido);
        panelJuego.add(panel2, BorderLayout.NORTH);
        panelJuego.add(status = new JLabel("Empieza"), BorderLayout.SOUTH);
        add(panelNiveles);
        add(panelJuego);
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Crea la interfaz de un tablero de juego.
     *
     * @param filas Numero de filas en el tablero.
     * @param columnas Numero de columnas en el tablero.
     */
    public void crearTablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        botones = new BotonView[filas][columnas];
        tablero = new JPanel(new GridLayout(filas, columnas));
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                botones[i][j] = new BotonView(i, j);
                tablero.add(botones[i][j]);
            }
        }
        panelJuego.add(tablero, BorderLayout.CENTER);
    }

    /**
     * Remueve el tablero de la ventana de juego.
     */
    public void remueveTablero() {
        panelJuego.remove(tablero);
    }

    public BotonView[][] getBotones() {
        return botones;
    }

    /**
     * Instruye que comience el juego.
     */
    public void pintaComienza() {
        status.setText("Comienza");
    }

    /**
     * Instruye que se continue el juego.
     */
    public void pintaContinua() {
        status.setText("Continua");
    }

    /**
     * Anuncia que se se ha perdido el juego.
     */
    public void pintaPerdiste() {
        status.setText("Has Perdido!");
    }

    /**
     * Anuncia que se gano el juego.
     */
    public void pintaGanaste() {
        status.setText("Has Ganado!");
    }

    /**
     * Cambia el contenido en la ventana, de seleccion de niveles a el juego y
     * viceversa.
     */
    public void cambiarVista() {
        cardLayout.next(getContentPane());
    }

    /**
     * Actualiza la visualizacion de el tiempo recorrido de juego.
     *
     * @param tiempoRecorrido El tiempo recorrido de juego.
     */
    public void actualizarTiempoRecorrido(int tiempoRecorrido) {
        this.tiempoRecorrido.setText("" + tiempoRecorrido);
    }

    /**
     * Actualiza la visualizacion de el numeros de minas restantes.
     *
     * @param minasRestantes El numero de minas restantes
     */
    public void actualizarMinasRestantes(int minasRestantes) {
        this.minasRestantes.setText("" + minasRestantes);
    }

    /**
     * Añade un MouseListener a cada boton en el tablero de juego.
     *
     * @param e Un MouseListener.
     */
    public void addCasillasListener(MouseListener e) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                botones[i][j].addMouseListener(e);
            }
        }
    }

    /**
     * Añade un ActionListener para el boton "facil".
     *
     * @param a Un ActionListener
     */
    public void addActionForFacil(ActionListener a) {
        facil.addActionListener(a);
    }

    /**
     * Añade un ActionListener para el boton "intermedio".
     *
     * @param a Un ActionListener
     */
    public void addActionForIntermedio(ActionListener a) {
        intermedio.addActionListener(a);
    }

    /**
     * Añade un ActionListener para el boton "avanzado".
     *
     * @param a Un ActionListener
     */
    public void addActionForAvazado(ActionListener a) {
        avanzado.addActionListener(a);
    }

    /**
     * Añade un ActionListener para el boton "reiniciar".
     *
     * @param a Un ActionListener
     */
    public void addActionForReiniciar(ActionListener a) {
        reiniciar.addActionListener(a);
    }

    /**
     * Añade un ActionListener para el boton "niveles".
     *
     * @param a Un ActionListener
     */
    public void addActionForNiveles(ActionListener a) {
        niveles.addActionListener(a);
    }
}
