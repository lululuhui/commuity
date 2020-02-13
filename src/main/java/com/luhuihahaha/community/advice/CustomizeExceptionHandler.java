

package com.luhuihahaha.community.advice;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class CustomizeExceptionHandler implements ErrorController {

    @Override
    public String getErrorPath() {
        return "errors";
    }

    @RequestMapping
    public String error() {
        return getErrorPath();
    }
}
