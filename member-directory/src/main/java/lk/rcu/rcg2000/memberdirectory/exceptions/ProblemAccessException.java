package lk.rcu.rcg2000.memberdirectory.exceptions;

public class ProblemAccessException extends RuntimeException {
    public ProblemAccessException(final String userId) {
        super("Access to this problem for user ID " + userId + " is not allowed.");
    }
}
