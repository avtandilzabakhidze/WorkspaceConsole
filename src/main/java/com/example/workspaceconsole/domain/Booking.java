package com.example.workspaceconsole.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(generator = "booking_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "booking_id_gen", sequenceName = "booking_id_seq", allocationSize = 1)
    private long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Workspace workspace;
    @Column(nullable = false)
    private LocalDateTime startTime;
    @Column(nullable = false)
    private LocalDateTime endTime;
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
    private String additionalRequirements;
}
