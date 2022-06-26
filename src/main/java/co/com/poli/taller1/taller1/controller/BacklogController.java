package co.com.poli.taller1.taller1.controller;

import co.com.poli.taller1.taller1.helpers.Response;
import co.com.poli.taller1.taller1.helpers.ResponseBuild;
import co.com.poli.taller1.taller1.persistence.entity.Backlog;
import co.com.poli.taller1.taller1.services.BacklogService;
import co.com.poli.taller1.taller1.services.dto.BacklogInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/backlogs")
public class BacklogController {

    @Autowired
    private BacklogService backlogService;

    @Autowired
    private ResponseBuild responseBuild;

    @PostMapping
    public Response save(@Valid @RequestBody BacklogInDTO backlogInDTO, BindingResult result){
        Backlog backlog = this.backlogService.save(backlogInDTO);

        if(result.hasErrors()){
            return this.responseBuild.failed(formatMessage(result));
        }

        if(backlog == null){
            FieldError fieldError = new FieldError("data","projectIdentifier","",
                    false, null, null,
                    "No existe el project con identificador: " + backlogInDTO.getProjectIdentifier());
            result.addError(fieldError);

            return this.responseBuild.failed(formatMessage(result));
        }else if(backlog.getId() == -1){
            FieldError fieldError = new FieldError("data","projectIdentifier","",
                    false, null, null,
                    "Ya existe un backlog con identificador: " + backlogInDTO.getProjectIdentifier());
            result.addError(fieldError);

            return this.responseBuild.failed(formatMessage(result));
        }

        return this.responseBuild.successCreated(backlogInDTO);
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
