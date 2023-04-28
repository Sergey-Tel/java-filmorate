package ru.yandex.practicum.filmorate.controller.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exception.IncorrectParameterException;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.ErrorResponse;
import ru.yandex.practicum.filmorate.model.ValidationErrorResponse;

import java.util.Collections;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(IncorrectParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidateUser(final RuntimeException exp) {
        return new ErrorResponse(exp.getMessage());
    }

    @ExceptionHandler({FilmNotFoundException.class, UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleFilmNotFound(final RuntimeException exp) {
        return new ErrorResponse(exp.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleBindException(MethodArgumentNotValidException exp) {
        log.error(exp.getMessage());
        return new ValidationErrorResponse(Collections.singletonMap("error:", exp.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleThrowable(final Throwable exp) {
        log.error("Произошла непредвиденная ошибка.");
        return new ErrorResponse("Произошла непредвиденная ошибка.");
    }

}