package azor.indatacorebackend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class IndatacoreBackendApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(IndatacoreBackendApplication.class, args);
    }


    @GetMapping("say-hi")
    public String getMessage(){
        return "Hy";
    }



}
