package lk.rcu.rcg2000.memberdirectory.profile;

import com.querydsl.core.types.Predicate;

import java.util.Objects;

public class ProfilePredicateBuilder {

    private Predicate predicate;

    public static ProfilePredicateBuilder profilePredicate() {
        return new ProfilePredicateBuilder();
    }

    public ProfilePredicateBuilder predicate(Predicate predicate) {
        if (Objects.nonNull(predicate)) {
            this.predicate = predicate;
        }
        return this;
    }

    public ProfilePredicateBuilder id(String id) {
        predicate = QProfile.profile.id.eq(id).and(predicate);
        return this;
    }

    public ProfilePredicateBuilder companyId(String companyId) {
        predicate = QProfile.profile.company.id.eq(companyId).and(predicate);
        return this;
    }

    public Predicate build() {
        return predicate;
    }

}
