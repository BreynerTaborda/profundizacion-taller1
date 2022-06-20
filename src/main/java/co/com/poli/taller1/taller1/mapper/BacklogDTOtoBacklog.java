package co.com.poli.taller1.taller1.mapper;

import co.com.poli.taller1.taller1.persistence.entity.Backlog;
import co.com.poli.taller1.taller1.services.dto.BacklogInDTO;

public class BacklogDTOtoBacklog implements IMapper<BacklogInDTO, Backlog>{

    @Override
    public Backlog mapper(BacklogInDTO in) {
        Backlog backlog = new Backlog();
        backlog.setProjectIdentifier(in.getProjectIdentifier());

        return backlog;
    }
}
