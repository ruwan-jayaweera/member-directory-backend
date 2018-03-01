package lk.rcu.rcg2000.memberdirectory.exceptions;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException {
    private final String id;

    public NotFoundException(final String id) {
        this.id = id;
    }

    public NotFoundException(final String id, Exception e) {
        super(e);
        this.id = id;
    }
}
