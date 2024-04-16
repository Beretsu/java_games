import javax.swing.JFrame;

public class TicTacToe {

    public static void main(String[] args) {
        TicTacToeFrame frame = new TicTacToeFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
