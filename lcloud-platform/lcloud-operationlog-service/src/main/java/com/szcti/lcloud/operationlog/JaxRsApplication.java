package com.szcti.lcloud.operationlog;

import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@Component
@ApplicationPath("/v1/")
public class JaxRsApplication extends Application {

}