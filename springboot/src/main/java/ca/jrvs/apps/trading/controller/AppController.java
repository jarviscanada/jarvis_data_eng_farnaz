package ca.jrvs.apps.trading.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/")
public class AppController {

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(path = "/health")
    public String health() {
        return "I'm very very healthy!!!";
    }
}