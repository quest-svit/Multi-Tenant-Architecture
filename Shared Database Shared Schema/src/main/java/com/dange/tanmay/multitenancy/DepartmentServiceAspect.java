package com.dange.tanmay.multitenancy;

import com.dange.tanmay.interceptor.TenantContext;
import com.dange.tanmay.service.DepartmentService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DepartmentServiceAspect {
    @Before("execution(* com.dange.tanmay.service.DepartmentService.*(..))&& target(departmentService) ")
    public void aroundExecution(JoinPoint pjp, DepartmentService departmentService) throws Throwable {
        org.hibernate.Filter filter = departmentService.entityManager.unwrap(Session.class).enableFilter("tenantFilter");
        filter.setParameter("tenantId", TenantContext.getCurrentTenant());
        filter.validate();
    }
}
