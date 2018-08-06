package lk.rcu.rcg2000.memberdirectory.company;

import com.querydsl.core.types.Predicate;
import lk.rcu.rcg2000.memberdirectory.exceptions.NotFoundException;
import lk.rcu.rcg2000.memberdirectory.profile.Profile;
import lk.rcu.rcg2000.memberdirectory.profile.ProfileRepository;
import lk.rcu.rcg2000.memberdirectory.util.PasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordGenerator passwordGenerator;

    private Logger logger = LoggerFactory.getLogger(lk.rcu.rcg2000.memberdirectory.company.CompanyService.class);

    public Company create(final Company company) {
        return companyRepository.save(company);
    }

    public Company update(final String id, final Company updated) {
        final Company existing = findOne(id);

        return companyRepository.save(existing);
    }

    public Company findOne(final String id) {
        final Company company = companyRepository.findOne(id);

        if (Objects.isNull(company)) {
            throw new NotFoundException(id);
        } else {
            return company;
        }
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public void delete(final String id) {
        try {
            companyRepository.delete(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException(id);
        }
    }

    public Page<Company> findAll(final Predicate predicate, final Pageable pageable) {
        return companyRepository.findAll(predicate, pageable);
    }

    public void checkExists(final String id) {
        if (!companyRepository.exists(id)) {
            throw new NotFoundException(id);
        }
    }
}
