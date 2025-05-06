import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';

    public TicTacToeGUI() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        initializeButtons();

        setVisible(true);
    }

    private void initializeButtons() {
        Font font = new Font("Arial", Font.BOLD, 60);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(font);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals("")) {
            return; // already clicked
        }

        clicked.setText(String.valueOf(currentPlayer));

        if (hasWon()) {
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
            resetBoard();
        } else if (isDraw()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private boolean hasWon() {
        String playerStr = String.valueOf(currentPlayer);

        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (playerStr.equals(buttons[i][0].getText()) &&
                playerStr.equals(buttons[i][1].getText()) &&
                playerStr.equals(buttons[i][2].getText())) return true;

            if (playerStr.equals(buttons[0][i].getText()) &&
                playerStr.equals(buttons[1][i].getText()) &&
                playerStr.equals(buttons[2][i].getText())) return true;
        }

        // Check diagonals
        if (playerStr.equals(buttons[0][0].getText()) &&
            playerStr.equals(buttons[1][1].getText()) &&
            playerStr.equals(buttons[2][2].getText())) return true;

        if (playerStr.equals(buttons[0][2].getText()) &&
            playerStr.equals(buttons[1][1].getText()) &&
            playerStr.equals(buttons[2][0].getText())) return true;

        return false;
    }

    private boolean isDraw() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (buttons[i][j].getText().equals(""))
                    return false;
        return true;
    }

    private void resetBoard() {
        currentPlayer = 'X';
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttons[i][j].setText("");
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
