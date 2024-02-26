package com.example.workspaceconsole.service;

import com.example.workspaceconsole.domain.Branch;
import com.example.workspaceconsole.dto.BranchDTO;
import com.example.workspaceconsole.repository.BranchRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BranchService {
    private final BranchRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public BranchService(BranchRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public Set<BranchDTO> getAllBranches() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(branch -> modelMapper.map(branch, BranchDTO.class))
                .collect(Collectors.toSet());
    }

    public BranchDTO saveBranch(BranchDTO branchDTO) {
        Branch branch = repository.save(modelMapper.map(branchDTO, Branch.class));
        return modelMapper.map(branch, BranchDTO.class);
    }

    public BranchDTO updateBranch(long id, BranchDTO branchDTO) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Branch with id " + id + " not found");
        }
        branchDTO.setId(id);
        Branch branch = repository.save(modelMapper.map(branchDTO, Branch.class));
        return modelMapper.map(branch, BranchDTO.class);
    }
}
