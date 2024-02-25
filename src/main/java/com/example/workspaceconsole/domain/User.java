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
public class User {
    @Id
    @GeneratedValue(generator = "user_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_id_gen", sequenceName = "user_id_seq", allocationSize = 1)
    private long id;
    private String personalNo;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Workspace> workspaces;
}
