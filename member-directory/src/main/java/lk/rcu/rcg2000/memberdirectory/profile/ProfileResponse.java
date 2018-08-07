package lk.rcu.rcg2000.memberdirectory.profile;

import lk.rcu.rcg2000.memberdirectory.company.Company;
import lk.rcu.rcg2000.memberdirectory.company.CompanyResponse;
import lk.rcu.rcg2000.memberdirectory.skill.Skill;
import lk.rcu.rcg2000.memberdirectory.skill.SkillResponse;
import lombok.Data;

import java.util.Set;

@Data
public class ProfileResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String profession;
    private String city;
    private String country;
    private String nickName;
    private String userName;
    private CompanyResponse company;
    private Set<SkillResponse> skills;

}
