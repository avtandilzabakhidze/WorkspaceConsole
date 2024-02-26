package com.example.workspaceconsole.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingDTO {
    private long id;
    private long workspaceId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long userId;
    private String additionalRequirements;
}
