package co.com.poli.taller1.taller1.services;

import co.com.poli.taller1.taller1.persistence.entity.Backlog;
import co.com.poli.taller1.taller1.persistence.entity.Project;
import co.com.poli.taller1.taller1.services.dto.BacklogInDTO;

public interface BacklogService {

    Backlog save(BacklogInDTO backlogInDTO);

    Backlog findByProjectIdentifier(String projectIdentifier);
}
