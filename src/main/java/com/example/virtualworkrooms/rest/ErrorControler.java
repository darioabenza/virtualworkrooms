package com.example.virtualworkrooms.rest;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorControler implements ErrorController{

    @RequestMapping("/error")
    public String redireccionarALogin(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status != null){
            Integer statusCode = Integer.valueOf(status.toString());
            if(statusCode == HttpStatus.FORBIDDEN.value())
                return "login.html";
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
    
}
