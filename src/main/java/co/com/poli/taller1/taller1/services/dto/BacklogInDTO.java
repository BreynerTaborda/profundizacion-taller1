package co.com.poli.taller1.taller1.services.dto;

import javax.validation.constraints.NotBlank;

public class BacklogInDTO {

    @NotBlank
    private String projectIdentifier;

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
}
