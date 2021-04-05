package gameoflife;

import java.util.Random;

public class Grid {
    private Cell[][] cells;
    private Cell[][] newCells;
    private int sizeGrid;
    private Random rd;
    /*
    * Initializes this.Cells
    * Generate the initial state of the grid
    */

    public Grid(int sizeGrid) {
        this.rd = new Random();
        this.sizeGrid = sizeGrid;
        this.cells = new Cell[sizeGrid][sizeGrid];
        generateRandomInitialState();
    }

    Grid(int sizeGrid, Cell[][] cells) {
        this.sizeGrid = sizeGrid;
        this.cells = cells;
    }

    /*
     * generateRandomInitialState()
     * Initializes this.cells
     * Randomly sets the state of each cell
     */

    private void generateRandomInitialState() {
        double r ;
        int i, j;
        for (i = 0; i < this.sizeGrid; ++i) {
            for (j = 0; j < this.sizeGrid; ++j) {
                this.cells[i][j] = new Cell();
            }
        }
        for (i = 0 ; i < this.cells.length ; ++i) {
            for (j = 0 ; j < this.cells[i].length ; ++j) {
                r= Math.random();
                System.out.print(r);
                if (r < 0.5)
                    this.cells[i][j].setIsAlive(false);
                else
                    this.cells[i][j].setIsAlive(true);

            }
        }
    }

    /*
     * generateNextState()
     * Copies this.cells into this.newCells to create the next state of the grid
     * Counts the neighbours of each cell then checks if she's alive and
     * Then calls processState() to update the status of this cell
     */
    public void generateNextState() {
        int aliveNeighbours;
        boolean state ;

        this.newCells = new Cell[this.cells.length][this.cells.length];
        for (int i = 0; i < this.cells.length; ++i) {
            for (int j = 0; j < this.cells[i].length; ++j) {
                this.newCells[i][j] = new Cell(this.cells[i][j].isAlive());
            }
        }

        for (int i = 0; i < this.cells.length; ++i) {
            for (int j = 0; j < this.cells[i].length; ++j) {
                aliveNeighbours = this.countNeighboursAlive(i, j) ;
                state = newCells[i][j].isAlive();
                state = newCells[i][j].processState(state, aliveNeighbours);
                this.cells[i][j].setIsAlive(state);
            }
        }
    }

    /*
     * countNeighboursAlive()
     * Counts the number of neighbour Cells alive around one cell on line lin and column col
     * Returns number of Neighbours alive
     */

    private int countNeighboursAlive (int lin, int col) {
        int alive = 0, x, y ;
        for (int i = -1 ; i <= 1 ; ++i) {      // these two loops look at all the neighbours of a cell
            for (int j = -1 ; j <= 1 ; ++j) {
                x = lin + i;
                y = col + j;

                if (x >= 0 && y >= 0 && x < this.cells.length && y < this.cells[0].length) //condition to check the array boundaries
                    if (cells[x][y] != cells[lin][col])
                        alive += this.newCells[x][y].isAlive() == true ? 1 : 0;
            }
        }
        return alive ;
    }

    /*
     * toString()
     * Writes the Grid as a string output
     */
    public String toString() {
        String str = "" ;
        for (int i = 0 ; i < this.cells.length ; ++i) {
            for (int j = 0 ; j < this.cells[i].length ; ++j) {
                str += this.cells[i][j].toString();
                if (j != this.cells.length - 1)
                    str += ' ';
            }
            if (i != this.cells.length - 1)
                str += '\n' ;
        }
        return str;
    }
}
