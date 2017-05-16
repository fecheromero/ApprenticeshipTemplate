package com.tenpines;

import com.tenpines.starter.modelo.*;
import com.tenpines.starter.repositorios.RepositorioDeCalendarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;

@SpringBootApplication
public class StarterMainRunner extends SpringBootServletInitializer {
    @Autowired
    private RepositorioDeCalendarios repo;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
           application.profiles("local");
        return application.sources(StarterMainRunner.class);
    }

    public static void main(String[] args) throws Exception {

        SpringApplication.run(StarterMainRunner.class, args);
    }
}