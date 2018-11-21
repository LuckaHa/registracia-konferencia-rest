package upjs.sk.upjs.registracia_itat_rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import sk.upjs.registracia_itat.persitent.ParticipantNotFoundException;

/*
 * odchytavanie vynimiek
 */
@ControllerAdvice
public class RestAdviser {
	
	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiError handleNumberFormatException(NumberFormatException e) {
		return new ApiError(HttpStatus.BAD_REQUEST.value(), "Participant id musi byt integer"); // HttpStatus.BAD_REQUEST.value() je 400	
	}
	
	@ExceptionHandler(ParticipantNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ApiError handleParticipantNotFoundException(ParticipantNotFoundException e) {
		return new ApiError(HttpStatus.NOT_FOUND.value(), "Participant s id " + e.getParticipantId() + " neexistuje"); // 404
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiError handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		return new ApiError(HttpStatus.BAD_REQUEST.value(), "Nespravny JSON request");
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ApiError handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		return new ApiError(HttpStatus.NOT_FOUND.value(), "Zadana URI neexistuje");
	}
	
	@ExceptionHandler(DaoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ApiError handleDaoException(DaoException e) {
		return new ApiError(HttpStatus.NOT_FOUND.value(), "Participant nie je v spravnom formate");
	}
}
