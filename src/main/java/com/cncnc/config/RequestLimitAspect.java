package com.cncnc.config;


import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestLimitAspect {

    private static final Logger logger = LoggerFactory.getLogger(RequestLimitAspect.class);

}
