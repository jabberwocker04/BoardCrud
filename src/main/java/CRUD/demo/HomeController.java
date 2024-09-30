package CRUD.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String Home() {
        System.out.println("Home!");
        log.info("Home Controller Start");
        return "home";
    }

}
