package lk.rcu.rcg2000.memberdirectory.company;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    Company requestToCompany(CompanyRequest request);

    CompanyResponse companyToResponse(Company company);
}
