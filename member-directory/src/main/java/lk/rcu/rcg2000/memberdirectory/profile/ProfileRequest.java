package lk.rcu.rcg2000.memberdirectory.profile;

import io.swagger.annotations.ApiModelProperty;
import lk.rcu.rcg2000.memberdirectory.company.Company;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class ProfileRequest {

    @ApiModelProperty(required = true)
    @NotNull(message = "Profile first name cannot be null.")
    @Size(min = 1, message = "Profile first name cannot be empty.")
    private String firstName;

    @ApiModelProperty(required = true)
    @NotNull(message = "Profile last name cannot be null.")
    @Size(min = 1, message = "Profile last name cannot be empty.")
    private String lastName;

    @ApiModelProperty(required = false)
    private String email;

    @ApiModelProperty(required = false)
    private String phoneNumber;

    @ApiModelProperty(required = false)
    private String profession;

    @ApiModelProperty(required = false)
    private String city;

    @ApiModelProperty(required = false)
    private String country;

    @ApiModelProperty(required = false)
    private String nickName;

    @ApiModelProperty(required = true)
    @NotNull(message = "Profile user name cannot be null.")
    @Size(min = 5, message = "Profile user name cannot be empty.")
    private String userName;

    @ApiModelProperty(required = false)
    private Company company;
}
