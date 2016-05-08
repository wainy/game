package com.chess.engine;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public abstract class Tile {
	protected final int tileCoodinate;
	
	private static final Map<Integer,EmptyTile>EMPTY_TILES=creatAllPossibleEmptyTile();
	
	private static Map<Integer, EmptyTile> creatAllPossibleEmptyTile() {
		final Map<Integer,EmptyTile>emptyTileMap=new HashMap<>();
		for(int i=0;i<64;i++){
			emptyTileMap.put(i, new EmptyTile(i));
		}
		
		return ImmutableMap.copyOf(emptyTileMap);
	}
	
	public static Tile creatTile(final int tileCoordinate,final Piece piece){
		return piece!=null?new OccupiedTile(tileCoordinate,piece):EMPTY_TILES.get(tileCoordinate);
	}
	
	private Tile(int tileCoodinate){
		this.tileCoodinate=tileCoodinate;
	}
	
	public abstract boolean isTileOccupied();
	
	public abstract Piece getPiece();
	
	public static final class EmptyTile extends Tile{
		EmptyTile(final int coordinate){
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
		private final Piece pieceOnTile;
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
