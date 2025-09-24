
public class Queen extends Piece {
    public Queen(int color, int col, int row, ChessPanel board) {
        super(color, col, row, board);
        value = 9;
        if (color == WHITE) image = getImage("images/white-queen.png");
        else image = getImage("images/black-queen.png");
    }
    @Override
    public Boolean validateMove(int destinationCol, int destinationRow, ChessPanel board) {
        int colChange = destinationCol - col;
        int rowChange = destinationRow - row;
        int stepCol = Integer.signum(colChange);
        int stepRow = Integer.signum(rowChange);
        int steps = Math.max(Math.abs(colChange), Math.abs(rowChange));

        if (colChange == 0 && rowChange == 0) return false;

        if (!(colChange == 0 || rowChange == 0 || Math.abs(colChange) == Math.abs(rowChange))) {
            return false;
        }

        for (int i = 1; i < steps; i++) {
            int currentCol = col + i * stepCol;
            int currentRow = row + i * stepRow;

            if (board.getPieceAt(currentCol, currentRow) != null) return false;
        }

        return verifyTarget(board, destinationCol, destinationRow);
    }

}