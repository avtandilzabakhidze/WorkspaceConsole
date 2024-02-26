package com.example.workspaceconsole.controller;

import com.example.workspaceconsole.dto.WorkspaceDTO;
import com.example.workspaceconsole.service.WorkspaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("workspace")
@Validated
public class WorkspaceController {
    private final WorkspaceService service;

    @Autowired
    public WorkspaceController(WorkspaceService service) {
        this.service = service;
    }

    @Operation(summary = "Get all workspaces")
    @GetMapping
    public ResponseEntity<Set<WorkspaceDTO>> getAllWorkspaces() {
        Set<WorkspaceDTO> workspaces = service.getAllWorkspaces();
        return new ResponseEntity<>(workspaces, HttpStatus.OK);
    }

    @Operation(summary = "Create a new workspace")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workspace created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PostMapping
    public ResponseEntity<WorkspaceDTO> saveWorkspace(@Valid @RequestBody WorkspaceDTO workspaceDTO) {
        WorkspaceDTO savedWorkspace = service.saveWorkspace(workspaceDTO);
        return new ResponseEntity<>(savedWorkspace, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a workspace by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workspace updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Workspace not found"),
    })
    @PutMapping("{id}")
    public ResponseEntity<WorkspaceDTO> updateWorkspace(@PathVariable long id,@Valid  @RequestBody WorkspaceDTO workspaceDTO) {
        WorkspaceDTO updatedWorkspace = service.updateWorkspace(id, workspaceDTO);
        return new ResponseEntity<>(updatedWorkspace, HttpStatus.OK);
    }
}
