package lk.rcu.rcg2000.memberdirectory.skill;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class SkillRequest {

    @ApiModelProperty(required = true)
    @NotNull(message = "Skill name cannot be null.")
    @Size(min = 1, message = "Skill name cannot be empty.")
    private String name;

    @ApiModelProperty(required = true)
    @NotNull(message = "Skill category cannot be null.")
    @Size(min = 1, message = "Skill category cannot be empty.")
    private String category;

}
