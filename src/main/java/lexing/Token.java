package lexing;

public class Token {
    private int code;
    private String value;
    private Position position;
    private Types type;

    public Token(String value, Types type, Position position) {
        this.value = value;
        this.position = position;
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
