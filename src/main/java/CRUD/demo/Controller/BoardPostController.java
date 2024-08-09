package CRUD.demo.Controller;

import CRUD.demo.Service.BoardPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BoardPostController {

    private final BoardPostService boardPostService;

    @GetMapping("/")
    public String Home() {
        System.out.println("Home!");
        return "home";
    }

}

