package com.ssafy.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyErrorController extends BasicErrorController {

    public MyErrorController(ErrorAttributes errorAttributes,
            ServerProperties serverProperties,
            List<ErrorViewResolver> errorViewResolvers) {

        super(errorAttributes, serverProperties.getError(), errorViewResolvers);
    }

    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) { 
        ModelAndView mnv = super.errorHtml(request, response);

        HttpStatus hs = getStatus(request);
        switch (hs) {
        case NOT_FOUND:
        	mnv.addObject(hs+" : 페이지를 찾을 수 없습니다!");
            mnv.setViewName("/error/error");
            break;

        default:
        	mnv.addObject(hs+" : 서버 에러가 발생했습니다!");
            mnv.setViewName("/error/error");
            break;
        }
        return mnv;
    }

}