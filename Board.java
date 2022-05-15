
/**
 * A class to maintain update the current state of the board.
 *
 * @author Ms Caitlin Woods
 * @version April 2022
 * 
 * Student Names and Numbers: { ENTER YOUR STUDENT NAME AND NUMBER HERE }
 */

import java.util.ArrayList;

public class Board {

    Piece[][] board; // A 2D array of pieces, representing the state of a board.

    /**
     * Constructor for the Board class
     * Initializes the board with 12 rows and columns
     * and sets all locations to VACANT
     */
    public Board() {
        board = new Piece[12][12];
        for (int x = 0; x < 12; x++) { 
            for (int y = 0; y <12; y++) {
                board[x][y] = Piece.VACANT;
            }
        }
    }

    /**
     * Constructor for the Board class
     * Initializes the board with the given number of rows and columns
     * and sets all locations to defaultValue
     */
    public Board(int rows, int cols, Piece defaultValue) {
        board = new Piece[rows][cols];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                board[x][y] = defaultValue;
            }
        }
    }

    /**
     * Returns the size of the board as an integer (i.e. 12x12 board should return
     * 12)
     * hint: You can assume the grid is square (i.e. rows == cols)
     * @return the size of the board
     */
    public int getBoardSize() {
        return board.length;
    }

    /**
     * Returns true if x and y are coordinates in the board, false otherwise
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if x and y are coordinates in the board, false otherwise
     */
    public boolean isPiece(int x, int y) {
        if ((x >= 0 && x < board.length) && (y >= 0 && y < board.length)) {
            return true;
        }
        else { return false; }
    }

    /**
     * Returns the value of the piece at the given coordinates
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the value of the piece at the given coordinates
     * @throws IllegalArgumentException if x and y are not valid coordinates
     */
    public Piece getPiece(int x, int y) {
        if (isPiece(x,y)) {
            return board[x][y];
        }
        else { throw new IllegalArgumentException("Invalid coordinates entered");
        }
    }
    
    /**
     * Check if a given piece is part of a lost item
     * Throw an exeption if the piece is not in the board
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if a given piece is part of a lost item, false otherwise
     * @throws IllegalArgumentException if the piece is not in the board
     */
    public boolean isLostItem(int x, int y) {
        if (getPiece(x,y) == Piece.LOSTITEM) {
            return true;
        }
        else { return false; }
    }
    
    /**
     * Sets the given piece to be part of a lost item (i.e. an item is hidden at
     * this location)
     * Throw an exception if the piece is not on the board
     * @param x the x coordinate
     * @param y the y coordinate
     * @throws IllegalArgumentException if the piece is not in the board
     */
    public void setLostItem(int x, int y) {
        board[x][y] = getPiece(x,y);
        board[x][y] = Piece.LOSTITEM;
    }

    /**
     * Check if a given piece is vacant
     * Throw an exeption if the piece is not in the board
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if a given piece is vacant, false otherwise
     * @throws IllegalArgumentException if the piece is not in the board
     */
    public boolean isVacant(int x, int y) {
        if (getPiece(x,y) == Piece.VACANT) {
            return true;
        }
        else { return false; }
    }
    
    /**
     * Sets the given piece to be vacant (i.e. no item is on this location)
     * Throw an exception if the piece is not on the board
     * @param x the x coordinate
     * @param y the y coordinate
     * @throws IllegalArgumentException if the piece is not in the board
     */
    public void setVacant(int x, int y) {
        board[x][y] = getPiece(x,y);
        board[x][y] = Piece.VACANT;
    }
    
    /**
     * Check if a given piece is part of an item that has been found by the player
     * Throw an exeption if the piece is not in the board
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if a given piece is part of a found item, false otherwise
     * @throws IllegalArgumentException if the piece is not in the board
     */
    public boolean isFoundItem(int x, int y) {
        if (getPiece(x,y) == Piece.FOUNDITEM) {
            return true;
        }
        else { return false; }
    }

    
    /**
     * Sets the given piece to be part of a found item (i.e. an item is hidden at
     * this location and this piece has been found by the player)
     * Throw an exception if the piece is not on the board
     * @param x the x coordinate
     * @param y the y coordinate
     * @throws IllegalArgumentException if the piece is not in the board
     */
    public void setFoundItem(int x, int y) {
        board[x][y] = getPiece(x,y);
        board[x][y] = Piece.FOUNDITEM;
    }
    
    /**
    * Check if a given piece has been searched by the player (but does not contain
     * any part of a hidden item)
     * Throw an exeption if the piece is not in the board
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if a given piece is part of a found item, false otherwise
     * @throws IllegalArgumentException if the piece is not in the board
     */
    public boolean isSearched(int x, int y) {
        if (getPiece(x,y) == Piece.SEARCHED) {
            return true;
        }
        else { return false; }
    }
    
    /**
     * Sets the given piece to "searched" (i.e. no item has been hidden at this
     * location and the player has searched this space)
     * Throw an exception if the piece is not on the board
     * @param x the x coordinate
     * @param y the y coordinate
     * @throws IllegalArgumentException if the piece is not in the board
     */
    public void setSearched(int x, int y) {
        board[x][y] = getPiece(x,y);
        board[x][y] = Piece.SEARCHED;
    }


    /**
     * Sets the given piece to a new state, after it has been searched according to
     * the following rules:
     * 1) If the space is currently VACANT, set it to SEARCHED
     * 2) If the space is currently LOST, set it to FOUND
     * 3) Return true if the search was successful (or unnecessary), false otherwide
     * @param x
     * @param y
     * @return true if the search was successful (or unnecessary), false otherwise
     */
    public boolean searchSpace(int x, int y) {
        if (isVacant(x,y)) {
            setSearched(x,y);
            return false;
        }
        else if (isLostItem(x,y)) {
            setFoundItem(x,y);
            return true;
        }
        else if (isSearched(x,y) || isFoundItem(x,y)) {
            return true;
        }
        else { return false; }
    }
    
    /**
     * Find the closest lost item piece to the piece that has just been touched (at [touchedX, touchedY]).
     * Return the "number of spaces away" that the closest lost item is.
     * An item is considered "the closest" if the "number of spaces away in the x direction" 
     * plus the "number of spaces away in the y direction" is minimal.
     * @param touchedX the x coordinate of the touched point
     * @param touchedY the y coordinate of the touched point
     * @return an array of size 2 containing the number of spaces away 
     * in the X direction and the number of spaces away in the Y direction 
     * where the array is in the form [x, y]
     */
    public int[] getClosestItem(int touchedX, int touchedY) {
        int[] minimalDist = {board[0].length, board.length};
        for (int i = 0; i < board[0].length; i++){
            for (int j = 0; j < board.length; j++){
                boolean isCloser = Math.abs(i - touchedX) + Math.abs(j - touchedY) < minimalDist[0] + minimalDist[1];
                if (board[i][j] == Piece.LOSTITEM && isCloser){
                    minimalDist[0] = Math.abs(i - touchedX);
                    minimalDist[1] = Math.abs(j - touchedY);
                }
            }
        }
        return minimalDist;
    }
    
    /**
     * Checks if a whole item has been found on the board
     * An item is considered "found" if there are a cluster of spaces on the board that 
     * have a value of FOUNDITEM, and that cluster is in the shape of the item. 
     * @param item the item to check.
     * @return true if the item has been found, false otherwise.
     */
    public boolean checkForFoundItem(Item item) {
        boolean isItemFound = true;
        int[][] shape = item.getShape();
        Piece[][] matrix = new Piece[shape.length][shape[0].length];
        
        for (int i = item.getLocationY(); i < shape.length; i++) {
            for (int j = item.getLocationX(); j < shape[0].length; j++) {
                matrix[i][j] = board[i][j];
            }
        }
        
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if ((shape[i][j] == 1) && (matrix[i][j] != Piece.FOUNDITEM)){
                    isItemFound = false;
                }
            }
        }
        
        if (isItemFound){
            item.setFound();
        }
        return isItemFound;
    }
     /**
     * Goes through a list of items and checks which of the items have been found.
     * (hint: use a helper method that you have already written).
     * @return an array list of items that have been found.
     */
    public ArrayList<Item> getFoundItemsList(Item[] items) {
        ArrayList<Item> founditems = new ArrayList<>();
        for (Item item : items) {
            checkForFoundItem(item);
            if (item.getIsFound()) {
                founditems.add(item);
            }
        }
        return founditems;
    }
}
