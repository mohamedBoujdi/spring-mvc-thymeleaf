package me.med.springmvc_thymeleaf;

import com.github.javafaker.Faker;
import me.med.springmvc_thymeleaf.Entities.Patient;
import me.med.springmvc_thymeleaf.Repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;


@SpringBootApplication//(exclude = { DataSourceAutoConfiguration.class })
public class SpringMvcThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcThymeleafApplication.class, args);
    }
 @Bean
    CommandLineRunner start(PatientRepository patientRepository){
        return args -> {
            Faker faker=new Faker();
            for (int cpt=0;cpt<100;cpt++)
                patientRepository.save(new Patient(null,faker.name().fullName(),faker.date().birthday(),faker.bool().bool()));


            patientRepository.findAll().forEach(patient -> {
                System.out.println("patient:"+ patient.getName());
                System.out.println("date:"+ patient.getDate_naissance());
                System.out.println("cas: il "+ ((patient.getMalade()==false)?"n'est pas ":"est ")+"malade " );
                System.out.println("============================================");
            });
        } ;
 }
}
