import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;

/**
 * Coordina el funcionamiento del modelo con la interfaz grafica.
 *
 * @author Dillian
 */
public class Controller {

    private Model m;
    private final View v;
    private byte nivelActual;               //La dificultad actual en la que esta el juego.
    private final Timer temporizador;

    /**
     * Crea una Ventana y le da ActionListeners a los botones.
     */
    public Controller() {
        v = new View();
        v.addActionForFacil(new FacilListener());
        v.addActionForIntermedio(new IntermedioListener());
        v.addActionForAvazado(new AvanzadoListener());
        v.addActionForNiveles(new CambioNivelesListener());
        v.addActionForReiniciar(new ReiniciarListener());
        temporizador = new Timer(1000, new TimerListener());
    }

    /**
     * MouseListener para cada casilla en el tablero de juego. Coordina a los
     * botones del tablero con el modelo de tablero de la clase ("Model").
     */
    private class CasillaListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            BotonView boton = (BotonView) e.getSource();
            if (!m.isGanado() && !m.isPerdido()) {                                      //En caso de no haber perdido ni ganado:
                if (e.getButton() == 1 && m.getEstado(boton.fila, boton.columna) == 0) {//Si se hace click izquierdo en una casilla valida.
                    m.clickIzquierdo(boton.fila, boton.columna);
                    if (m.isComenzado()) {
                        temporizador.start();
                        v.pintaContinua();
                    }
                    if (m.getValorCasilla(boton.fila, boton.columna) == 0) {            //Si se apreto un 0, recorre a todas las casillas en modelo de tablero y va apretando a todos los botones que se revelaron en el modelo del tablero.
                        for (int i = 0; i < v.getBotones().length; i++) {
                            for (int j = 0; j < v.getBotones()[0].length; j++) {
                                if (m.getEstado(i, j) == 3) {
                                    v.getBotones()[i][j].aprietaNoMina(m.getValorCasilla(i, j));
                                }
                            }
                        }
                    } else if (m.getValorCasilla(boton.fila, boton.columna) > 0) {
                        v.getBotones()[boton.fila][boton.columna].aprietaNoMina(m.getValorCasilla(boton.fila, boton.columna));
                    } else {                                                            //Si se apreto una mina: Perdiste. oscurece a las casillas no apretadas y revela a las minas y a las banderas erroneas.
                        v.pintaPerdiste();
                        temporizador.stop();
                        boton.aprietaMina();
                        for (int i = 0; i < v.getBotones().length; i++) {
                            for (int j = 0; j < v.getBotones()[0].length; j++) {
                                if (m.getEstado(i, j) != 3) {
                                    v.getBotones()[i][j].pintaBotonNoApretado();
                                    if (m.getValorCasilla(i, j) < 0) {
                                        if (m.getEstado(i, j) != 1) {
                                            v.getBotones()[i][j].pintaMina();
                                        }
                                    } else if (m.getEstado(i, j) == 1) {
                                        v.getBotones()[i][j].pintaBanderaErronea();
                                    }
                                }
                            }
                        }
                    }
                } else if (e.getButton() == 3 && m.getEstado(boton.fila, boton.columna) != 3) {     //En caso de dar click derecho en casilla valida:
                    m.clickDerecho(boton.fila, boton.columna);
                    v.actualizarMinasRestantes(m.getNumMinas());
                    switch (m.getEstado(boton.fila, boton.columna)) {
                        case 1:
                            boton.pintaBandera();
                            break;
                        case 2:
                            boton.pintaInterrogacion();
                            break;
                        default:
                            boton.borraPintura();
                    }
                }
                if (m.isGanado()) {
                    v.pintaGanaste();
                    temporizador.stop();
                }
            }
        }
    }

    /**
     * ActionListener para el boton "facil" en la clase "View". Crea un tablero
     * en la ventana de juego y un modelo de tablero con las configuraciones de
     * la dificultad "Facil".
     */
    private class FacilListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            v.setSize(400, 450);
            v.crearTablero(8, 8);
            m = new Model(8, 8, 10);
            nivelActual = 1;
            v.addCasillasListener(new CasillaListener());
            v.actualizarMinasRestantes(m.getNumMinas());
            v.cambiarVista();
        }
    }

    /**
     * ActionListener para el boton "intermedio" en la clase "View". Crea un
     * tablero en la ventana de juego y un modelo de tablero con las
     * configuraciones de la dificultad "Intermedio".
     */
    private class IntermedioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            v.setSize(600, 650);
            v.crearTablero(16, 16);
            m = new Model(16, 16, 40);
            nivelActual = 2;
            v.addCasillasListener(new CasillaListener());
            v.actualizarMinasRestantes(m.getNumMinas());
            v.cambiarVista();
        }
    }

    /**
     * ActionListener para el boton "avanzado" en la clase "View". Crea un
     * tablero en la ventana de juego y un modelo de tablero con las
     * configuraciones de la dificultad "Avanzado".
     */
    private class AvanzadoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            v.setSize(1100, 650);
            v.crearTablero(16, 30);
            m = new Model(16, 30, 99);
            nivelActual = 3;
            v.addCasillasListener(new CasillaListener());
            v.actualizarMinasRestantes(m.getNumMinas());
            v.cambiarVista();
        }
    }

    /**
     * ActionListener para el boton "reiniciar" en la clase "View". Reinicia el
     * juego deacuerdo a la dificultad actual del juego.
     */
    private class ReiniciarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (m.isComenzado()) {
                temporizador.stop();
                v.actualizarTiempoRecorrido(0);
                v.pintaComienza();
                v.remueveTablero();
                switch (nivelActual) {
                    case 1:
                        v.crearTablero(8, 8);
                        m = new Model(8, 8, 10);
                        break;
                    case 2:
                        v.crearTablero(16, 16);
                        m = new Model(16, 16, 40);
                        break;
                    default:
                        v.crearTablero(16, 30);
                        m = new Model(16, 30, 99);
                }
                v.addCasillasListener(new CasillaListener());
                v.actualizarMinasRestantes(m.getNumMinas());
                v.revalidate();
            }
        }
    }

    /**
     * ActionListener para el boton "niveles" en la clase "View".Prepara las
     * configuraciones para un juego nuevo y cambia el contenido en la ventana
     * de el juego a la seleccion de niveles.
     */
    private class CambioNivelesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            temporizador.stop();
            v.actualizarTiempoRecorrido(0);
            v.pintaComienza();
            v.remueveTablero();
            v.cambiarVista();
        }
    }

    /**
     * ActionListener para el temporizador. Le aumenta 1 a la veriable tiempo en
     * la clase "Model" cada segundo.
     */
    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            m.aumentarTiempo();
            v.actualizarTiempoRecorrido(m.getTiempo());
        }
    }
}
