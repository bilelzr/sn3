package com.sofrecom.sn3.repositories;

import com.sofrecom.sn3.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("select application from Application application where application.applicationName ilike :applicatioName")
    Application findByName(@Param("applicationName") String applicationName);
}
