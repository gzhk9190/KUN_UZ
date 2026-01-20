package kun.uz.exp;

public class JwtTimedOutException extends RuntimeException {
    public JwtTimedOutException(String message) {
        super(message);
    }
}
