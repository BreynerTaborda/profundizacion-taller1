package co.com.poli.taller1.taller1.controller;

import co.com.poli.taller1.taller1.helpers.Response;
import co.com.poli.taller1.taller1.helpers.ResponseBuild;
import co.com.poli.taller1.taller1.persistence.entity.Project;
import co.com.poli.taller1.taller1.services.ProjectService;
import co.com.poli.taller1.taller1.services.dto.ProjectInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ResponseBuild responseBuild;

    @GetMapping
    public List<Project> findAll() {
        return this.projectService.findAll();
    }

    @PostMapping
    public Response save(@Valid @RequestBody ProjectInDTO projectDTO, BindingResult result) {

        if(result.hasErrors()){
            return this.responseBuild.failed(formatMessage(result));
        }
        this.projectService.save(projectDTO);
        return this.responseBuild.successCreated(projectDTO);
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
