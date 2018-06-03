package lexing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Tokenizer {
    private String str;
    private int curIndex;
    private int curRow;
    private int curLine;
    private int curTokenPos;
    private char curCh;
    private LinkedList<Token> tokens;
    private ArrayList<String> ids;
    private HashSet<String> KEY_WORDS ;
    private HashSet<Character> UNARY_OPERATIONS ;
    private HashSet<String> BINARY_OPERATIONS;
    private HashSet<Character> SEPARATORS;
    private HashSet<Character> END_TOKEN;
    private HashSet<Character> EMPTY_SYMBOLS;

    public static class Builder{
        private HashSet<String> keyWords ;
        private HashSet<Character> unaryOperations;
        private HashSet<String> binaryOperations;
        private HashSet<Character> separators;
        private HashSet<Character> endTokens;
        private HashSet<Character> emptySymbols;

        public Builder(){}

        public Builder withKeyWords(HashSet<String> keyWords){
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

        public Tokenizer build(){
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

    public List<Token> tokenize(String str){
        init(str);

        Token token = nextToken();

        while (!Types.END_TOKEN.equals(token) && token != null){
            System.out.println(token.getValue());
            token = nextToken();
        }
        return tokens;
    }

    private Tokenizer(){

    }

    private void init(String str){
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

        if (isLetter(curCh)){
            return processLetterToken();
        }
        if (isDigit(curCh)){
            return processConstToken();
        }
        return processOtherTokens();
    }

    // Token processors

    private Token processLetterToken(){
        String token = "";
        int i = curIndex;
        char ch = str.charAt(i);
        while (isLetter(ch)){
            token += ch;
            i++;
            ch=str.charAt(i);
        }
        if (KEY_WORDS.contains(token)){
            return addToken(token, Types.KEY_WORD, getPosition());
        }
        else{
            if (ids.indexOf(token) < 0){
                ids.add(token);
            }
            return addToken(Integer.toString(ids.indexOf(token)), Types.ID, getPosition());

        }
    }

    private Token processConstToken(){
        String token = "";
        int i = curIndex;
        while (isDigit(str.charAt(i))){
            token +=curCh;
            i++;
        }

        return addToken(token, Types.CONST, getPosition());
    }

    private Token processOtherTokens(){

        if (EMPTY_SYMBOLS.contains(curCh)){
            incIndex();
            return nextToken();
        }

        if (UNARY_OPERATIONS.contains(curCh)){
            return addToken(String.valueOf(curCh), Types.UNARY_OPERATOR, getPosition());
        }

        if (SEPARATORS.contains(curCh)){
            return addToken(String.valueOf(curCh), Types.SEPARATOR,  getPosition());
        }

        if (BINARY_OPERATIONS.contains(curCh)){
            return addToken(String.valueOf(curCh), Types.BINARY_OPERATOR,  getPosition());
        }

        if (curCh == '='){
            String token =  String.valueOf(curCh);
            token += str.charAt(curIndex+1);
            if ("==".equals(token)){
                return addToken(token, Types.BINARY_OPERATOR,  getPosition());
            }
        }

        return addToken(String.valueOf(curCh), Types.UNDEFINED, getPosition());
    }


    // Character checks
    private boolean isDigit(char ch){
        return Character.isDigit(ch);
    }

    private boolean isLetter(char ch){
        return Character.isLetter(ch);
    }


    private boolean isKeyWord(String str) {
        return KEY_WORDS.contains(str);
    }

    private boolean isEndToken(char ch) {
        return END_TOKEN.contains(ch);
    }

    private boolean isLineBreaker(char ch){
        return ch == '\n';
    }



    //utils

    private void decIndex(){
        curIndex--;
        curRow--;
        curCh = str.charAt(curIndex);
    }
    private void incIndex() {
        curIndex++;
        curRow++;
    }

    private void incIndex(int i){
        curIndex += i;
        curRow += i;
    }

    private void incLine() {
        curIndex++;
        curLine++;
        curRow = 0;
        curCh = str.charAt(curIndex);
    }

    private Position getPosition(){
        return new Position(curLine, curTokenPos);
    }

    private Token addToken(String value, Types type, Position pos){
        Token token = new Token(value, type, pos);
        tokens.add(token);
        incIndex(value.length());
        return token;
    }

    public void setUnaryOperations(HashSet<Character> UNARY_OPERATIONS) {
        this.UNARY_OPERATIONS = UNARY_OPERATIONS;
    }
}

