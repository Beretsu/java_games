/**
 * Modelo de un tablero de juego para un Buscaminas.
 *
 * @author Dillian
 */
public class Model {

    private final int casillas[][][];                       //En la casilla[i][j][0] se almacena el valor de la casilla, y en casilla[i][j][1] se almacena el estado de la casilla[si fue apretado(3), si es bandera(1), signo de interrogacion(2) o si es casilla libre(0).]
    private final int filas;                                //Numero de filas en el modelo del tablero.
    private final int columnas;                             //Numero de columnas en el modelo del tablero.
    private int numMinas;                                   //Numero de minas en el modelo del tablero.
    private final int[] f = {-1, -1, -1, 0, 1, 1, 1, 0};    //En combinacion con 'c' se revisa a todos los vecinos.
    private final int[] c = {-1, 0, 1, 1, 1, 0, -1, -1};    //En combinacion con 'f' se revisa a todos los vecinos.
    private int noMinas;                                    //Conteo de casillas que no son minas.
    private boolean ganado;                                 //True: si el juego fue ganado. False: Si no se ha ganado.
    private boolean perdido;                                //True: si el juego fue perdido. False si no se he perdido.
    private boolean comenzado;                              //Si el jugador ya dio el primer click a una casilla(Si el juego ya fue comenzado).
    public int tiempo;                                      //Tiempo recorrido de desde que comenzo el juego.

    /**
     * Crea un Modelo que representa un tablero de juego para un buscaminas.
     *
     * @param filas Numero de filas en el tablero.
     * @param columnas Numero de columnas en el tablero.
     * @param numMinas Numero de minas en el tablero.
     */
    public Model(int filas, int columnas, int numMinas) {
        this.filas = filas;
        this.columnas = columnas;
        this.numMinas = numMinas;
        casillas = new int[filas][columnas][2];
        noMinas = filas * columnas - numMinas;
    }

    /**
     * Genera las posiciones de las minas y las coloca en el tablero despues de
     * apretar el primer boton. Una mina es un valor menor a 0.
     *
     * @param fila la fila en la que se encuentra el boton apretado.
     * @param columna la columna en la que se encuentra el boton apretado.
     */
    private void generaMinas(int fila, int columna) {
        int minasRestantes = 0;
        while (minasRestantes < numMinas) {
            int randFil = (int) (Math.random() * filas);
            int randCol = (int) (Math.random() * columnas);
            if (verificaPosicion(randFil, randCol, fila, columna)) {
                casillas[randFil][randCol][0] = -9;
                for (int i = 0; i < f.length; i++) {
                    if (randFil + f[i] >= 0 && randCol + c[i] >= 0 && randFil + f[i] < filas && randCol + c[i] < columnas) {
                        casillas[randFil + f[i]][randCol + c[i]][0]++;
                    }
                }
                minasRestantes++;
            }
        }
    }

