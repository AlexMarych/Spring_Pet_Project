package org.spring.pet_project.Mapper;

import org.mapstruct.*;
import org.spring.pet_project.Config.IdExtractor;
import org.spring.pet_project.Controller.DTO.Response.*;
import org.spring.pet_project.Model.*;

import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RequestMapper {

    
}
