package gameoflife;

public class Cell {
    private boolean isAlive;

    Cell() {
        this.isAlive = false;
    }

    Cell(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public String toString() {
        return isAlive ? "X" : ".";
    }

    boolean isAlive() {
        return isAlive;
    }

    void setIsAlive(boolean newState) {
        this.isAlive = newState;
    }

    /*
     * processState()
        * isAlive = status of the passed cell
        * nbNeighbourCellsAlive = number of neighbours alive
     * Checks if cell stay alive or not depending on the state of the cell and of the cells around
     * Returns true if the cell stays alive
     * Else returns false
     */
    static boolean processState(boolean isAlive, int nbNeighbourCellsAlive) {
        if (isAlive == true) {
            if (nbNeighbourCellsAlive == 2 || nbNeighbourCellsAlive == 3)
                return true;

        } else {
            if (nbNeighbourCellsAlive == 3)
                return true;
        }
        return false;
    }
}
