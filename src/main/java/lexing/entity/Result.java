package lexing.entity;

import java.util.ArrayList;
import java.util.LinkedList;

public class Result {
    private ArrayList<String> ids;
    private LinkedList<Token> tokens;

    public Result(ArrayList<String> ids, LinkedList<Token> tokens) {
        this.ids = ids;
        this.tokens = tokens;
    }

    public ArrayList<String> getIds() {
        return ids;
    }

    public void setIds(ArrayList<String> ids) {
        this.ids = ids;
    }

    public LinkedList<Token> getTokens() {
        return tokens;
    }

    public void setTokens(LinkedList<Token> tokens) {
        this.tokens = tokens;
    }
}
