package ma.StudentMVC;

import ma.StudentMVC.entities.Student;
import ma.StudentMVC.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class StudentMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentMvcApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(StudentRepository StudentRepository){
        return  args -> {
            StudentRepository.save(new Student(null,"Hassan",new Date(),"Maths"));
            StudentRepository.save(new Student(null,"Mohammed",new Date(),"Physique"));
            StudentRepository.save(new Student(null,"Yasmine",new Date(),"Maths"));
            StudentRepository.save(new Student(null,"Hanae",new Date(),"Chimie"));

            StudentRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });
        };
    }
}
