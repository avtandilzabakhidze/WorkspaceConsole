package com.example.workspaceconsole.serviceTest;

import com.example.workspaceconsole.domain.Branch;
import com.example.workspaceconsole.dto.BranchDTO;
import com.example.workspaceconsole.repository.BranchRepository;
import com.example.workspaceconsole.service.BranchService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BranchServiceTest {

    @Mock
    private BranchRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BranchService branchService;

    private BranchDTO branchDTO;
    private Branch branch;

    @BeforeEach
    public void setUp() {
        branchDTO = new BranchDTO();
        branchDTO.setId(1);
        branchDTO.setBranchName("Branch 1");
        branchDTO.setAddress("123 Main Street");

        branch = new Branch();
        branch.setId(1);
        branch.setBranchName("Branch 1");
        branch.setAddress("123 Main Street");
    }

    @Test
    public void testGetAllBranches() {
        when(repository.findAll()).thenReturn(Collections.singletonList(branch));
        when(modelMapper.map(branch, BranchDTO.class)).thenReturn(branchDTO);

        Set<BranchDTO> result = branchService.getAllBranches();

        assertEquals(1, result.size());
        assertTrue(result.contains(branchDTO));
    }

    @Test
    public void testSaveBranch() {
        when(modelMapper.map(branchDTO, Branch.class)).thenReturn(branch);
        when(repository.save(branch)).thenReturn(branch);
        when(modelMapper.map(branch, BranchDTO.class)).thenReturn(branchDTO);

        BranchDTO result = branchService.saveBranch(branchDTO);

        assertEquals(branchDTO, result);
    }

    @Test
    public void testUpdateBranch() {
        long id = 1;
        when(repository.existsById(id)).thenReturn(true);
        when(modelMapper.map(branchDTO, Branch.class)).thenReturn(branch);
        when(repository.save(branch)).thenReturn(branch);
        when(modelMapper.map(branch, BranchDTO.class)).thenReturn(branchDTO);

        BranchDTO result = branchService.updateBranch(id, branchDTO);

        assertEquals(branchDTO, result);
    }

    @Test
    public void testUpdateBranchNotFound() {
        long id = 1;
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> branchService.updateBranch(id, branchDTO));
    }
}
