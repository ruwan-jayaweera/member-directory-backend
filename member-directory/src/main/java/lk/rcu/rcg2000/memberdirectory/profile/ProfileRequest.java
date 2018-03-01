package lk.rcu.rcg2000.memberdirectory.profile;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class ProfileRequest {

    @ApiModelProperty(required = true)
    @NotNull(message = "Profile name cannot be null.")
    @Size(min = 1, message = "Profile name cannot be empty.")
    private String name;
}
