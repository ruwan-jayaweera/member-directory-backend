package lk.rcu.rcg2000.memberdirectory.profile;

import com.querydsl.core.types.Predicate;
import lk.rcu.rcg2000.memberdirectory.company.Company;
import lk.rcu.rcg2000.memberdirectory.company.CompanyService;
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
    private ProfileRepository profileRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private PasswordGenerator passwordGenerator;

    private Logger logger = LoggerFactory.getLogger(ProfileService.class);

    public Profile create(final Profile profile) {
        profile.setPassword(passwordGenerator.nextString());
        return profileRepository.save(profile);
    }

    public Profile update(final String id, final Profile updated) {
        final Profile existing = findOne(id);

        return profileRepository.save(existing);
    }

    public Profile findOne(final String id) {
        final Profile profile = profileRepository.findOne(id);

        if (Objects.isNull(profile)) {
            throw new NotFoundException(id);
        } else {
            return profile;
        }
    }

    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    public void delete(final String id) {
        try {
            profileRepository.delete(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException(id);
        }
    }

    public Page<Profile> findAll(final Predicate predicate, final Pageable pageable) {
        return profileRepository.findAll(predicate, pageable);
    }

    public void checkExists(final String id) {
        if (!profileRepository.exists(id)) {
            throw new NotFoundException(id);
        }
    }

    public void attachCompany(final String profileId, final String companyId) {
        final Profile existingP = findOne(profileId);
        final Company existingC = companyService.findOne(companyId);
        existingP.setCompany(existingC);
        profileRepository.save(existingP);
    }
}
