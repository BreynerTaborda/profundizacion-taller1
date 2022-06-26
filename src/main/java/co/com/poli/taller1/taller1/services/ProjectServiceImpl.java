package co.com.poli.taller1.taller1.services;

import co.com.poli.taller1.taller1.mapper.ProjectDTOtoProject;
import co.com.poli.taller1.taller1.persistence.entity.Project;
import co.com.poli.taller1.taller1.persistence.repository.ProjectRepository;
import co.com.poli.taller1.taller1.services.dto.ProjectInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{


    private final ProjectRepository projectRepository;


    private final ProjectDTOtoProject projectDTOtoProject;

    @Override
    public List<Project> findAll() {
        return this.projectRepository.findAll();
    }

    @Override
    public Project save(ProjectInDTO projectDTO) {
        Project project = this.findByProjectIdentifier(projectDTO.getProjectIdentifier());

        if(project != null){
            return null;
        }

        project = this.projectRepository.findByProjectName(projectDTO.getProjectName());

         if(project != null){
             project.setId(-1);
             return project;
         }

        project = projectDTOtoProject.mapper(projectDTO);

        return this.projectRepository.save(project);
    }

    @Override
    public Project findByProjectIdentifier(String projectIdentifier){

        return this.projectRepository.findByProjectIdentifier(projectIdentifier);
    }
}
