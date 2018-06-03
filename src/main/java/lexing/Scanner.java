package lexing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Scanner {

    private final HashSet<String> KEY_WORDS =  Stream.of("Var", "End", "Begin", "Boolean", "Decimal", "IF", "ELSE")
            .collect(Collectors.toCollection(HashSet::new));
    private final HashSet<Character> UNARY_OPERATIONS =  Stream.of('!')
            .collect(Collectors.toCollection(HashSet::new));
    private final HashSet<String> BINARY_OPERATIONS =  Stream.of("&" , "," , "^" , "-" , "+" , "*" , "/" , ">" , "<" , "==")
            .collect(Collectors.toCollection(HashSet::new));
    private final HashSet<Character> SEPARATORS =  Stream.of(',', ':', ';')
            .collect(Collectors.toCollection(HashSet::new));
    private final HashSet<Character> END_TOKEN =  Stream.of('.')
            .collect(Collectors.toCollection(HashSet::new));
    private final HashSet<Character> EMPTY_SYMBOLS = Stream.of(' ', '\n', '\r')
            .collect(Collectors.toCollection(HashSet::new));

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
        System.out.println(Arrays.toString(tokens.toArray()));
    }
}
