package com.example.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import com.example.sudoku.model.Board;
import com.example.sudoku.model.FixedTile;
import com.example.sudoku.model.SudokuController;
import com.example.sudoku.model.Tile;

import java.util.ArrayList;

public class SudokuBoard extends Activity {

    public static final String CELL = "Sudoku.CELL_COORDINATES";
    private static final int BUTTON_SIZE = 64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        Board easyBoard = SudokuController.createEasyGame();

        for (int i = 0; i < easyBoard.getAllTiles().length; i++) {
            Tile[] tiles = easyBoard.getAllTiles()[i];
            for (int j = 0; j < tiles.length; j++) {
                Tile tile = tiles[j];
                Button v = getViewFromCoordinates(i, j);
                if (v != null){
                    if (!tile.canEdit()){
                       v.setTextSize(20);
                       v.setText("" + tile.getValue());
                    }
                } else {
                    System.out.println("Could not find view for " + i + " / " + j);
                }
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sudoku_board, menu);
        return true;
    }

    public void updateCell(View view){

        Intent theIntent = new Intent(this, SudokuCellPopup.class);
        theIntent.putExtra(CELL, getCoordinatesFromView(view));
        getCoordinatesFromView(view);

        startActivity(theIntent);
    }

    private int[] getCoordinatesFromView(View view){
        System.out.println("Left value is " + view.getLeft());
        System.out.println("Right value is " + view.getRight());


        System.out.println("Top value is " + view.getTop());
        System.out.println("Bottom value is " + view.getBottom());

        int rowIndex = view.getRight() / BUTTON_SIZE;
        int colIndex = view.getBottom() / BUTTON_SIZE;
        return new int[]{ rowIndex, colIndex };
    }


    protected Button getViewFromCoordinates(int row, int column){
        GridLayout grid = (GridLayout) findViewById(R.id.gridboard);

        ArrayList<View> views = grid.getTouchables();

        int matchingX = row * BUTTON_SIZE;
        int matchingY = column * BUTTON_SIZE;
        for (View view : views) {
            if (view.getRight() == matchingX && view.getBottom() == matchingY){
                return (Button) view;
            }
        }
        return null;
    }
    
}
