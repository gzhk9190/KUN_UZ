package kun.uz.exp;

public class PasswordOrEmailWrongException extends RuntimeException{
    public PasswordOrEmailWrongException(String message) {
        super(message);
    }
}
