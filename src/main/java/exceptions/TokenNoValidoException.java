package exceptions;

public class TokenNoValidoException extends RuntimeException {

    public TokenNoValidoException() {
        super("Token NO válido");
    }

    public TokenNoValidoException(Throwable causa) {
        super("Token NO válido", causa);
    }
}
