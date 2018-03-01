package lk.rcu.rcg2000.memberdirectory.exceptions;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class NotFoundExceptionMatcher extends TypeSafeMatcher<NotFoundException> {

    private String foundId;
    private final String expectedId;

    public static NotFoundExceptionMatcher hasId(String item) {
        return new NotFoundExceptionMatcher(item);
    }

    private NotFoundExceptionMatcher(final String expectedId) {
        this.expectedId = expectedId;
    }

    @Override
    protected boolean matchesSafely(final NotFoundException exception) {
        foundId = exception.getId();
        return foundId.equals(expectedId);
    }

    @Override
    public void describeTo(final Description description) {
        description.appendValue(foundId)
                .appendText(" was not found instead of ")
                .appendValue(expectedId);
    }
}