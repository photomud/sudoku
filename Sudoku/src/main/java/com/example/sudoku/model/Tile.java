package com.example.sudoku.model;

/**
 * Created by muddy buddy on 8/11/13.
 */
public class Tile {
    public static final int EMPTY = -1;
    public static final int SUDOKU_NUM = 9;

    private int _value;
    private int[] _notes;

    public Tile(){
        _value = EMPTY;
        _notes = new int[SUDOKU_NUM];
    }

    public int getValue(){
        return _value;
    }

    public int[] getNotes(){
        return _notes;
    }

    public boolean canEdit(){
        return true;
    }

    /**
     * Clear out the {@link #getValue()} and any {@link #getNotes()} stored on this tile.
     */
    public void clean(){
        if (canEdit()){
            _value = EMPTY;
            _notes = new int[SUDOKU_NUM];
        }
    }

    /**
     * If this tile is #empty() and #canEdit(), add a note to the notes values.
     * @param note the sudoku note to add.
     */
    public void addNote(int note){
        checkNumLegal(note);
        if (canEdit() && empty()){
            getNotes()[note-1] = note;
        }
    }

    public static void checkNumLegal(int num){
        if (!((num >= 1) && (num <= SUDOKU_NUM))){
          throw new IllegalArgumentException("The number " + num + " must be between 1 and 9");
        }
    }

    /**
     * @return true if no value has been set
     */
    public boolean empty(){
        return getValue() == EMPTY;
    }
}
