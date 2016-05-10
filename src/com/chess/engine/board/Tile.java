package com.chess.engine.board;

import java.util.HashMap;
import java.util.Map;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

//this is class about the tile on the board
public abstract class Tile {

    protected final int tileCoordinate;

    private static final Map<Integer, EmptyTile> EMPTY_TILES = createAllPossibleEmptyTiles();

    private Tile(final int coordinate) {
        this.tileCoordinate = coordinate;
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();

    public static Tile createTile(final int coordinate,final Piece piece) {
        return piece != null ? new OccupiedTile(coordinate, piece) :
                               EMPTY_TILES.get(coordinate);
    }

    public int getTileCoordinate() {
        return this.tileCoordinate;
    }
    
    //create possible empty tiles
    private static Map<Integer,EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for(int i = 0; i < BoardUtils.NUM_TILES; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap);
    }
    
    // empty tile class
    public static final class EmptyTile extends Tile {

        private EmptyTile(final int coordinate) {
            super(coordinate);
        }

        @Override
        public String toString() {
            return "-";
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }

        public Piece getPiece() {
            return null;
        }

    }
    
    // Occupied tile
    public static final class OccupiedTile extends Tile {

        private final Piece pieceOnTile;

        private OccupiedTile(final int coordinate,
                             final Piece pieceOnTile) {
            super(coordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public String toString() {
            return this.pieceOnTile.getPieceAllegiance().isWhite() ?
                    this.pieceOnTile.toString() :
                    this.pieceOnTile.toString().toLowerCase();
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return pieceOnTile;
        }
    }

}