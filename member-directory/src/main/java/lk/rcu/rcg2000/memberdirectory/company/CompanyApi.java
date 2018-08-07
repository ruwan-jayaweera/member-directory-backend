package lk.rcu.rcg2000.memberdirectory.company;

import com.querydsl.core.types.Predicate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lk.rcu.rcg2000.memberdirectory.exceptions.ApiError;
import lk.rcu.rcg2000.memberdirectory.profile.ProfileResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "Companies")
public interface CompanyApi {

    @ApiOperation(value = "Get a Company",
            notes = "Retrieve a Company, identified by a given Company ID.",
            response = CompanyResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Successfully retrieved Company"),
            @ApiResponse(code = 404, message = "Resource Not Found - Company for given ID not found", response = ApiError.class)
    })
    CompanyResponse getCompany(String id);

    @ApiOperation(value = "Create a Company",
            notes = "Create a Company.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created Company"),
            @ApiResponse(code = 400, message = "Bad Request - Possibly invalid json format", response = ApiError.class)
    })
    Company createCompany(@ApiParam(value = "company", required = true) CompanyRequest companyRequest);

    @ApiOperation(value = "Update a Company",
            notes = "Update an existing Company, identified by a given Company ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content - Successfully updated Company"),
            @ApiResponse(code = 400, message = "Bad Request - Possibly invalid json format", response = ApiError.class),
            @ApiResponse(code = 404, message = "Resource Not Found - Company for given ID not found", response = ApiError.class),
            @ApiResponse(code = 409, message = "Conflict - Invalid or duplicate resource", response = ApiError.class)
    })
    void updateCompany(String id, @ApiParam(value = "company", required = true) CompanyRequest companyRequest);

    @ApiOperation(value = "Delete a Company",
            notes = "Delete an existing Company, identified by a given Company ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content - Successfully deleted Company"),
            @ApiResponse(code = 404, message = "Resource Not Found - Company for given ID not found", response = ApiError.class)
    })
    void deleteCompany(String companyId);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "string", paramType = "query", value = "Results page you want to retrieve (0..N). Default value is 0 (first page)."),
            @ApiImplicitParam(name = "size", dataType = "string", paramType = "query", value = "Number of records per page. Default value is 20."),
    })
    @ApiOperation(value = "Get All Profiles for a Company Paginated",
            notes = "Retrieve a list of all Profile for the site identified by a given Company ID in a paginated manner.",
            response = ProfileResponse.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Successfully retrieved list of Profiles"),
            @ApiResponse(code = 401, message = "Unauthorized - Invalid access token", response = ApiError.class),
            @ApiResponse(code = 403, message = "Forbidden - User Not Allowed", response = ApiError.class)
    })
    List<ProfileResponse> getProfileByCompany(String id, Predicate predicate, Pageable pageable,
                                              HttpServletRequest request, HttpServletResponse response, UriComponentsBuilder builder);

}
