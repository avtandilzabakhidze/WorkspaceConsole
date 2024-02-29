package com.example.workspaceconsole.serviceTest;

import com.example.workspaceconsole.domain.TimeManagement;
import com.example.workspaceconsole.domain.Workspace;
import com.example.workspaceconsole.dto.WorkspaceDTO;
import com.example.workspaceconsole.repository.WorkspaceRepository;
import com.example.workspaceconsole.service.WorkspaceService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WorkspaceServiceTest {

    @Mock
    private WorkspaceRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private WorkspaceService workspaceService;

    private WorkspaceDTO workspaceDTO;
    private Workspace workspace;

    @BeforeEach
    public void setUp() {
        workspaceDTO = new WorkspaceDTO();
        workspaceDTO.setId(1);
        workspaceDTO.setWorkspaceName("Workspace 1");
        workspaceDTO.setPrice(100.0);
        workspaceDTO.setTimeManagement(TimeManagement.HOUR);
        workspaceDTO.setDescription("Description for Workspace 1");
        workspaceDTO.setCapacity(10);
        workspaceDTO.setBranchId(1);

        workspace = new Workspace();
        workspace.setId(1);
        workspace.setWorkspaceName("Workspace 1");
        workspace.setPrice(100.0);
        workspace.setTimeManagement(TimeManagement.HOUR);
        workspace.setDescription("Description for Workspace 1");
        workspace.setCapacity(10);
    }

    @Test
    public void testGetAllWorkspaces() {
        when(repository.findAll()).thenReturn(Collections.singletonList(workspace));
        when(modelMapper.map(workspace, WorkspaceDTO.class)).thenReturn(workspaceDTO);

        Set<WorkspaceDTO> result = workspaceService.getAllWorkspaces();

        assertEquals(1, result.size());
        assertTrue(result.contains(workspaceDTO));
    }

    @Test
    public void testSaveWorkspace() {
        when(modelMapper.map(workspaceDTO, Workspace.class)).thenReturn(workspace);
        when(repository.save(workspace)).thenReturn(workspace);
        when(modelMapper.map(workspace, WorkspaceDTO.class)).thenReturn(workspaceDTO);

        WorkspaceDTO result = workspaceService.saveWorkspace(workspaceDTO);

        assertEquals(workspaceDTO, result);
    }

    @Test
    public void testUpdateWorkspace() {
        long id = 1;
        when(repository.existsById(id)).thenReturn(true);
        when(modelMapper.map(workspaceDTO, Workspace.class)).thenReturn(workspace);
        when(repository.save(workspace)).thenReturn(workspace);
        when(modelMapper.map(workspace, WorkspaceDTO.class)).thenReturn(workspaceDTO);

        WorkspaceDTO result = workspaceService.updateWorkspace(id, workspaceDTO);

        assertEquals(workspaceDTO, result);
    }

    @Test
    public void testUpdateWorkspaceNotFound() {
        long id = 1;
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> workspaceService.updateWorkspace(id, workspaceDTO));
    }
}
