package com.dange.tanmay.multitenancy;

import com.dange.tanmay.interceptor.TenantContext;
import com.dange.tanmay.service.StudentService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StudentServiceAspect {
    @Before("execution(* com.dange.tanmay.service.StudentService.*(..))&& target(studentService) ")
    public void aroundExecution(JoinPoint pjp, StudentService studentService) throws Throwable {
        org.hibernate.Filter filter = studentService.entityManager.unwrap(Session.class).enableFilter("tenantFilter");
        filter.setParameter("tenantId", TenantContext.getCurrentTenant());
        filter.validate();
    }
}
