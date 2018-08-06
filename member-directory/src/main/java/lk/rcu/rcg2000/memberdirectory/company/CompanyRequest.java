package lk.rcu.rcg2000.memberdirectory.company;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class CompanyRequest {

    @ApiModelProperty(required = true)
    @NotNull(message = "Company name cannot be null.")
    @Size(min = 1, message = "Company name cannot be empty.")
    private String name;

    @ApiModelProperty(required = true)
    @NotNull(message = "Company address cannot be null.")
    @Size(min = 1, message = "Company address cannot be empty.")
    private String address;

    @ApiModelProperty(required = true)
    private String phoneNumber;

    @ApiModelProperty(required = false)
    private String city;

    @ApiModelProperty(required = false)
    private String country;

    @ApiModelProperty(required = false)
    private String industry;

}
