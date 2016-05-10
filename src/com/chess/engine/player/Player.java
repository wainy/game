package com.chess.engine.player;

import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.MoveTransition;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import org.magicwerk.brownies.collections.GapList;
// class player
public abstract class Player {

    protected final Board board;
    protected final King playerKing;//play's king piece
    protected final Collection<Move> legalMoves;// play's all legal moves
    private final boolean isInCheck;

    Player(final Board board,
           final Collection<Move> playerLegals,
           final Collection<Move> opponentLegals) {
        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = ImmutableList.copyOf(
                Iterables.concat(playerLegals, calculateKingCastles(playerLegals, opponentLegals)));
        this.isInCheck = !Player.calculateAttacksOnTile(this.getPlayerKing().getPiecePosition(), opponentLegals).isEmpty();
    }
    
    // whether this move is legal move
    public boolean isMoveLegal(final Move move) {
        return !(move.isCastlingMove() && isInCheck()) && this.legalMoves.contains(move);
    }
    // whether the king is in check
    public boolean isInCheck() {
        return this.isInCheck;
    }
    // king is in check and has no move to escape
    public boolean isInCheckMate() {
       return this.isInCheck && !hasEscapeMoves();
    }
    // king is not in check but has no move to do;
    public boolean isInStaleMate() {
        return !this.isInCheck && !hasEscapeMoves();
    }
    // whether it can castling
    public boolean isCastled() {
        return this.playerKing.isCastled();
    }
    // whether king side castling is available 
    public boolean isKingSideCastleCapable() {
        return this.playerKing.isKingSideCastleCapable();
    }
    // whether queen side castling is available
    public boolean isQueenSideCastleCapable() {
        return this.playerKing.isQueenSideCastleCapable();
    }


    public King getPlayerKing() {
        return this.playerKing;
    }
    
    // return the information about the player
    public String playerInfo() {
        return ("Player is: " +this.getAlliance() + "\nlegal moves =" + getLegalMoves() + "\ninCheck = " +
                isInCheck() + "\nisInCheckMate = " +isInCheckMate() +
                "\nisCastled = " +isCastled())+ "\n";
    }
    
    
    
    protected King establishKing() {
        for(final Piece piece : getActivePieces()) {
            if(piece.getPieceType().isKing()) {
                return (King) piece;
            }
        }
        throw new RuntimeException("Should not reach here! " +this.getAlliance()+ " king could not be established!");
    }
    
    //whether is has move to escape
    protected boolean hasEscapeMoves() {
        for(final Move move : getLegalMoves()) {
            final MoveTransition transition = makeMove(move);
            if (transition.getMoveStatus().isDone()) {
                return true;
            }
        }
        return false;
    }

    public Collection<Move> getLegalMoves() {
        return this.legalMoves;
    }

    // calculate all attacks move to this tile
    public static Collection<Move> calculateAttacksOnTile(final int tile,
                                                          final Collection<Move> moves) {
        final List<Move> attackMoves = new GapList<>();
        for (final Move move : moves) {
            if (tile == move.getDestinationCoordinate()) {
                attackMoves.add(move);
            }
        }
        return ImmutableList.copyOf(attackMoves);
    }
    
    public MoveTransition makeMove(final Move move) {
        if (!isMoveLegal(move)) {
            return new MoveTransition(this.board, move, Move.MoveStatus.ILLEGAL_MOVE);
        }
        final Board transitionedBoard = move.execute();
        final Collection<Move> kingAttacks = Player.calculateAttacksOnTile(
                transitionedBoard.currentPlayer().getOpponent().getPlayerKing().getPiecePosition(),
                transitionedBoard.currentPlayer().getLegalMoves());
        if (!kingAttacks.isEmpty()) {
            return new MoveTransition(this.board, move, Move.MoveStatus.LEAVES_PLAYER_IN_CHECK);
        }
        return new MoveTransition(transitionedBoard, move, Move.MoveStatus.DONE);
    }

    public MoveTransition unMakeMove(final Move move) {
        return new MoveTransition(move.undo(), move, Move.MoveStatus.DONE);
    }

    public abstract Collection<Piece> getActivePieces();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();
    protected abstract Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentLegals);

}
