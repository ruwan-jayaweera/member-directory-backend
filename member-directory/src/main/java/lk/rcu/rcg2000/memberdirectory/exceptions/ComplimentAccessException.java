package lk.rcu.rcg2000.memberdirectory.exceptions;

public class ComplimentAccessException extends RuntimeException {
    public ComplimentAccessException(final String userId) {
        super("Access to this compliment for user ID " + userId + " is not allowed.");
    }
}
