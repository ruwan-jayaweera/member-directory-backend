package lk.rcu.rcg2000.memberdirectory.profile;

import lombok.Data;

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

}
