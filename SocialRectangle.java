package sample;

import javafx.scene.shape.Rectangle;

/**
 * Extends a Rectangle class by keeping information about its neighbors and its position.
 * It's very helpful
 * @see Rectangle
 */
public class SocialRectangle extends Rectangle {
    Position position = new Position();
    SocialRectangle upNeighbor, downNeighbor, leftNeighbor, rightNeighbor;

    public void setUpNeighbor(SocialRectangle upNeighbor) {
        this.upNeighbor = upNeighbor;
    }

    public SocialRectangle getUpNeighbor() {
        return upNeighbor;
    }

    public void setDownNeighbor(SocialRectangle downNeighbor) {
        this.downNeighbor = downNeighbor;
    }

    public SocialRectangle getDownNeighbor() {
        return downNeighbor;
    }

    public void setLeftNeighbor(SocialRectangle leftNeighbor) {
        this.leftNeighbor = leftNeighbor;
    }

    public SocialRectangle getLeftNeighbor() {
        return leftNeighbor;
    }

    public void setRightNeighbor(SocialRectangle rightNeighbor) {
        this.rightNeighbor = rightNeighbor;
    }

    public SocialRectangle getRightNeighbor() {
        return rightNeighbor;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(int rowNO, int columnNo) {
        position.setRowNo(rowNO);
        position.setColumnNo(columnNo);
    }

    /**
     * Main constructor, basically it overrides Rectangle class constructor
     * @see Rectangle
     * @param x X cartesian coordinate
     * @param y Y cartesian coordinate
     * @param width Width of Rectangle
     * @param height Height of Rectangle
     */
    public SocialRectangle(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    /**
     * Additional constructor, it enables to assign a Position object to rectangle which can be later
     * used as a key object in HashMap
     * @param x X cartesian coordinate
     * @param y Y cartesian coordinate
     * @param width Width Width of Rectangle
     * @param height Height of Rectangle
     * @param rowNO Number of a row in a table when rectangle is placed
     * @param columnNo Number of a column in a table when rectangle is placed
     */
    public SocialRectangle(double x, double y, double width, double height, int rowNO, int columnNo) {
        super(x, y, width, height);
        position.setRowNo(rowNO);
        position.setColumnNo(columnNo);
    }


}
