package lk.rcu.rcg2000.memberdirectory.skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, String>, QueryDslPredicateExecutor<Skill> {

}
