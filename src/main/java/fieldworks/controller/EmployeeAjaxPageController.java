package fieldworks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee/ajax")
public class EmployeeAjaxPageController {

    @GetMapping
    public String page() {
        return "employee-ajax";
    }
}

