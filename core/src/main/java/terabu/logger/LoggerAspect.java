package terabu.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggerAspect {
    @Pointcut(value = "@annotation(terabu.logger.LoggerAnnotation)")
    public void logPointcut() {
    }
    @Before(value = "logPointcut()")
    public void logBefore() {
        log.info("Start request");
    }
    @After(value = "logPointcut()")
    public void logAfter() {
        log.info("End request");
    }
}
