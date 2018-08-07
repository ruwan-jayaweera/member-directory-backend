package lk.rcu.rcg2000.memberdirectory.skill;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lk.rcu.rcg2000.memberdirectory.exceptions.ApiError;

@Api(tags = "Skills")
public interface SkillApi {

    @ApiOperation(value = "Get a Skill",
            notes = "Retrieve a Skill, identified by a given Skill ID.",
            response = SkillResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Successfully retrieved Skill"),
            @ApiResponse(code = 404, message = "Resource Not Found - Skill for given ID not found", response = ApiError.class)
    })
    SkillResponse getSkill(String id);

    @ApiOperation(value = "Create a Skill",
            notes = "Create a Skill.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created Skill"),
            @ApiResponse(code = 400, message = "Bad Request - Possibly invalid json format", response = ApiError.class)
    })
    Skill createSkill(@ApiParam(value = "company", required = true) SkillRequest skillRequest);

    @ApiOperation(value = "Update a Skill",
            notes = "Update an existing Skill, identified by a given Skill ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content - Successfully updated Skill"),
            @ApiResponse(code = 400, message = "Bad Request - Possibly invalid json format", response = ApiError.class),
            @ApiResponse(code = 404, message = "Resource Not Found - Skill for given ID not found", response = ApiError.class),
            @ApiResponse(code = 409, message = "Conflict - Invalid or duplicate resource", response = ApiError.class)
    })
    void updateSkill(String id, @ApiParam(value = "skill", required = true) SkillRequest skillRequest);

    @ApiOperation(value = "Delete a Skill",
            notes = "Delete an existing Skill, identified by a given Skill ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content - Successfully deleted Skill"),
            @ApiResponse(code = 404, message = "Resource Not Found - Skill for given ID not found", response = ApiError.class)
    })
    void deleteSkill(String companyId);

}
