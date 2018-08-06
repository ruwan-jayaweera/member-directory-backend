package lk.rcu.rcg2000.memberdirectory.company;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lk.rcu.rcg2000.memberdirectory.exceptions.ApiError;

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

}
