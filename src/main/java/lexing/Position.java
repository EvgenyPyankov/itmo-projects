package lexing;

public class Position {
    private int line;
    private int row;

    public Position(int line, int row) {
        this.line = line;
        this.row = row;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}

