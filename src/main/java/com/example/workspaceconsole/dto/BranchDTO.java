package com.example.workspaceconsole.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BranchDTO {
    private long id;
    private String branchName;
    private String address;
    private List<WorkspaceDTO> workspaces;
}
