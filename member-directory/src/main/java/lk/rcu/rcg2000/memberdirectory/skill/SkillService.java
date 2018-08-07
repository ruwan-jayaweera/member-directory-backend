package lk.rcu.rcg2000.memberdirectory.skill;

import com.querydsl.core.types.Predicate;
import lk.rcu.rcg2000.memberdirectory.exceptions.NotFoundException;
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
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    private Logger logger = LoggerFactory.getLogger(SkillService.class);

    public Skill create(final Skill company) {
        return skillRepository.save(company);
    }

    public Skill update(final String id, final Skill updated) {
        final Skill existing = findOne(id);

        return skillRepository.save(existing);
    }

    public Skill findOne(final String id) {
        final Skill company = skillRepository.findOne(id);

        if (Objects.isNull(company)) {
            throw new NotFoundException(id);
        } else {
            return company;
        }
    }

    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    public void delete(final String id) {
        try {
            skillRepository.delete(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException(id);
        }
    }

    public Page<Skill> findAll(final Predicate predicate, final Pageable pageable) {
        return skillRepository.findAll(predicate, pageable);
    }

    public void checkExists(final String id) {
        if (!skillRepository.exists(id)) {
            throw new NotFoundException(id);
        }
    }
}
