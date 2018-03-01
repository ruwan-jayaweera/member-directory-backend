package lk.rcu.rcg2000.memberdirectory.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String>, QueryDslPredicateExecutor<Profile> {

}
