package lk.rcu.rcg2000.memberdirectory.company;

import com.querydsl.core.types.Predicate;
import lk.rcu.rcg2000.memberdirectory.profile.Profile;
import lk.rcu.rcg2000.memberdirectory.profile.ProfileMapper;
import lk.rcu.rcg2000.memberdirectory.profile.ProfilePredicateBuilder;
import lk.rcu.rcg2000.memberdirectory.profile.ProfileResponse;
import lk.rcu.rcg2000.memberdirectory.profile.ProfileService;
import lk.rcu.rcg2000.memberdirectory.util.ResponseHeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
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
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController implements CompanyApi {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileMapper profileMapper;

    @Override
    @GetMapping("/{id}")
    public CompanyResponse getCompany(@PathVariable final String id) {
        final Company company = companyService.findOne(id);
        return companyMapper.companyToResponse(company);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company createCompany(@RequestBody @Valid final CompanyRequest companyRequest) {
        final Company company = companyMapper.requestToCompany(companyRequest);
        return companyService.create(company);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCompany(@PathVariable final String id,
                              @RequestBody @Valid final CompanyRequest companyRequest) {
        final Company company = companyMapper.requestToCompany(companyRequest);
        companyService.update(id, company);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable final String id) {
        companyService.delete(id);
    }

    @Override
    @GetMapping("/{id}/profile")
    public List<ProfileResponse> getProfileByCompany(@PathVariable final String id,
                                                     @QuerydslPredicate(root = Profile.class) final Predicate predicate,
                                                     final Pageable pageable,
                                                     final HttpServletRequest request,
                                                     final HttpServletResponse response,
                                                     final UriComponentsBuilder uriComponentsBuilder) {
        Predicate searchPredicate = ProfilePredicateBuilder.profilePredicate()
                .predicate(predicate)
                .companyId(id)
                .build();

        final Page<ProfileResponse> result = profileService.findAll(searchPredicate, pageable).map(profileMapper::profileToResponse);

        ResponseHeaderUtil.addPagingHeadersToResponse(request, response, result, uriComponentsBuilder);
        return result.getContent();
    }
}
