package sislamoglu.in.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sislamoglu.in.exception.ResourceNotAvailableError;
import sislamoglu.in.exception.ResourceNotAvailableException;

import java.util.Date;

@ControllerAdvice(
        annotations = {RestController.class}
)
@NoArgsConstructor
public abstract class BaseRestControllerAdvice {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ObjectMapper objectMapper;

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler({ResourceNotAvailable.class})
//    @ResponseBody
//    public ResourceNotAvailableError handleResourceNotAvailableError(ResourceNotAvailableException e){
//        e.getError().setTimestamp(new Date());
//        logger.error(e.getMessage(), e);
//        return e.getError();
//    }
}
