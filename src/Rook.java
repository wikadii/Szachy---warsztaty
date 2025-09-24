public class Rook extends Piece{

    public Rook(int color ,int x, int y, ChessPanel board) {
        super(color, x, y, board);
        value = 5;
        if (color == 1) image = getImage("images/white-rook.png");
        else image = getImage("images/black-rook.png");
    }
    @Override
    public Boolean validateMove(int destinationCol, int destinationRow, ChessPanel board) {
        int colChange = destinationCol - col;
        int rowChange = destinationRow - row;
        int stepCol = Integer.signum(colChange);
        int stepRow = Integer.signum(rowChange);
        int steps = Math.max(Math.abs(colChange), Math.abs(rowChange));

        if (colChange != 0 && rowChange != 0) return false;

        for (int i = 1; i < steps; i++) {
            int currentCol = col + i * stepCol;
            int currentRow = row + i * stepRow;
            if (board.getPieceAt(currentCol, currentRow) != null) return false;
        }

        return verifyTarget(board, destinationCol, destinationRow);
    }

}
