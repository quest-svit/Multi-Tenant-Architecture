package com.dange.tanmay.multitenancy;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.File;
import java.util.Map;

@Configuration
public class FlywaySlaveInitializer {

    @Autowired
    TenantDataSource tenantDataSource;

    @PostConstruct
    public void migrateFlyway() {
        Flyway flyway = new Flyway();

        Map<String, DataSource> dataSources = tenantDataSource.getAll();
        //source 2
        for (Map.Entry<String, DataSource> entry : dataSources.entrySet()) {
            String name = entry.getKey();
            DataSource ds = entry.getValue();

            //logic to find absolute path of resources folder for flyway to run migrations for tenant dbs
            File f = new File("src/main/resources/test.txt");
            String path = f.getAbsolutePath();
            String absolutePath  = path.substring(0,path.lastIndexOf("/"));
            flyway.setDataSource(ds);
            flyway.setLocations("filesystem:"+absolutePath+"/"+name+"/db/migration");
            flyway.migrate();

        }
    }
}