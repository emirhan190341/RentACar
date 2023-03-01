package com.emirhanarici.rentACar01;

import com.emirhanarici.rentACar01.utilities.exceptions.BusinessException;
import com.emirhanarici.rentACar01.utilities.exceptions.ProblemDetails;
import com.emirhanarici.rentACar01.utilities.exceptions.ValidationProblemDetails;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;


@SpringBootApplication
public class RentACar01Application {

	public static void main(String[] args) {
		SpringApplication.run(RentACar01Application.class, args);
	}



	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}


}
