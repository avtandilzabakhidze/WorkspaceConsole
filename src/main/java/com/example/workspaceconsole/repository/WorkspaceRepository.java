package com.example.workspaceconsole.repository;

import com.example.workspaceconsole.domain.Workspace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository extends CrudRepository<Workspace, Long> {
}
