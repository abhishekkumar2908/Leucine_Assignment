//package com.leucine.Assignment.handler;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.logging.Logger;
//
//@Controller
//public class MyErrorController implements ErrorController {
//
////    @RequestMapping("/error")
////    public String handleError() {
//        Logger logger = Logger.getLogger(MyErrorController.class.getName());
////        return "error";
////    }
//
//    @RequestMapping("/error")
//    public String handleError(HttpServletRequest request) {
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//
//        if (status != null) {
//            Integer statusCode = Integer.valueOf(status.toString());
//
//            if(statusCode == HttpStatus.NOT_FOUND.value()) {
//                return "error";
//            }
//            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//                return "error";
//            }
//            else if (statusCode == HttpStatus.FORBIDDEN.value()) {
////                logger.log(System.Logger.Level.ERROR,"403 error", request.getRequ);
//                return "error";
//            }
//        }
//        return "error";
//    }
//}
