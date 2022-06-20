package co.com.poli.taller1.taller1.controller;

import co.com.poli.taller1.taller1.helpers.Response;
import co.com.poli.taller1.taller1.helpers.ResponseBuild;
import co.com.poli.taller1.taller1.persistence.entity.Backlog;
import co.com.poli.taller1.taller1.persistence.entity.ProjectTask;
import co.com.poli.taller1.taller1.persistence.entity.TaskStatus;
import co.com.poli.taller1.taller1.services.BacklogService;
import co.com.poli.taller1.taller1.services.ProjectTaskService;
import co.com.poli.taller1.taller1.services.dto.ProjectTaskInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class ProjectTaskController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private ResponseBuild responseBuild;

    @PostMapping
    public Response save(@Valid @RequestBody ProjectTaskInDTO projectTaskInDTO, BindingResult result) {

        if(result.hasErrors()){
            return this.responseBuild.failed(formatMessage(result));
        }

        ProjectTask projectTask = this.projectTaskService.save(projectTaskInDTO);

        if(projectTask == null && projectTaskInDTO.getProjectIdentifier() != ""){
            FieldError fieldError = new FieldError("data","projectIdentifier","",
                    false, null, null,
                    "No existe el Backlog para el identificador: " + projectTaskInDTO.getProjectIdentifier());
            result.addError(fieldError);

            return this.responseBuild.failed(formatMessage(result));
        }

        return this.responseBuild.successCreated(projectTaskInDTO);
    }


    @GetMapping("/task/project/{projectIdentifier}")
    public Response findByProjectIdentifier(@PathVariable("projectIdentifier") String projectIdentifier) {
        List<ProjectTask> projectTasks = this.projectTaskService.findByProjectIdentifier(projectIdentifier);

        if(projectTasks.isEmpty()){
//            FieldError fieldError = new FieldError("data","projectIdentifier","",
//                    false, null, null,
//                    "Ya existe el identificador: ");
//
//            System.out.println("ASDASDASDASDASDASDASDASDASDASDASDASDASD");
            String mensajeError = "No existen task para el projectIdentifier: "+ projectIdentifier ;
            return this.responseBuild.failed(mensajeError);
        }

        return this.responseBuild.success(projectTasks);
    }

    @GetMapping("/task/project/hours/{projectIdentifier}")
    public Response countHoursNoDeleted(@PathVariable("projectIdentifier") String projectIdentifier) {
        Double cantidadHoras = this.projectTaskService.countHoursNoDeleted(projectIdentifier);

        if(cantidadHoras == null){
            String mensajeError = "No existe el projectIdentifier: "+ projectIdentifier ;
            return this.responseBuild.failed(mensajeError);
        }

        return this.responseBuild.success(cantidadHoras);
    }

    @GetMapping("/task/project/hours/{projectIdentifier}/{status}")
    public Response countHoursByStatus(@PathVariable("projectIdentifier") String projectIdentifier, @PathVariable("status") TaskStatus status) {
        Double cantidadHoras = this.projectTaskService.countHoursByStatus(projectIdentifier, status);

        if(cantidadHoras == null){
            String mensajeError = "No existe el projectIdentifier: "+ projectIdentifier ;
            return this.responseBuild.failed(mensajeError);
        }

        return this.responseBuild.success(cantidadHoras);
    }

    @PatchMapping("/task/{idtask}/{projectIdentifier}")
    public Response deleteTaskLogicoById(@PathVariable("idtask") Long id, @PathVariable("projectIdentifier") String projectIdentifier) {
        String result = this.projectTaskService.deleteTaskLogicoById(id, projectIdentifier);

        if(result == null){
            return this.responseBuild.failed(result);
        }

        return this.responseBuild.success("Correcto");
    }

    private List<Map<String, String>> formatMessage(BindingResult result){
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(error ->{
                    Map<String, String> newError = new HashMap<>();
                    newError.put(error.getField(), error.getDefaultMessage());
                    return newError;
                }).collect(Collectors.toList());
        return errors;

    }
}
