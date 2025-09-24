public class Rook extends Piece{

    public Rook(int color ,int x, int y, ChessPanel board) {
        super(color, x, y, board);
        value = 5;
        if (color == 1) image = getImage("images/white-rook.png");
        else image = getImage("images/black-rook.png");
    }
    @Override
    public Boolean validateMove(int destinationCol, int destinationRow, ChessPanel board) {
        return false;
    }

}