    /**
     * Verifica que la posicion de la mina sea valida, No permite posiciones
     * repetidas, ni posiciones en donde se encuetra el primer boton apretado o
     * sus vecinos.
     *
     * @param randFil La fila aleatoria que se genero para la mina.
     * @param randCol La Columna aleatoria que se genero para la mina.
     * @param fila La fila donde se encuentra el boton apretado.
     * @param columna La fila donde se encuentra el boton apretado.
     * @return true: Si la posicion es valida. false: Si la posicion no es
     * valida.
     */
    private boolean verificaPosicion(int randFil, int randCol, int fila, int columna) {
        if (casillas[randFil][randCol][0] < 0) {
            return false;
        }
        if (fila == randFil && columna == randCol) {
            return false;
        }
        for (int i = 0; i < f.length; i++) {
            if (randFil + f[i] >= 0 && randCol + c[i] >= 0 && randFil + f[i] < filas && randCol + c[i] < columnas) {
                if (fila == randFil + f[i] && columna == randCol + c[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Revela(Simulando un click izquierdo) a los vecinos del boton
     * apretado(debe ser 0) si no es una mina.
     *
     * @param fila La fila donde se encuentra el boton apretado.
     * @param columna La columna donde se encuentra el boton apretado.
     */
    private void revela(int fila, int columna) {
        for (int i = 0; i < f.length; i++) {
            if (fila + f[i] >= 0 && columna + c[i] >= 0 && fila + f[i] < filas && columna + c[i] < columnas) {
                if (casillas[fila + f[i]][columna + c[i]][0] >= 0 && casillas[fila + f[i]][columna + c[i]][1] != 3) {
                    clickIzquierdoIndirecto(fila + f[i], columna + c[i]);
                }
            }
        }
    }

    /**
     * Simula un click Izquierdo: Cambia el estado del boton a apretado,
     * Determina si el juego fue ganado o perdido y revela a los vecinos del
     * boton si su valor es 0.
     *
     * @param fila La fila donde se encuentra el boton apretado.
     * @param columna La columna donde se encuentra el boton apretado.
     */
    private void clickIzquierdoIndirecto(int fila, int columna) {
        casillas[fila][columna][1] = 3;
        if (casillas[fila][columna][0] >= 0) {
            noMinas--;
            if (noMinas == 0 && numMinas == 0) {
                ganado = true;
            }
        } else {
            perdido = true;
        }
        if (casillas[fila][columna][0] == 0) {
            revela(fila, columna);
        }
    }

    /**
     * Determina si el click fue el primero o no para asi generar las minas, y
     * simula un click Izquierdo.
     *
     * @param fila La fila donde se encuentra el boton apretado.
     * @param columna La columna donde se encuentra el boton apretado.
     */
    public void clickIzquierdo(int fila, int columna) {
        if (!comenzado) {
            generaMinas(fila, columna);
            comenzado = true;
        }
        clickIzquierdoIndirecto(fila, columna);
    }

    /**
     * Simula un click derecho: Cambia el estado del boton a una bandera, un
     * signo de interrogacion o a casilla libre, descuenta minas por cada
     * bandera y determina si el juego fue ganado.
     *
     * @param fila La fila donde se encuentra el boton apretado.
     * @param columna La columna donde se encuentra el boton apretado.
     */
    public void clickDerecho(int fila, int columna) {
        if (casillas[fila][columna][1] == 2) {
            casillas[fila][columna][1] = 0;
        } else {
            casillas[fila][columna][1]++;
            if (casillas[fila][columna][1] == 1) {
                numMinas--;
                if (noMinas == 0 && numMinas == 0) {
                    ganado = true;
                }
            } else if (casillas[fila][columna][1] == 2) {
                numMinas++;
            }
        }
    }

    /**
     * Consige el numero de minas.
     *
     * @return Numero de Minas
     */
    public int getNumMinas() {
        return numMinas;
    }

    /**
     * Regresa si el juego fue ganado o no.
     *
     * @return true: si fue ganado. false: si no se ha ganado.
     */
    public boolean isGanado() {
        return ganado;
    }

    /**
     * Regresa si el juego fue perdido o no.
     *
     * @return true: si fue perdido. false: si no se ha perdido.
     */
    public boolean isPerdido() {
        return perdido;
    }

    /**
     * Consigue el valor contenido en la casilla[fila][columna][0].
     *
     * @param fila La fila de la casilla
     * @param columna La columna de la casilla
     * @return
     */
    public int getValorCasilla(int fila, int columna) {
        return casillas[fila][columna][0];
    }

    /**
     * Consigue el estado contenido en la casilla[fila][columna][1].
     *
     * @param fila La fila de la casilla.
     * @param columna La columna de la casilla.
     * @return
     */
    public int getEstado(int fila, int columna) {
        return casillas[fila][columna][1];
    }

    /**
     * Consigue si el juego ha comenzado.
     *
     * @return true: Si ya comenzo el juego. fase: si no ha comenzado el juego.
     */
    public boolean isComenzado() {
        return comenzado;
    }

    /**
     * Consigue el tiempo recorrido desde que inicio el juego.
     *
     * @return El tiempo recorrido desde que inicio el juego.
     */
    public int getTiempo() {
        return tiempo;
    }

    /**
     * Le suma uno a la variable "tiempo".
     */
    public void aumentarTiempo() {
        tiempo++;
    }
}
