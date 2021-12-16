package com.example.Reto5C4;

import com.example.Reto5C4.repository.Interface.OrderInterface;
import com.example.Reto5C4.repository.Interface.SupplementsInterface;
import com.example.Reto5C4.repository.Interface.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
public class Reto5C4 implements CommandLineRunner {

    @Autowired
    private OrderInterface orderInterface;
    @Autowired
    private UserInterface userInterface;
    @Autowired
    private SupplementsInterface supplementsInterface;

    public static void main(String[] args) {
        SpringApplication.run(Reto5C4.class, args);
    }

	@Override
    public void run(String... args) throws Exception {
        supplementsInterface.deleteAll();
        userInterface.deleteAll();
        orderInterface.deleteAll();
    }
}
