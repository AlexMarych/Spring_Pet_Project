package org.spring.pet_project.Handler;

import org.springframework.http.HttpStatus;

@ExceptionConfiguration(code = "INTERNAL_EXCEPTION", status = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalException extends RuntimeException {
}
