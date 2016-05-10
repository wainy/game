package com.chess.engine;

import com.chess.engine.board.BoardUtils;
import com.chess.engine.player.BlackPlayer;
import com.chess.engine.player.Player;
import com.chess.engine.player.WhitePlayer;
// this is Alliance about the chess piece: white or black
public enum Alliance {

    WHITE() {

        @Override
        public boolean isWhite() {
            return true;
        }

        @Override
        public boolean isBlack() {
            return false;
        }
        
        //direction of the board;
        @Override
        public int getDirection() {
            return UP_DIRECTION;
        }

        @Override
        public int getOppositeDirection() {
            return DOWN_DIRECTION;
        }
        
        //get the promotion square about the pawn;
        @Override
        public boolean isPawnPromotionSquare(final int position) {
            return BoardUtils.FIRST_ROW[position];
        }

        @Override
        public Player choosePlayerByAlliance(final WhitePlayer whitePlayer,
                                             final BlackPlayer blackPlayer) {
            return whitePlayer;
        }

        @Override
        public String toString() {
            return "White";
        }
    },
    BLACK() {

        @Override
        public boolean isWhite() {
            return false;
        }

        @Override
        public boolean isBlack() {
            return true;
        }

        @Override
        public int getDirection() {
            return DOWN_DIRECTION;
        }

        @Override
        public int getOppositeDirection() {
            return UP_DIRECTION;
        }

        @Override
        public boolean isPawnPromotionSquare(final int position) {
            return BoardUtils.EIGHTH_ROW[position];
        }

        @Override
        public Player choosePlayerByAlliance(final WhitePlayer whitePlayer,
                                             final BlackPlayer blackPlayer) {
            return blackPlayer;
        }

        @Override
        public String toString() {
            return "Black";
        }

    };
	
    public abstract int getDirection();

    public abstract int getOppositeDirection();

    public abstract boolean isWhite();

    public abstract boolean isBlack();

    public abstract boolean isPawnPromotionSquare(int position);

    public abstract Player choosePlayerByAlliance(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer);

    private static final int UP_DIRECTION = -1;

    private static final int DOWN_DIRECTION = 1;

}