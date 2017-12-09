package lgh.app.example.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonParseException;

import lgh.app.example.dto.ResponseCode;
import lgh.app.example.dto.ResponseObject;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {
    @ExceptionHandler(value = JsonParseException.class)
    @ResponseBody
    public ResponseEntity<ResponseObject> handleJsonParseException(JsonParseException e) {
        log.info("ExceptionHandler:{}", e);
        ResponseObject responseObject = new ResponseObject(ResponseCode.FORMAT_ERROR);
        return new ResponseEntity<ResponseObject>(responseObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ResponseObject> handleException(Exception e, HttpServletRequest req) {
        log.warn("ExceptionHandler:{}", e);
        ResponseObject responseObject = new ResponseObject(ResponseCode.SERVER_ERROR, req.getRequestURI());
        return new ResponseEntity<ResponseObject>(responseObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
