public class Submarino {

    private int fila, columna, orientacion;
    private int vida;
    
    public Submarino(Marco m, int vida){
        this.vida = vida;
        do{
            generaCoordenadas();
        }while(!m.verificarPosicion(fila, columna, orientacion , vida));
    }

    private void generaCoordenadas() {
        fila = (int) (Math.random() * 10);
        columna = (int) (Math.random() * 10);
        orientacion = (int) (Math.random() * 2);
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public int getOrientacion() {
        return orientacion;
    }

    public boolean estaMuerto() {
        return vida==0;
    }

    public void disminuirVida() {
        vida--;
    }
}
