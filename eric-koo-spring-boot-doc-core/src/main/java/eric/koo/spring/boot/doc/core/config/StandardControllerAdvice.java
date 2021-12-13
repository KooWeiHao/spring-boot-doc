package eric.koo.spring.boot.doc.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
class StandardControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(StandardControllerAdvice.class);

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    ResponseEntity<Map<String, String>> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException exception){
        logger.error(exception.getMessage());
        return ResponseEntity.badRequest().body(Collections.singletonMap("message", "max.upload.size.exceeded"));
    }
}
