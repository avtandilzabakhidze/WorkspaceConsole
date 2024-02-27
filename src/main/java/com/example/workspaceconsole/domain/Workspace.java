package com.example.workspaceconsole.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Workspace {
    @Id
    @GeneratedValue(generator = "workspace_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "workspace_id_gen", sequenceName = "workspace_id_seq", allocationSize = 1)
    private long id;

    @Column(nullable = false)
    private String workspaceName;

    @Transient
    private boolean isOccupied = false;

    @Column(nullable = false)
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TimeManagement timeManagement;
    private String description;

    @Column(nullable = false)
    private int capacity;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Branch branch;
}
