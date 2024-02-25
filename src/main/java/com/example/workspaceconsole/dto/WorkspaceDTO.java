package com.example.workspaceconsole.dto;

import com.example.workspaceconsole.domain.TimeManagement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WorkspaceDTO {
    private long id;
    private String workspaceName;
    private boolean isOccupied;
    private double price;
    private TimeManagement timeManagement;
    private String description;
    private int capacity;
    private BranchDTO branch;
}
