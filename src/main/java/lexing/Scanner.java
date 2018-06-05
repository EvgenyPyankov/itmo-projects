package lexing;

import lexing.entity.Result;
import lexing.entity.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Scanner {

    private final HashSet<String> KEY_WORDS =  Stream.of("Var", "End", "Begin", "Boolean", "Decimal", "If", "Else")
            .collect(Collectors.toCollection(HashSet::new));
    private final HashSet<Character> UNARY_OPERATIONS =  Stream.of('!')
            .collect(Collectors.toCollection(HashSet::new));
    private final HashSet<String> BINARY_OPERATIONS =  Stream.of("&" , "^" , "-" , "+" , "*" , "/" , ">" , "<" , "==")
            .collect(Collectors.toCollection(HashSet::new));
    private final HashSet<Character> SEPARATORS =  Stream.of(',', ':', ';', '(', ')')
            .collect(Collectors.toCollection(HashSet::new));
    private final HashSet<Character> END_TOKEN =  Stream.of('.')
            .collect(Collectors.toCollection(HashSet::new));
    private final HashSet<Character> EMPTY_SYMBOLS = Stream.of(' ', '\t', '\n', '\r')
            .collect(Collectors.toCollection(HashSet::new));

    public Scanner(){
    }

    public void run(String path) {
        Tokenizer tokenizer = new Tokenizer.Builder()
                .withKeyWords(KEY_WORDS)
                .withUnaryOperations(UNARY_OPERATIONS)
                .withBinaryOperations(BINARY_OPERATIONS)
                .withSeparators(SEPARATORS)
                .withEndTokens(END_TOKEN)
                .withEmptySymbols(EMPTY_SYMBOLS)
                .build();

        Result result  = tokenizer.tokenize(readFromFile(path));
        print(result);
    }

    private String readFromFile(String path){
        if (path == null || path.length() == 0)
            throw new IllegalArgumentException("Path can't be null or empty");
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void print(Result result){
        ArrayList<String> ids = result.getIds();
        LinkedList<Token> tokens = result.getTokens();
        System.out.println("Tokens.");
        System.out.println("<type, value> (line, row)");
        System.out.println("-------------------------");
        for(Token token: tokens){
            System.out.println(String.format("<%s, %s> (%d, %d)", token.getType(), token.getValue(), token.getPosition().getLine(), token.getPosition().getRow()));
        }

        printIdsTable(ids);
    }

    private void printIdsTable(ArrayList<String> ids){
        System.out.println("\nIDs table.");
        System.out.println("---------");
        for (int i =0; i<ids.size(); i++){
            System.out.println(i + " " + ids.get(i));
        }
    }
}
