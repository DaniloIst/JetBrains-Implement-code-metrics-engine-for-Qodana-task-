public class Token {
    final TokenType type;
    final String character;
    final Object literal;
    final int line;

    public Token(TokenType type, String character, Object literal, int line) {
        this.type = type;
        this.character = character;
        this.literal = literal;
        this.line = line;
    }

    @Override
    public String toString() {
        return type + " " + character + " " + literal + " " + line;
    }
}
