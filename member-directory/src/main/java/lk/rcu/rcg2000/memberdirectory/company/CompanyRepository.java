package lk.rcu.rcg2000.memberdirectory.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String>, QueryDslPredicateExecutor<Company> {

}
