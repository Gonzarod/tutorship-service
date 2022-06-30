package com.evertix.tutorshipservice.controller;

import com.evertix.tutorshipservice.entities.SessionDetail;
import com.evertix.tutorshipservice.service.SessionDetailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "SessionDetail", description = "API")
@RestController
@RequestMapping("/api/sessionsDetails")
@CrossOrigin(origins = "*")
public class SessionDetailController {

    @Autowired
    private SessionDetailService sessionDetailService;

    /*
    @GetMapping("/")
    @Operation(summary = "Get All Session Details", description = "Get All Sessions Details", tags = {"SessionDetail"})
    public List<SessionDetail> getAllSessions(){
        return sessionDetailService.getAllSessionDetails();
    }

    @GetMapping("/page")
    @Operation(summary = "Get All Session Details Page", description = "Get All Sessions Details Page", tags = {"SessionDetail"},
            parameters = {
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Page you want to retrieve (0..N)"
                            , name = "page"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Number of records per page."
                            , name = "size"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "20"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Sorting criteria in the format: property(,asc|desc). "
                            + "Default sort order is ascending. " + "Multiple sort criteria are supported."
                            , name = "sort"
                            , content = @Content(array = @ArraySchema(schema = @Schema(type = "string"))))
            })
    public Page<SessionDetail> getAllSessionsPage(@PageableDefault @Parameter(hidden = true) Pageable pageable){
        return sessionDetailService.getAllSessionDetailsPage(pageable);
    }

     */

    @GetMapping("/sessions/{sessionId}/")
    @Operation(summary = "Get All Sessions Details By Session Id", description = "Get All Sessions Details By Session Id", tags = {"SessionDetail"},
            parameters = {
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Page you want to retrieve (0..N)"
                            , name = "page"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Number of records per page."
                            , name = "size"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "20"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Sorting criteria in the format: property(,asc|desc). "
                            + "Default sort order is ascending. " + "Multiple sort criteria are supported."
                            , name = "sort"
                            , content = @Content(array = @ArraySchema(schema = @Schema(type = "string"))))
            })
    public List<SessionDetail> getAllSessionsDetailsBySessionId(@PathVariable Long sessionId){
        return sessionDetailService.getAllSessionDetailsBySessionId(sessionId);
    }
    /*
    @GetMapping("/sessions/{sessionId}/page")
    @Operation(summary = "Get All Sessions Details By Session Id Page", description = "Get All Sessions Details By Session Id Page", tags = {"SessionDetail"},
            parameters = {
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Page you want to retrieve (0..N)"
                            , name = "page"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Number of records per page."
                            , name = "size"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "20"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Sorting criteria in the format: property(,asc|desc). "
                            + "Default sort order is ascending. " + "Multiple sort criteria are supported."
                            , name = "sort"
                            , content = @Content(array = @ArraySchema(schema = @Schema(type = "string"))))
            })
    public Page<SessionDetail> getAllSessionsDetailsBySessionIdPage(@PathVariable Long sessionId,@PageableDefault @Parameter(hidden = true) Pageable pageable){
        return sessionDetailService.getAllSessionDetailsBySessionIdPage(sessionId,pageable);
    }


    @GetMapping("/sessions/{sessionId}/sessionDetails")
    @Operation(summary = "Get All SessionDetails By Session", description = "Get All SessionDetails By Session", tags = {"SessionDetail"},
            parameters = {
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Page you want to retrieve (0..N)"
                            , name = "page"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Number of records per page."
                            , name = "size"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "20"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Sorting criteria in the format: property(,asc|desc). "
                            + "Default sort order is ascending. " + "Multiple sort criteria are supported."
                            , name = "sort"
                            , content = @Content(array = @ArraySchema(schema = @Schema(type = "string"))))
            })
    public Page<SessionDetailResource> getAllSessionDetailsBySessionId(@PathVariable(name = "sessionId") Long sessionId, @PageableDefault @Parameter(hidden = true) Pageable pageable){
        Page<SessionDetail> sessionDetailPage = sessionDetailService.getAllSessionDetailsBySessionId(sessionId, pageable);
        List<SessionDetailResource> resources = sessionDetailPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,sessionDetailPage.getTotalElements());
    }

    @PostMapping("/sessions/{sessionId}/teachers/{teacherId}/sessionDetails")
    @Operation(summary = "Post SessionDetail", description = "Create SessionDetail", tags = {"SessionDetail"})
    public SessionDetailResource createSessionDetail(@PathVariable(name = "sessionId") Long sessionId,
                                                     @PathVariable(name = "teacherId") Long teacherId,
                                                     @Valid @RequestBody SessionDetailSaveResource resource){
        return convertToResource(sessionDetailService.createSessionDetail(sessionId,teacherId,convertToEntity(resource)));
    }

    @PutMapping("/sessions/{sessionId}/teachers/{teacherId}/sessionDetails/{sessionDetailId}")
    @Operation(summary = "Put SessionDetail", description = "Update SessionDetail", tags = {"SessionDetail"})
    public SessionDetailResource updateSessionDetail(@PathVariable(name = "sessionId") Long sessionId,
                                                     @PathVariable(name = "teacherId") Long teacherId,
                                                     @PathVariable(name = "sessionDetailId") Long sessionDetailId,
                                                     @Valid @RequestBody SessionDetailSaveResource resource){
        return convertToResource(sessionDetailService.updateSessionDetail(sessionId,teacherId,sessionDetailId,convertToEntity(resource)));
    }

    @DeleteMapping("/sessions/{sessionId}/teachers/{teacherId}/sessionDetails/{sessionDetailId}")
    @Operation(summary = "Delete SessionDetail", description = "Delete SessionDetail", tags = {"SessionDetail"})
    ResponseEntity<?> deleteSessionDetail(@PathVariable(name = "sessionId") Long sessionId,
                                          @PathVariable(name = "teacherId") Long teacherId,
                                          @PathVariable(name = "sessionDetailId") Long sessionDetailId){
        return sessionDetailService.deleteSessionDetail(sessionId, teacherId, sessionDetailId);
    }

     */

}
