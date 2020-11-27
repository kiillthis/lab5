package com.example.demo;

import com.example.demo.service.WorkerService;
import com.example.demo.service.WorkerServiceImpl;
import com.example.demo.view.View;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {

        ApplicationContext ac = SpringApplication.run(Main.class, args);

        WorkerService service = ac.getBean(WorkerServiceImpl.class);

        View view = new View(service);

        view.doWork();

    }

}
