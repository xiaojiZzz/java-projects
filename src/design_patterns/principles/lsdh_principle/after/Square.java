package design_patterns.principles.lsdh_principle.after;

public class Square implements Quadrilateral {

    private double side;

    public double getSize() {
        return side;
    }

    public void setSize(double side) {
        this.side = side;
    }

    @Override
    public double getLength() {
        return side;
    }

    @Override
    public double getWidth() {
        return side;
    }
}
