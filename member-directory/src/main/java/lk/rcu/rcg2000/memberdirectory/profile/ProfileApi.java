package lk.rcu.rcg2000.memberdirectory.profile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lk.rcu.rcg2000.memberdirectory.exceptions.ApiError;

@Api(tags = "Profiles")
public interface ProfileApi {

    @ApiOperation(value = "Get a Profile",
            notes = "Retrieve a Profile, identified by a given Profile ID.",
            response = ProfileResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Successfully retrieved Profile"),
            @ApiResponse(code = 404, message = "Resource Not Found - Profile for given ID not found", response = ApiError.class)
    })
    ProfileResponse getProfile(String id);

    @ApiOperation(value = "Create a Profile",
            notes = "Create a Profile.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content - Successfully updated Profile"),
            @ApiResponse(code = 400, message = "Bad Request - Possibly invalid json format", response = ApiError.class)
    })
    void createProfile(@ApiParam(value = "profile", required = true) ProfileRequest profileRequest);

    @ApiOperation(value = "Update a Profile",
            notes = "Update an existing Profile, identified by a given Profile ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content - Successfully updated Profile"),
            @ApiResponse(code = 400, message = "Bad Request - Possibly invalid json format", response = ApiError.class),
            @ApiResponse(code = 404, message = "Resource Not Found - Profile for given ID not found", response = ApiError.class),
            @ApiResponse(code = 409, message = "Conflict - Invalid or duplicate resource", response = ApiError.class)
    })
    void updateProfile(String id, @ApiParam(value = "profile", required = true) ProfileRequest profileRequest);

    @ApiOperation(value = "Delete a Profile",
            notes = "Delete an existing Profile, identified by a given Profile ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content - Successfully deleted Profile"),
            @ApiResponse(code = 404, message = "Resource Not Found - Profile for given ID not found", response = ApiError.class)
    })
    void deleteProfile(String profileId);

}
