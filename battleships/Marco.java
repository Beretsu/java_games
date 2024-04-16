import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Marco extends JFrame {

    private final Casilla[][] casilla = new Casilla[10][10];
    private int submarinos = 5;
    private final JLabel label = new JLabel("te falta derrumbar a 5 submarinos");

    public Marco() {
        JPanel panel = new JPanel(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                panel.add(casilla[i][j] = new Casilla(this, i, j));
            }
        }
        ponSubmarino(5);
        ponSubmarino(4);
        ponSubmarino(3);
        ponSubmarino(3);
        ponSubmarino(2);
        add(panel, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);
    }
    
    private void ponSubmarino(int vida){
        Submarino submarino = new Submarino(this, vida);
        if(submarino.getOrientacion()==1){
            for(int i = submarino.getColumna();i<submarino.getColumna()+vida;i++){
                casilla[submarino.getFila()][i].setSubmarino(submarino);
            }
        }else{
            for(int i = submarino.getFila();i<submarino.getFila()+vida;i++){
                casilla[i][submarino.getColumna()].setSubmarino(submarino);
            }
        }
    }

    public JLabel getLabel() {
        return label;
    }

    public int getSubmarinos() {
        return submarinos;
    }
    
    public void disminuirSubmarinos(){
        submarinos--;
    }
    
    public boolean verificarPosicion(int fila, int columna, int orientacion, int vida){
        if(orientacion==1){
            if(columna+vida>9){
                return false;
            }
            for(int i=columna;i<columna+vida;i++){
                if(casilla[fila][i].getSubmarino()!=null){
                    return false;
                }
            }
        }else{
            if(fila+vida>9){
                return false;
            }
            for(int i=fila;i<fila+vida;i++){
                if(casilla[i][columna].getSubmarino()!=null){
                    return false;
                }
            }
        }
        return true;
    }
}
