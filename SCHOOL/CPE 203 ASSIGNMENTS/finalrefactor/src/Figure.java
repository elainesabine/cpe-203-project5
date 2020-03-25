import java.util.Objects;

public abstract class FigureAbstract {
    public static int nextid=0;
    public int id;

    public String symbol;
    public boolean happy;

    public FigureAbstract(String symbol) {
        this.id = this.nextid;
        this.nextid++;
        this.symbol=symbol;
        happy = true;
    }

    public abstract void drawFig();

    public void setSymbol(String newS) { symbol = newS; }

    public String toString() {
        return "Id: " + id + ", " + getClass().getName();
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Figure oF = (Figure) o;
        return Objects.equals(symbol,oF.symbol);
    }

}
