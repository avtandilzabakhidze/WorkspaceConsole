package com.example.workspaceconsole.domain;

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
    private String workspaceName;
    private boolean isOccupied;
    private double price;
    @Enumerated(value = EnumType.STRING)
    private TimeManagement timeManagement;
    private String description;
    @ManyToOne
    private Branch branch;
}
