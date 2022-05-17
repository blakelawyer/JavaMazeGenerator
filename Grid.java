// Blake Lawyer (bpl170001@utdallas.edu)

package com.mycompany.project4;

// Import needed packages.
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.Random;
import javax.swing.JPanel;

public class Grid extends JPanel{ // This is our JPanel to display the maze, so extend JPanel.
    
    final int ORIGIN = 50; // Pixels away from the corner to avoid window header.
    final int edgeLength = 40; // Length of cell edges.
    int rowNum; // Row number will be passed when initializing Grid.
    int colNum; // Column number will be passed when initializing Grid.

    // Constructor, given rows and columns.
    Grid(int rows, int columns) {
        this.setPreferredSize(new Dimension(1000, 1000)); // Set size of the JPanel.
        rowNum = rows; // Set row number.
        colNum = columns; // Set column number.
    }
    
    // Override for custom JPanel paint function.
    @Override
    public void paint(Graphics g) {
         
        // X and y pixels away from the corner to avoid window header.
        int x = ORIGIN; 
        int y = ORIGIN; 
        
        Graphics2D g2D = (Graphics2D) g;
        
        for (int i = 0; i < rowNum; i++) { // Loop through rows..
            for (int j = 0; j < colNum; j++) { // Loop through columns..
                
                // Labels each cell of the maze by its index.
                g2D.drawString(Integer.toString(Maze.cellInfo[i][j].getIndex()), x + 5, y + 15);
                
                // Draw lines for each wall of the cell.
                if (Maze.cellInfo[i][j].getUp())
                    g2D.drawLine(x, y, x + edgeLength, y); // Draw line to the right, creating the up wall.
                x += edgeLength;
                if (Maze.cellInfo[i][j].getRight())
                    g2D.drawLine(x, y, x, y + edgeLength); // Draw line down, creating the right wall.
                y += edgeLength;
                if (Maze.cellInfo[i][j].getDown())
                    g2D.drawLine(x, y, x - edgeLength, y); // Draw line to the left, creating the down wall.
                x -= edgeLength;
                if (Maze.cellInfo[i][j].getLeft())
                    g2D.drawLine(x, y, x, y - edgeLength); // Draw line up, creating the left wall.
                y -= edgeLength;
                x+= edgeLength;
            }
            x = ORIGIN;
            y += edgeLength;
        }
        
        if (Maze.unionCount == rowNum * colNum - 1) { // If the maze generation is complete..
             g2D.drawString("Maze generation complete." + " (" + Maze.unionCount + "/" + (rowNum * colNum - 1) + " unions)", 30, 30);
        } else { // If the maze is still generating..
            DecimalFormat df = new DecimalFormat("#.00");
            if (Maze.unionCount != 0)
                g2D.drawString("Generating maze: " + df.format((double) Maze.unionCount/(double)(rowNum*colNum) * 100) + "%" +
                        " (" + Maze.unionCount + "/" + (rowNum * colNum - 1) + " unions)", 
                        30, 30);
        }
   
    } // paint
    
} // Grid
