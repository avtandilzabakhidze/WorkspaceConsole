package com.example.workspaceconsole.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Branch {
    @Id
    @GeneratedValue(generator = "branch_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "branch_id_gen", sequenceName = "branch_id_seq", allocationSize = 1)
    private long id;
    @Column(nullable = false)
    private String branchName;
    @Column(nullable = false)
    private String address;

    @JsonManagedReference
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Workspace> workspaces;
}
