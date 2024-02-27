package com.example.workspaceconsole.dto;

import com.example.workspaceconsole.domain.TimeManagement;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkspaceDTO {
    private long id;
    @NotBlank(message = "Workspace name is required")
    @Size(max = 100, message = "Workspace name must be less than or equal to 100 characters")
    private String workspaceName;

    @PositiveOrZero(message = "Price must be a positive number or zero")
    private double price;

    @NotNull(message = "Time management type is required")
    private TimeManagement timeManagement;

    @Size(max = 200, message = "Description must be less than or equal to 200 characters")
    private String description;

    @Positive(message = "Capacity must be a positive number")
    private int capacity;

    @Positive(message = "Branch ID must be a positive number")
    private long branchId;
}
