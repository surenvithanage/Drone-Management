package com.server.musalasoft.drone_management.annotation;

import com.server.musalasoft.drone_management.utility.function.Common;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoggableServiceAspect {

    private final Common common;

    @Before(value = "@annotation(LoggableService) && args(o, ..)")
    public void beforeServiceLog(JoinPoint joinPoint, Object o) {
        common.writeLog(joinPoint, o);
    }
}
