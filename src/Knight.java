public class Knight extends Piece{
    public Knight(int color ,int x, int y, ChessPanel board) {
        super(color, x, y, board);
        value = 3;
        if (color == 1) image = getImage("images/white-knight.png");
        else image = getImage("images/black-knight.png");
    }
    @Override
    public Boolean validateMove(int destinationCol, int destinationRow, ChessPanel board) {
        int colChange = Math.abs(destinationCol - col);
        int rowChange = Math.abs(destinationRow - row);

        if (colChange * rowChange == 2) {
            return verifyTarget(board, destinationCol, destinationRow);
        }
        return false;
    }

}

