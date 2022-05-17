// Blake Lawyer (bpl170001@utdallas.edu)

package com.mycompany.project4;

// Cell objects will be stored in a global 2D array to track whether walls have been knocked down or not, and their index.
public class Cell {
    
    // Wall booleans and index int (for cell labeling)
    boolean up;
    boolean down;
    boolean left;
    boolean right;
    int index;
    
    // Contructor
    Cell(int count) {
        up = true;
        down = true;
        left = true;
        right = true;
        index = count;
    }
    
    // Getters
    boolean getUp() {
        return up;
    }
    
    boolean getDown() {
        return down;
    }
    
    boolean getLeft() {
        return left;
    }
    
    boolean getRight() {
        return right;
    }
    
    int getIndex() {
        return index;
    }
    
    // Setters
    void setUp(boolean ToF) {
        up = ToF;
    }
    
    void setDown(boolean ToF) {
        down = ToF;
    }
    
    void setLeft(boolean ToF) {
        left = ToF;
    }
    
    void setRight(boolean ToF) {
        right = ToF;
    }
    
}
