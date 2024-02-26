package com.example.workspaceconsole.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BranchDTO {
    private long id;

    @NotBlank(message = "Branch name is required")
    @Size(max = 100, message = "Branch name must be less than or equal to 100 characters")
    private String branchName;

    @NotBlank(message = "Address is required")
    @Size(max = 200, message = "Address must be less than or equal to 200 characters")
    private String address;

    private List<WorkspaceDTO> workspaces;
}
