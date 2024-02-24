package com.example.workspaceconsole.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String branchName;
    private String address;
    @OneToMany(mappedBy = "branch")
    @JsonIgnore
    private List<Workspace> workspaces;
}
