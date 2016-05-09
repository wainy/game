package com.chess.engine.pieces;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;

import com.chess.engine.Alliance;

public abstract class Piece {
	
	protected final PieceType pieceType;
	protected final int piecePosition;
	protected final Alliance pieceAlliance;//whether this piece is white or black
	protected final boolean isFirstMove;
	private final int cachedHashCode;
	
	private int computehashCode(){
		int result = pieceType.hashCode();
		result = 31*result + pieceAlliance.hashCode();
		result = 31*result + piecePosition;
		result = 31*result + (isFirstMove?1:0);
		return result;
		
	}
	
	
	public Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance){
		this.pieceType=pieceType;
		this.pieceAlliance=pieceAlliance;
		this.piecePosition=piecePosition;
		//ToDO more work here
		this.isFirstMove = false;
		this.cachedHashCode = computehashCode();
		
	}
	
	@Override
	public boolean equals(final Object other){
		if(this == other){
			return true;
		}
		if(!(other instanceof Piece)){
			return false;
		}
		final Piece otherPiece =(Piece) other;
		return piecePosition == otherPiece.getPiecePosition() && pieceType == otherPiece.getPieceType() &&
				pieceAlliance == otherPiece.getPieceAlliance()&& isFirstMove == otherPiece.isFirstMove;
	}
	@Override
	public int hashCode(){
		return this.cachedHashCode;
	}
	public PieceType getPieceType() {
		return pieceType;
	}
	
	public Alliance getPieceAlliance(){
		return this.pieceAlliance;
	}
	public abstract Collection<Move> calculateLegalMoves(final Board board);
	
	public boolean isFirstMove(){
		return this.isFirstMove;
	}
	public int getPiecePosition() {
		return piecePosition;
	}
	public abstract Piece movePiece(Move move);
	
	public enum PieceType{
		
		PAWN("P") {
			@Override
			public boolean isKing() {
				return false;
			}
		},
		KNIGHT("N") {
			@Override
			public boolean isKing() {
				return false;
			}
		},
		BISHOP("B") {
			@Override
			public boolean isKing() {
				return false;
			}
		},
		ROOK("R") {
			@Override
			public boolean isKing() {
				return false;
			}
		},
		QUEEN("Q") {
			@Override
			public boolean isKing() {
				return false;
			}
		},
		KING("K") {
			@Override
			public boolean isKing() {
				return true;
			}
		};
		
		private String pieceName;
		
		PieceType(final String pieceName){
			this.pieceName=pieceName;
		}
		
		@Override
		public String toString(){
			return this.pieceName;
		}

		public abstract boolean isKing();
	}
	

}
