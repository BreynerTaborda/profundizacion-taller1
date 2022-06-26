package co.com.poli.taller1.taller1.services;

import co.com.poli.taller1.taller1.mapper.ProjectTaskDTOtoProjectTask;
import co.com.poli.taller1.taller1.persistence.entity.Backlog;
import co.com.poli.taller1.taller1.persistence.entity.ProjectTask;
import co.com.poli.taller1.taller1.persistence.entity.TaskStatus;
import co.com.poli.taller1.taller1.persistence.repository.BacklogRepository;
import co.com.poli.taller1.taller1.persistence.repository.ProjectTaskRepository;
import co.com.poli.taller1.taller1.services.dto.ProjectTaskInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectTaskServiceImpl implements  ProjectTaskService{

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectTaskDTOtoProjectTask projectTaskDTOtoProjectTask;

    @Autowired
    private BacklogService backlogService;

    @Override
    public ProjectTask save(ProjectTaskInDTO projectTaskInDTO) {
        Backlog backlog = this.backlogService.findByProjectIdentifier(projectTaskInDTO.getProjectIdentifier());

        if(backlog == null){
            return null;
        }

        ProjectTask projectTask = this.projectTaskDTOtoProjectTask.mapper(projectTaskInDTO);

        return this.projectTaskRepository.save(projectTask);
    }

    @Override
    public List<ProjectTask> findByProjectIdentifier(String projectIdentifier) {
        return this.projectTaskRepository.findByProjectIdentifier(projectIdentifier);
    }

    @Transactional
    @Override
    public Double countHoursNoDeleted(String projectIdentifier) {
        Backlog backlog = this.backlogService.findByProjectIdentifier(projectIdentifier);

        if(backlog == null){
            return null;
        }

        Double cantidadHoras = this.projectTaskRepository.countHoursNoDeleted(projectIdentifier);

        if(cantidadHoras == null){
            return -1D;
        }

        return cantidadHoras;
    }

    @Transactional
    @Override
    public Double countHoursByStatus(String projectIdentifier, TaskStatus status) {
        Backlog backlog = this.backlogService.findByProjectIdentifier(projectIdentifier);

        if(backlog == null){
            return null;
        }

        Double cantidadHoras = this.projectTaskRepository.countHoursByStatus(projectIdentifier, status.ordinal());

        if(cantidadHoras == null){
            return -1D;
        }

        return cantidadHoras;
    }

    @Transactional
    @Override
    public String deleteTaskLogicoById(Long id, String projectIdentifier) {
        Optional<ProjectTask> projectTask = this.projectTaskRepository.findById(id);

        if(projectTask.isEmpty()){
            return null;
        }else if(!projectTask.get().getProjectIdentifier().equals(projectIdentifier)){
            return "Task no en projecIdentifier";
        }

        Backlog backlog = this.backlogService.findByProjectIdentifier(projectIdentifier);

        if(backlog == null){
            return "Backlog inexistente";
        }

        TaskStatus status = TaskStatus.DELETED;

        this.projectTaskRepository.deleteTaskLogicoById(id, projectIdentifier, status.ordinal());

        return "Se ha eliminado el task correctamente";
    }
}
