package com.example.workspaceconsole.controller;

import com.example.workspaceconsole.dto.WorkspaceDTO;
import com.example.workspaceconsole.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("workspace")
public class WorkspaceController {
    private final WorkspaceService service;

    @Autowired
    public WorkspaceController(WorkspaceService service) {
        this.service = service;
    }

    @GetMapping
    public Set<WorkspaceDTO> getAllWorkspaces() {
        return service.getAllWorkspaces();
    }

    @PostMapping
    public WorkspaceDTO saveWorkspace(@RequestBody WorkspaceDTO workspaceDTO) {
        return service.saveWorkspace(workspaceDTO);
    }

    @PutMapping("{id}")
    public WorkspaceDTO updateWorkspace(@PathVariable long id, @RequestBody WorkspaceDTO workspaceDTO) {
        return service.updateWorkspace(id, workspaceDTO);
    }
}
