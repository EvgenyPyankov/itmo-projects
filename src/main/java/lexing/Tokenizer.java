package lexing;

import lexing.enity.Position;
import lexing.enity.Result;
import lexing.enity.Token;
import lexing.enity.Types;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class Tokenizer {
    private String str;
    private int curIndex;
    private int curRow;
    private int curLine;
    private int curTokenPos;
    private char curCh;
    private LinkedList<Token> tokens;
    private ArrayList<String> ids;
    private HashSet<String> KEY_WORDS;
    private HashSet<Character> UNARY_OPERATIONS;
    private HashSet<String> BINARY_OPERATIONS;
    private HashSet<Character> SEPARATORS;
    private HashSet<Character> END_TOKEN;
    private HashSet<Character> EMPTY_SYMBOLS;

    public static class Builder {
        private HashSet<String> keyWords;
        private HashSet<Character> unaryOperations;
        private HashSet<String> binaryOperations;
        private HashSet<Character> separators;
        private HashSet<Character> endTokens;
        private HashSet<Character> emptySymbols;

        public Builder() {
        }

        public Builder withKeyWords(HashSet<String> keyWords) {
            this.keyWords = keyWords;
            return this;
        }

        public Builder withUnaryOperations(HashSet<Character> unaryOperations) {
            this.unaryOperations = unaryOperations;
            return this;
        }

        public Builder withBinaryOperations(HashSet<String> binaryOperations) {
            this.binaryOperations = binaryOperations;
            return this;
        }

        public Builder withSeparators(HashSet<Character> separators) {
            this.separators = separators;
            return this;
        }

        public Builder withEndTokens(HashSet<Character> endTokens) {
            this.endTokens = endTokens;
            return this;
        }

        public Builder withEmptySymbols(HashSet<Character> emptySymbols) {
            this.emptySymbols = emptySymbols;
            return this;
        }

        public Tokenizer build() {
            Tokenizer tokenizer = new Tokenizer();
            tokenizer.KEY_WORDS = keyWords;
            tokenizer.UNARY_OPERATIONS = unaryOperations;
            tokenizer.BINARY_OPERATIONS = binaryOperations;
            tokenizer.SEPARATORS = separators;
            tokenizer.END_TOKEN = endTokens;
            tokenizer.EMPTY_SYMBOLS = emptySymbols;
            return tokenizer;
        }
    }

    public Result tokenize(String str) {
        init(str.trim());

        Token token = nextToken();
        while (!Types.END_TOKEN.equals(token) && token != null) {
            token = nextToken();
        }
        Result result = new Result(ids, tokens);
        return result;
    }

    private Tokenizer() {
    }

    private void init(String str) {
        this.str = str;
        curIndex = 0;
        curRow = 0;
        curLine = 0;
        curTokenPos = 0;
        tokens = new LinkedList<Token>();
        ids = new ArrayList<String>();
    }

    private Token nextToken() {

        if (curIndex >= str.length())
            return null;

        curCh = str.charAt(curIndex);
        curTokenPos = curRow;

        if (isLetter(curCh)) {
            return obtainLetterToken();
        }
        if (isDigit(curCh)) {
            return obtainConstToken();
        }
        return obtainOtherTokens();
    }

    // Token processors

    private Token obtainLetterToken() {
        String token = "";
        int i = curIndex;
        char ch = str.charAt(i);
        while (isLetter(ch)) {
            token += ch;
            i++;
            ch = str.charAt(i);
        }
        if (isKeyWord(token)) {
            return addToken(token, Types.KEY_WORD, getPosition());
        } else {
            if (ids.indexOf(token) < 0) {
                ids.add(token);
            }
            return addToken(token, Types.ID, getPosition());

        }
    }

    private Token obtainConstToken() {
        String token = "";
        int i = curIndex;
        while (isDigit(str.charAt(i))) {
            token += curCh;
            i++;
        }

        return addToken(token, Types.CONST, getPosition());
    }


    private Token obtainOtherTokens() {

        Token token = obtainAmbiguousToken();
        if (token != null){
            return token;
        }

        if (isLineBreaker(curCh)) {
            incLine();
            return nextToken();
        }

        if (EMPTY_SYMBOLS.contains(curCh)) {
            incIndex(1);
            return nextToken();
        }

        if (UNARY_OPERATIONS.contains(curCh)) {
            return addToken(curCh, Types.UNARY_OPERATOR, getPosition());
        }

        if (SEPARATORS.contains(curCh)) {
            return addToken(curCh, Types.SEPARATOR, getPosition());
        }

        if (BINARY_OPERATIONS.contains(String.valueOf(curCh))) {
            return addToken(curCh, Types.BINARY_OPERATOR, getPosition());
        }

        if (isEndToken(curCh)) {
            return addToken(curCh, Types.END_TOKEN, getPosition());
        }

        return addToken(curCh, Types.UNDEFINED, getPosition());
    }

    private Token obtainAmbiguousToken(){
        if (curCh == ':') {
            String token = String.valueOf(curCh);
            token += str.charAt(curIndex + 1);
            if (":=".equals(token)) {
                return addToken(token, Types.BINARY_OPERATOR, getPosition());
            } else {
                return addToken(curCh, Types.SEPARATOR, getPosition());
            }
        }
        if (curCh == '=') {
            String token = String.valueOf(curCh);
            token += str.charAt(curIndex + 1);
            if ("==".equals(token)) {
                return addToken(token, Types.BINARY_OPERATOR, getPosition());
            }
        }
        return null;
    }

    // Сhecks

    private boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    private boolean isLetter(char ch) {
        return Character.isLetter(ch);
    }

    private boolean isKeyWord(String str) {
        return KEY_WORDS.contains(str);
    }

    private boolean isEndToken(char ch) {
        return END_TOKEN.contains(ch);
    }

    private boolean isLineBreaker(char ch) {
        return ch == '\n';
    }

    // Utils

    private void incIndex(int i) {
        curIndex += i;
        curRow += i;
    }

    private void incLine() {
        curIndex++;
        curLine++;
        curRow = 0;
        curCh = str.charAt(curIndex);
    }

    private Position getPosition() {
        return new Position(curLine, curTokenPos);
    }

    private Token addToken(Object val, Types type, Position pos) {
        String value = String.valueOf(val);
        incIndex(value.length());
        if (Types.ID.equals(type)) {
            value = String.valueOf(ids.indexOf(value));
        }
        Token token = new Token(value, type, pos);
        tokens.add(token);
        return token;
    }
}

