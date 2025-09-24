public class Bishop extends Piece {
    public Bishop(int color, int col, int row, ChessPanel board) {
        super(color, col, row, board);
        value = 3;
        if (color == WHITE) image = getImage("images/white-bishop.png");
        else image = getImage("images/black-bishop.png");
    }

    @Override
    public Boolean validateMove(int destinationCol, int destinationRow, ChessPanel board) {
        int colChange = destinationCol - col;
        int rowChange = destinationRow - row;
        int stepCol = Integer.signum(colChange);
        int stepRow = Integer.signum(rowChange);

        if (Math.abs(colChange) != Math.abs(rowChange) || colChange == 0) {
            return false;
        }

        for (int currentCol = col + stepCol, currentRow = row + stepRow;
             currentCol != destinationCol && currentRow != destinationRow;
             currentCol += stepCol, currentRow += stepRow) {

            if (board.getPieceAt(currentCol, currentRow) != null) return false;
        }
        return verifyTarget(board, destinationCol, destinationRow);
    }
}
