package lk.rcu.rcg2000.memberdirectory.company;

import lombok.Data;

@Data
public class CompanyResponse {

    private String id;
    private String name;
    private String address;
    private String phoneNumber;
    private String city;
    private String country;
    private String industry;
}
