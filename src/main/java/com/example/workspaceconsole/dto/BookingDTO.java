package com.example.workspaceconsole.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingDTO {
    private long id;
    private WorkspaceDTO workspace;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private UserDTO user;
    private String additionalRequirements;
}
