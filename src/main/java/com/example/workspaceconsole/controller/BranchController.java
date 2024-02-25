package com.example.workspaceconsole.controller;

import com.example.workspaceconsole.dto.BranchDTO;
import com.example.workspaceconsole.service.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("branch")
public class BranchController {
    private final BranchService service;

    @Autowired
    public BranchController(BranchService service) {
        this.service = service;
    }

    @Operation(summary = "Get all branches")
    @GetMapping
    public ResponseEntity<Set<BranchDTO>> getAllBranches() {
        Set<BranchDTO> branches = service.getAllBranches();
        return new ResponseEntity<>(branches, HttpStatus.OK);
    }

    @Operation(summary = "Create a new branch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Branch created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PostMapping
    public ResponseEntity<BranchDTO> saveBranch(@RequestBody BranchDTO branchDTO) {
        BranchDTO savedBranch = service.saveBranch(branchDTO);
        return new ResponseEntity<>(savedBranch, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a branch by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Branch updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Branch not found"),
    })
    @PutMapping("{id}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable long id, @RequestBody BranchDTO branchDTO) {
        BranchDTO updatedBranch = service.updateBranch(id, branchDTO);
        return new ResponseEntity<>(updatedBranch, HttpStatus.OK);
    }
}
