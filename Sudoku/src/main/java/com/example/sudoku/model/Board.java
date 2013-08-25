package com.example.sudoku.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by muddy buddy on 8/11/13.
 */
public class Board {



    private Map<Integer, Tile> _fixedTiles;
    private Map<Integer, Tile> _tiles;

    private Tile[][] _tileMatrix;

    /**
     * Setup an empty board
     */
    public Board(){
        _tiles = init();
        _fixedTiles = new HashMap<Integer, Tile>();
    }

    /**
     * Initialize board
     */
    protected Map<Integer, Tile> init(){
        _tiles = new HashMap<Integer, Tile>();
        _tileMatrix = new Tile[9][9];
        for (int i = 0; i < 9; i++) {
            _tileMatrix[i] = new Tile[9];
            for (int j = 0; j < 9; j++) {
                int key = keyFromCoordinates(i, j);
                Tile emptyTile = new Tile();
                _tiles.put(key, emptyTile);
                _tileMatrix[i][j] = emptyTile;
            }

        }
        return _tiles;
    }

    /**
     * Return the {@link Tile} at (row, column}
     */
    public Tile getTile(int row, int column){
        validateCoordinates(row, column);

        int key = keyFromCoordinates(row, column);
        return _tiles.get(key);
    }

    private int keyFromCoordinates(int row, int column){
        return (row * 10) + column;
    }

    public FixedTile setFixedTile(int row, int column, int value){
        validateCoordinates(row, column);
        FixedTile tile = new FixedTile(value);

        int key = keyFromCoordinates(row, column);
        _tiles.put(key, tile);
        _fixedTiles.put(key, tile);
        _tileMatrix[row][column] = tile;
        return tile;
    }

    private void validateCoordinates(int row, int column){
        if (row < 0 || row >= Tile.SUDOKU_NUM || column < 0 || column >= Tile.SUDOKU_NUM){
            throw new IllegalArgumentException("Invalid coordinates row/column " + row +"/" + column);
        }
    }


    public Tile[][] getAllTiles() {
        return _tileMatrix;
    }
}
