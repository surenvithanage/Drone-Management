package com.server.musalasoft.drone_management.utility.function;

import com.server.musalasoft.drone_management.bean.response.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Slf4j
@Component
public class Common {

    public ResponseEntity<ResponseBean> getResponseEntityBean(ResponseBean responseBean) {
        if (responseBean.isRequestOk()) {
            return new ResponseEntity<>(responseBean, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseBean, HttpStatus.BAD_REQUEST);
        }
    }

    public void writeLog(JoinPoint joinPoint, Object o) {
        try {
            if (o != null) {
                this.logObjectContent(joinPoint, o);
            }
        } catch (Exception e) {
            log.error("Exception : ", e);
        }
    }

    public void logObjectContent(JoinPoint joinPoint, Object o) throws IllegalAccessException {
        StringBuilder result = new StringBuilder();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.info("------------------------------------------------------------");
        log.info("className and methodName : " + className + "  " + methodName);
        for (Field field : o.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String name = field.getName();
            Object value = field.get(o);
            result.append("  ").append(name).append(" : ").append(value);
        }
        log.info("object attributes :" + result);
        log.info("------------------------------------------------------------");
    }
}
