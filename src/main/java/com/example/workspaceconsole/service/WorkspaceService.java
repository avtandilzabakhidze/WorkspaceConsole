package com.example.workspaceconsole.service;

import com.example.workspaceconsole.domain.Workspace;
import com.example.workspaceconsole.dto.WorkspaceDTO;
import com.example.workspaceconsole.repository.WorkspaceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WorkspaceService {
    private final WorkspaceRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public WorkspaceService(WorkspaceRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public Set<WorkspaceDTO> getAllWorkspaces() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(workspace -> modelMapper.map(workspace, WorkspaceDTO.class))
                .collect(Collectors.toSet());
    }

    public WorkspaceDTO saveWorkspace(WorkspaceDTO workspaceDTO) {
        Workspace workspace = repository.save(modelMapper.map(workspaceDTO, Workspace.class));
        return modelMapper.map(workspace, WorkspaceDTO.class);
    }

    public WorkspaceDTO updateWorkspace(long id, WorkspaceDTO workspaceDTO) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Workspace with id " + id + " not found");
        }
        workspaceDTO.setId(id);
        Workspace workspace = repository.save(modelMapper.map(workspaceDTO, Workspace.class));
        return modelMapper.map(workspace, WorkspaceDTO.class);
    }

    public void deleteWorkspaceById(long id){
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Workspace with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}
