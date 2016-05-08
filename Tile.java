package com.chess.engine;

public abstract class Tile {
	int tileCoodinate;
	
	Tile(int tileCoodinate){
		this.tileCoodinate=tileCoodinate;
	}
	public abstract boolean isTileOccupied();
	
	public abstract Piece getPiece();
	
	public static final class EmptyTile extends Tile{
		EmptyTile(int coordinate){
			super(coordinate);
		}
		@Override
		public boolean isTileOccupied(){
			return false;
		}
		
		@Override
		public Piece getPiece(){
			return null;
		}
	}
	
	public static final class OccupiedTile extends Tile{
		Piece pieceOnTile;
		OccupiedTile(int tileCoordinate,Piece pieceOnTile){
			super(tileCoordinate);
			this.pieceOnTile=pieceOnTile;
		}
		
		@Override
		public boolean isTileOccupied(){
			return true;
		}
		
		@Override
		public Piece getPiece(){
			
			return this.pieceOnTile;
		}
		
		
	}

}
