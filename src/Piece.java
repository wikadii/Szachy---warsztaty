import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Piece {
    public final int WHITE = 1;
    public final int BLACK = 0;

    Piece target;
    BufferedImage image;

    public int value;
    public int x, y;
    public int row, col;
    public int color;

    public Boolean isFirstMove = true;
    private ChessPanel board;

    public ArrayList<int[]> moves = new ArrayList<>();

    public Piece(int color ,int col, int row, ChessPanel board) {
        this.color = color;
        this.board = board;
        updatePieceLocation(col, row);
    }
    public BufferedImage getImage(String filename) {
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public void updatePieceLocation(int col, int row) {
        int tile_size = 60;
        this.col = col;
        this.row = row;
        this.x = (col - 1) * tile_size;
        this.y = (8 - row) * tile_size;
    }
    public Boolean validateMove(int destinationCol, int destinationRow, ChessPanel board) {
        return false;
    }
    public Boolean verifyTarget(ChessPanel board, int destinationCol, int destinationRow) {
        if (board.getPieceAt(destinationCol, destinationRow) != null){
            return !(board.getPieceAt(destinationCol, destinationRow).color == color);
        } else {
            return true;
        }
    }
    public void getMoves() {
        this.moves.clear();
        for (int i = 1; i <= 8; i++){
            for (int j = 1; j <= 8; j++){
                if (validateMove(i, j, board)){
                    moves.add(new int[]{i, j});
                }
            }
        }
    }
}
