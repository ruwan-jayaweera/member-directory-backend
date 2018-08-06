package lk.rcu.rcg2000.memberdirectory.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/profiles")
public class ProfileController implements ProfileApi {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileMapper profileMapper;

    @Override
    @GetMapping("/{id}")
    public ProfileResponse getProfile(@PathVariable final String id) {
        final Profile Profile = profileService.findOne(id);
        return profileMapper.ProfileToResponse(Profile);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Profile createProfile(@RequestBody @Valid final ProfileRequest profileRequest) {
        final Profile profile = profileMapper.requestToProfile(profileRequest);
        return profileService.create(profile);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProfile(@PathVariable final String id,
                              @RequestBody @Valid final ProfileRequest profileRequest) {
        final Profile Profile = profileMapper.requestToProfile(profileRequest);
        profileService.update(id, Profile);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@PathVariable final String id) {
        profileService.delete(id);
    }

    @Override
    @PutMapping("/{profileId}/company/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void attacheCompany(@PathVariable final String profileId,@PathVariable final String companyId) {
        profileService.attachCompany(profileId, companyId);
    }
}
