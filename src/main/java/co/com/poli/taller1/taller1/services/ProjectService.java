package co.com.poli.taller1.taller1.services;

import co.com.poli.taller1.taller1.persistence.entity.Project;
import co.com.poli.taller1.taller1.services.dto.ProjectInDTO;

import java.util.List;

public interface ProjectService {

    List<Project> findAll();

    Project save(ProjectInDTO project);
}
