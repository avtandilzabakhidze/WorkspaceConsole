package com.example.workspaceconsole.dto;
import com.example.workspaceconsole.domain.Workspace;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private long id;
    private String personalNo;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private List<WorkspaceDTO> workspaces;
}
