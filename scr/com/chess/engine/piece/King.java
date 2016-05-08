package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.MajorMove;


public class King extends Piece{
	
	private final static int[] CANDIDATE_MOVE_COORDINATES={-9,-8,-7,-1,1,7,8,9 };

	public King(final int piecePosition, final Alliance pieceAlliance) {
		super(PieceType.KING,piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> calculateLegalMoves(final Board board) {
		final List<Move> legalMoves = new ArrayList<>();
		for(final int currentCandidateOffset: CANDIDATE_MOVE_COORDINATES){
			final int candidateDestinationCoordinate = this.piecePosition+currentCandidateOffset;
			if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
				
				if(isFirstColumnExclusion(this.piecePosition,currentCandidateOffset)||
						isEighthColumnExclusion(this.piecePosition,currentCandidateOffset)
						){
					continue;
				}
				
				final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
				
				if(!candidateDestinationTile.isTileOccupied()){
					legalMoves.add(new MajorMove(board,this,candidateDestinationCoordinate));
				}else{
					final Piece pieceAtDestination = candidateDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
					
					if(this.pieceAlliance!=pieceAlliance){ //this is an enemy piece,legal move
						legalMoves.add(new AttackMove(board,this,candidateDestinationCoordinate,pieceAtDestination));
					}
				}
			}
		}
		return legalMoves;
	}
	
	private static boolean isFirstColumnExclusion(final int currentPosition,final int candidateOffset){
		
		return BoardUtils.FIRST_COLUMN[currentPosition]&&(candidateOffset==-9||candidateOffset == -1||candidateOffset == 7);
	}
	
	private static boolean isEighthColumnExclusion(final int currentPosition,final int candidateOffset){
		
		return BoardUtils.EIGHTH_COLUMN[currentPosition]&&(candidateOffset==-7||candidateOffset == 1||candidateOffset == 9);
	}
	@Override
	public String toString(){
		return PieceType.KING.toString();
	}
	@Override
	public King movePiece(final Move move) {
		return new King(move.getDestinationCoordinate(),move.getMovedPiece().getPieceAlliance());
	}

}
