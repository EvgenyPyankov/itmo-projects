package lexing;

import lexing.enity.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Scanner {

    private final HashSet<String> KEY_WORDS =  Stream.of("Var", "End", "Begin", "Boolean", "Decimal", "If", "Else")
            .collect(Collectors.toCollection(HashSet::new));
    private final HashSet<Character> UNARY_OPERATIONS =  Stream.of('!')
            .collect(Collectors.toCollection(HashSet::new));
    private final HashSet<String> BINARY_OPERATIONS =  Stream.of("&" , "," , "^" , "-" , "+" , "*" , "/" , ">" , "<" , "==")
            .collect(Collectors.toCollection(HashSet::new));
    private final HashSet<Character> SEPARATORS =  Stream.of(',', ':', ';', '(', ')')
            .collect(Collectors.toCollection(HashSet::new));
    private final HashSet<Character> END_TOKEN =  Stream.of('.')
            .collect(Collectors.toCollection(HashSet::new));
    private final HashSet<Character> EMPTY_SYMBOLS = Stream.of(' ', '\t', '\n', '\r')
            .collect(Collectors.toCollection(HashSet::new));
//    private final Map<String, Integer> CODES = new HashMap<String, Integer>(){
//        {
//            put("Var")
//        }
//    }
//    Map<String,String> test = new HashMap<String, String>()
//    {
//        {
//            put(key1, value1);
//            put(key2, value2);
//        }
//    };

    public Scanner(){
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


    public void run(String path) {
        Tokenizer tokenizer = new Tokenizer.Builder()
                .withKeyWords(KEY_WORDS)
                .withUnaryOperations(UNARY_OPERATIONS)
                .withBinaryOperations(BINARY_OPERATIONS)
                .withSeparators(SEPARATORS)
                .withEndTokens(END_TOKEN)
                .withEmptySymbols(EMPTY_SYMBOLS)
                .build();

        List<Token> tokens = tokenizer.tokenize(readFromFile(path));

//
//        System.out.println(Arrays.toString(tokens.toArray()));
        print(tokens);
    }

    private void print(List<Token>tokens){
        System.out.println("<type, value> (line, row)");
        System.out.println("-------------------------");
        for(Token token: tokens){
            System.out.println(String.format("<%s, %s> (%d, %d)", token.getType(), token.getValue(), token.getPosition().getLine(), token.getPosition().getRow()));
        }

    }
}
