package gameoflife;

import java.util.Random;

public class Grid {
    private Cell[][] cells;
    private Cell[][] newCells;
    private int sizeGrid;
    private Random rd;
    /*
    * initialized this.Cells
    * And generate the initial state of it
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
     * initialized this.Cells
     * Put random alive or not into it
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
     * Copy Cells by Cells of this.cells into this.newCells
     * Count the Neighbours of one cell then check if she's alive and then call processState to update status of this cell
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
     * Allow to count the number of Neighbours alive around of one cell
     * return number of Neighbours alive
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
