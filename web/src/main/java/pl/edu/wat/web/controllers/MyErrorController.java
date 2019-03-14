package pl.edu.wat.web.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    private final String PATH = "/error";

    @ResponseBody
    @RequestMapping(value = PATH, method = {RequestMethod.GET, RequestMethod.POST})
    public Object error(HttpServletRequest request) {
        return request.getAttribute("javax.servlet.error.status_code");
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
