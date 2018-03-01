package lk.rcu.rcg2000.memberdirectory.exceptions;

public class TemplateException extends RuntimeException {
    public TemplateException(final String templateLocation) {
        super("Email template not found at location: " + templateLocation);
    }
}
