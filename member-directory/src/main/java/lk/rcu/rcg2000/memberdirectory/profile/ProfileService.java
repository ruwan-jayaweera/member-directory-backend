package lk.rcu.rcg2000.memberdirectory.profile;

import com.querydsl.core.types.Predicate;
import lk.rcu.rcg2000.memberdirectory.exceptions.NotFoundException;
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
public class ProfileService {
    @Autowired
    private ProfileRepository ProfileRepository;

    @Autowired
    private PasswordGenerator passwordGenerator;

    private Logger logger = LoggerFactory.getLogger(ProfileService.class);

    public Profile create(final Profile profile) {
        profile.setPassword(passwordGenerator.nextString());
        return ProfileRepository.save(profile);
    }

    public Profile update(final String id, final Profile updated) {
        final Profile existing = findOne(id);

        return ProfileRepository.save(existing);
    }

    public Profile findOne(final String id) {
        final Profile Profile = ProfileRepository.findOne(id);

        if (Objects.isNull(Profile)) {
            throw new NotFoundException(id);
        } else {
            return Profile;
        }
    }

    public List<Profile> findAll() {
        return ProfileRepository.findAll();
    }

    public void delete(final String id) {
        try {
            ProfileRepository.delete(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException(id);
        }
    }

    public Page<Profile> findAll(final Predicate predicate, final Pageable pageable) {
        return ProfileRepository.findAll(predicate, pageable);
    }

    public void checkExists(final String id) {
        if (!ProfileRepository.exists(id)) {
            throw new NotFoundException(id);
        }
    }
}
