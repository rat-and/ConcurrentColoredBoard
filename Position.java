package sample;

/**
 * Class made for HashMap purposes - used as a key so its equals and hashCodes are overridden
 * Basically it represents a 2Dpoint of both integer values
 */
public class Position extends Object{
    public int rowNo, columnNo;

    public int getRowNo() {
        return rowNo;
    }

    public int getColumnNo() {
        return columnNo;
    }

    public void setColumnNo(int columnNo) {
        this.columnNo = columnNo;
    }

    public void setRowNo(int rowNo) {
        this.rowNo = rowNo;
    }

    /**
     * Non-argument constructor, just creates an empty position
     */
    public Position() {}

    /**
     * Constructs a position with given parameters
     * @param rowNo X cartesian coordinate (integer!)
     * @param columnNo Y cartesian coordinate (integer!)
     */
    public Position(int rowNo, int columnNo) {
        this.rowNo = rowNo;
        this.columnNo = columnNo;
    }

    /**
     * In order to use Position object as a key object in HashMap this method has to be simplified
     * - meaning overridden. Two Position objects are equal if their coordinates (ints) are equal
     * @param other
     * @return True if both coordinates are equal
     */
    @Override
    public boolean equals(Object other) {
        if(other == null) return false;
        if (!(other instanceof Position)) {
            return false;
        }
        Position otherPosition = (Position) other;
        return rowNo == otherPosition.getRowNo() && columnNo == otherPosition.getColumnNo();
    }

    /**
     * Like equal method it has to be overridden in order to use Position object as a key object in HashMap
     * It's as simplified as can only be
     * @return always 1
     */
    @Override
    public int hashCode()
    {
        return 1;
    }
}
