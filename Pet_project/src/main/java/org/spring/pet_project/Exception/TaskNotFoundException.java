package org.spring.pet_project.Exception;

import org.spring.pet_project.Handler.ExceptionConfiguration;
import org.springframework.http.HttpStatus;

@ExceptionConfiguration(code = "Task_Not_Found", status = HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException {

}
