import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class MainPanel extends JPanel {

    public ChessPanel cp;
    ArrayList<Piece> pieces;
    public int whiteTime = 30 * 60;
    public int blackTime = 30 * 60;
    public Timer gameTimer;
    JLabel whiteLabel;
    JLabel blackLabel;
    JLabel gameResultLabel;

    public MainPanel(int initialTime) {
        cp = new ChessPanel(this);
        pieces = cp.getPieces();
        cp.setBounds(90,40,cp.BOARD_WIDTH,cp.BOARD_HEIGHT);

        if (initialTime > 0) {
            whiteTime = initialTime;
            blackTime = initialTime;
        } else {
            whiteTime = blackTime = 999 * 60;
        }

        gameTimer = new Timer(1000, e -> tick());
        gameTimer.start();

        this.setPreferredSize(new Dimension(1000,600));
        this.setLayout(null);
        this.setBackground(Color.decode("#FFCFE7"));
        this.add(cp);

        gameResultLabel = new JLabel("");
        gameResultLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        gameResultLabel.setForeground(Color.BLACK);
        gameResultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.setBounds(600, 200, 350, 100);
        resultPanel.setBackground(Color.decode("#FCE4EC"));
        resultPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        resultPanel.add(gameResultLabel, BorderLayout.CENTER);

        this.add(resultPanel);
        getTimerPanels();
    }

    public void showGameResult(String resultText) {
        gameResultLabel.setText(resultText);
    }

    public void tick(){
        if (cp.turn == 1) whiteTime--;
        else blackTime--;

        whiteLabel.setText(formatTime(whiteTime));
        blackLabel.setText(formatTime(blackTime));

        checkTime(whiteTime);
        checkTime(blackTime);
    }

   public void checkTime(int time){
        if (time <= 0){
            cp.gameState = 1;
            cp.printGamestate();
            gameTimer.stop();
        }
        if (cp.gameState != 0){
            gameTimer.stop();
        }
   }

    public void getPromotionPanel(Pawn pawn) {
        JPanel promotionPanel = new JPanel();
        promotionPanel.setLayout(new BoxLayout(promotionPanel, BoxLayout.Y_AXIS));
        promotionPanel.setBackground(Color.decode("#FFCFE7"));
        promotionPanel.setBounds(0, 40, cp.TILE_SIZE, (cp.TILE_SIZE * 4) + 40);

        String[] pieceNames = {"queen", "rook", "knight", "bishop"};
        for (String name : pieceNames) {
            JButton promotionButton = createButton(name);
            promotionButton.addActionListener(e -> {
                    cp.pieces.remove(pawn);
                    switch (name) {
                        case "queen": cp.pieces.add(new Queen(pawn.color, pawn.col, pawn.row, cp)); break;
                        case "rook": cp.pieces.add(new Rook(pawn.color, pawn.col, pawn.row, cp)); break;
                        case "knight": cp.pieces.add(new Knight(pawn.color, pawn.col, pawn.row, cp)); break;
                        case "bishop": cp.pieces.add(new Bishop(pawn.color, pawn.col, pawn.row, cp)); break;
                    }
                    this.remove(promotionPanel);
                    this.repaint();

                    cp.gameState = cp.getGameState();
                    cp.printGamestate();
                    cp.repaint();
                });
            promotionPanel.add(promotionButton);
        }
        this.add(promotionPanel);
        this.revalidate();
        this.repaint();
    }
    public JButton createButton(String pieceName) {
        ImageIcon image = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/white-" + pieceName + ".jpg")));
        ImageIcon pieceImage = new ImageIcon(image.getImage());

        JButton pieceButton = new JButton(pieceImage);
        pieceButton.setBorderPainted(false);
        pieceButton.setContentAreaFilled(false);
        pieceButton.setFocusPainted(false);
        return pieceButton;
    }

    public void getTimerPanels(){
        JPanel whiteTimePanel = createTimePanel(1);
        JPanel blackTimePanel = createTimePanel(0);
        this.add(whiteTimePanel);
        this.add(blackTimePanel);

    }

    public JPanel createTimePanel(int color){
        JPanel timePanel = new JPanel();
        if (color == 0){
            timePanel.setBounds(600, 75, 150,40);
            timePanel.setBackground(Color.decode("#FF69B4"));
            timePanel.setBorder(new LineBorder(Color.BLACK, 3));
            blackLabel = new JLabel(formatTime(blackTime));
            blackLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
            blackLabel.setBorder(BorderFactory.createEmptyBorder(-8,0,0,0));
            timePanel.add(blackLabel);
        }
        else{
            timePanel.setBounds(600, 445, 150,40);
            timePanel.setBackground(Color.decode("#FF69B4"));
            timePanel.setBorder(new LineBorder(Color.BLACK, 3));
            whiteLabel = new JLabel(formatTime(whiteTime));
            whiteLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
            whiteLabel.setBorder(BorderFactory.createEmptyBorder(-8,0,0,0));
            timePanel.add(whiteLabel);
        }
        return timePanel;
    }

    private String formatTime(int seconds) {
        int min = seconds / 60;
        int sec = seconds % 60;
        return String.format("%02d:%02d", min, sec);
    }

}


