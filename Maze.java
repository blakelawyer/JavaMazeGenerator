// Blake Lawyer - bpl170001@utdallas.edu

package com.mycompany.project4;

// Import needed packages.
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Maze {
    
    public static Cell[][] cellInfo; // Global 2D array to track cell/wall information.
    public static int unionCount = 0; // Global int to track number of unions made.
    
    public static void main(String[] args) throws InterruptedException {
        
        Scanner scanner = new Scanner(System.in); // Create Scanner to get user input.
        System.out.println("Enter number of rows for the maze:"); // Prompt user for row number.
        int rows = scanner.nextInt(); 
        System.out.println("Enter number of columns for the maze:"); // Promp user for column number.
        int columns = scanner.nextInt();
        
        Random rand = new Random(); // Create Random object to generate cell indexes.
        int max = rows*columns; // Set the non-inclusive max for random int generation.
        
        DisjSets disjSet = new DisjSets(rows*columns); // Create our disjoint set with number cells specified by user.
        
        JFrame frame = new JFrame("Disjoint-Set Maze Generator"); // Create our JFrame.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Program will end upon exiting window.
        
        cellInfo = new Cell[rows][columns]; // Specify number of rows and columns for our 2D Cell array.
        
        // Loops through rows and columns, initializing cells with walls on each side and an index.
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cellInfo[i][j] = new Cell(count);
                count += 1;
            }
        }
        
        // Knocks down some top left and bottom right walls to specify the endpoints.
        cellInfo[0][0].setLeft(false);
        cellInfo[0][0].setUp(false);
        cellInfo[rows-1][columns-1].setRight(false);
        cellInfo[rows-1][columns-1].setDown(false);
        
        JPanel panel = new Grid(rows, columns); // Initializes the JPanel to go on the JFrame.
        
        frame.add(panel); // Add the JPanel to the JFrame.
        frame.pack(); // Packs the frame so everything fits correctly.
        frame.setVisible(true); // Set the visibility so we can see our GUI.
        
        boolean visable = true; // Boolean to handle redrawing the screen.
        
        while(unionCount != rows*columns - 1) { // As long as the correct number of unions have not taken place (N-1)..
            
            // Randomly select 2 cells.
            int cell1 = rand.nextInt(max); 
            int cell2 = rand.nextInt(max);
            
            if (cell2 - columns == cell1) { // If cell1 is above cell2..
                if (disjSet.find(cell1) != disjSet.find(cell2)) { // If the cells are not in the same set..
                    disjSet.union(disjSet.find(cell1), disjSet.find(cell2)); // Union by height of the roots of each cell.
                    unionCount += 1; // Increase the union counter.
                    cellInfo[cell1 / columns][cell1 % columns].setDown(false); // Know down the bottom wall.
                    cellInfo[cell2 / columns][cell2 % columns].setUp(false); // Know down the top wall.
                }
            } else if (cell2 + columns == cell1) { // If cell1 is below cell2..
                if (disjSet.find(cell1) != disjSet.find(cell2)) { // If the cells are not in the same set..
                    disjSet.union(disjSet.find(cell1), disjSet.find(cell2)); // Union by height of the roots of each cell.
                    unionCount += 1; // Increase the union counter.
                    cellInfo[cell1 / columns][cell1 % columns].setUp(false); // Know down the top wall.
                    cellInfo[cell2 / columns][cell2 % columns].setDown(false); // Know down the bottom wall.
                }
            } else if (cell1 - 1 == cell2 && cell1 % columns != 0) { // If cell1 is to the right of cell2..
                if (disjSet.find(cell1) != disjSet.find(cell2)) { // If the cells are not in the same set..
                    disjSet.union(disjSet.find(cell1), disjSet.find(cell2)); // Union by height of the roots of each cell.
                    unionCount += 1; // Increase the union counter.
                    cellInfo[cell1 / columns][cell1 % columns].setLeft(false); // Know down the left wall.
                    cellInfo[cell2 / columns][cell2 % columns].setRight(false); // Know down the right wall.

                }
            } else if (cell1 + 1 == cell2 && cell1 % columns != columns - 1) { // If cell 1 is the the left of cell2..
                if (disjSet.find(cell1) != disjSet.find(cell2)) { // If the cells are not in the same set..
                    disjSet.union(disjSet.find(cell1), disjSet.find(cell2)); // Union by height of the roots of each cell.
                    unionCount += 1; // Increase the union counter.
                    cellInfo[cell1 / columns][cell1 % columns].setRight(false); // Know down the right wall.
                    cellInfo[cell2 / columns][cell2 % columns].setLeft(false); // Know down the left wall.
                }
                
            }
            
            // If the panel with our maze is visible, remove the panel and repaint the blank one.
                // Otherwise, create a new panel which intializes based on our cellInfo array.
                    // This continously redraws the screen.
            if (visable) {
                frame.remove(panel);
                frame.repaint();
                visable = false;
            } else {
                panel = new Grid(rows, columns);
                frame.add(panel);
                frame.setVisible(true);
                visable = true;
            }
               
        } // while
        
        // When the maze generation is done, repaint the final screen with the completed maze.
        panel = new Grid(rows, columns);
        frame.add(panel);
        frame.setVisible(true);
        
    } // main
    
} // Maze
