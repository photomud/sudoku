package com.example.sudoku.model;

/**
 * A tile that cannot be changed.
 *
 * Note, this tile must start out with an initial value.
 */
public class FixedTile extends Tile {

    private int _fixedValue;

    public FixedTile(int value){
        super();
        _fixedValue = value;
    }

    @Override
    public boolean canEdit(){
        return false;
    }

    @Override
    public void clean(){
        //no-op
    }

    @Override
    public int getValue(){
        return _fixedValue;
    }
}
