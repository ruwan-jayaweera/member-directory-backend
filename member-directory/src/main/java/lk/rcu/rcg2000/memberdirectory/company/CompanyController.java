package lk.rcu.rcg2000.memberdirectory.company;

import lk.rcu.rcg2000.memberdirectory.profile.Profile;
import lk.rcu.rcg2000.memberdirectory.profile.ProfileApi;
import lk.rcu.rcg2000.memberdirectory.profile.ProfileMapper;
import lk.rcu.rcg2000.memberdirectory.profile.ProfileRequest;
import lk.rcu.rcg2000.memberdirectory.profile.ProfileResponse;
import lk.rcu.rcg2000.memberdirectory.profile.ProfileService;
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
@RequestMapping(value = "/companies")
public class CompanyController implements CompanyApi {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyMapper companyMapper;

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
}
