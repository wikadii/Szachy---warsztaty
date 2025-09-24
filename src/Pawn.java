public class Pawn extends Piece{
    ChessPanel board;
    public Pawn(int color ,int x, int y, ChessPanel board) {
        super(color, x, y, board);
        value = 1;
        this.board = board;
        if (color == 1) image = getImage("images/whitePawn.png");
        else image = getImage("images/black-pawn.png");
        isFirstMove = true;
    }
    public boolean canEnPassant(int destinationCol, int destinationRow, ChessPanel board){
        int direction = (color == WHITE) ? 1 : -1;

        if (board.enPassantPawn == null) return false;

        if (Math.abs(destinationCol - this.col) == 1 && destinationRow == this.row + direction) {
            if (board.isSquareEmpty(destinationCol, destinationRow)) {
                if (board.enPassantPawn.row == this.row &&
                        board.enPassantPawn.col == destinationCol) {
                    return true;
                }
            }
        }
        return false;
    }
    public Boolean validateMove(int destinationCol, int destinationRow, ChessPanel board) {
        int direction = (color == WHITE) ? 1 : -1;
        if (destinationCol == this.col &&
                destinationRow == this.row + direction &&
                board.isSquareEmpty(destinationCol, destinationRow)) {
            return true;
        }
        if (isFirstMove &&
                destinationCol == this.col &&
                destinationRow == this.row + 2 * direction &&
                board.isSquareEmpty(destinationCol, destinationRow) &&
                board.isSquareEmpty(destinationCol, this.row + direction)) {
            return true;
        }

        if (Math.abs(destinationCol - this.col) == 1 &&
                destinationRow == this.row + direction) {
            Piece target = board.getPieceAt(destinationCol, destinationRow);
            if (target != null && target.color != this.color) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void getMoves() {
        super.getMoves();

        int direction = (color == WHITE) ? 1 : -1;

        if (board.enPassantPawn != null) {
            int epCol = board.enPassantPawn.col;
            int epRow = board.enPassantPawn.row;
            if (Math.abs(epCol - col) == 1 && row + direction == ((color == WHITE) ? 6 : 3)) {
                if (board.getPieceAt(epCol, epRow) == board.enPassantPawn) {
                    moves.add(new int[]{epCol, row + direction});
                }
            }
        }
    }
}

