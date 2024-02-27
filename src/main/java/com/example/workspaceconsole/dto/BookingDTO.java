package com.example.workspaceconsole.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingDTO {
    private long id;
    @Positive(message = "Workspace ID must be positive")
    private long workspaceId;

    @NotNull(message = "Start time is required")
    private LocalDateTime startTime;

    @Positive(message = "User ID must be positive")
    private long userId;

    private String additionalRequirements;
}
