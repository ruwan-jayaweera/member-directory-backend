package lk.rcu.rcg2000.memberdirectory.exceptions;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.ui.ModelMap;

@ApiModel
@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    @ApiModelProperty(value = "Message associated with the error.", readOnly = true, required = true)
    private String message;

    private ModelMap errorMap;
}
