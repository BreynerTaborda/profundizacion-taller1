package co.com.poli.taller1.taller1.services;

import co.com.poli.taller1.taller1.mapper.BacklogDTOtoBacklog;
import co.com.poli.taller1.taller1.persistence.entity.Backlog;
import co.com.poli.taller1.taller1.persistence.entity.Project;
import co.com.poli.taller1.taller1.persistence.repository.BacklogRepository;
import co.com.poli.taller1.taller1.services.dto.BacklogInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BacklogServiceImpl implements BacklogService{

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectService projectService;

    @Override
    public Backlog save(BacklogInDTO backlogInDTO) {
        Project project = this.projectService.findByProjectIdentifier(backlogInDTO.getProjectIdentifier());

        if(project == null){
            return null;
        }

        Backlog backlog = this.findByProjectIdentifier(backlogInDTO.getProjectIdentifier());

        if(backlog != null){
            backlog.setId(-1);

            return backlog;
        }

        backlog = new BacklogDTOtoBacklog().mapper(backlogInDTO);

        return this.backlogRepository.save(backlog);
    }

    @Override
    public Backlog findByProjectIdentifier(String projectIdentifier) {
        return this.backlogRepository.findByProjectIdentifier(projectIdentifier);
    }
}
