//package com.example.demo.Controller;
//
//import com.example.demo.Service.UsersService;
//import com.example.demo.entity.Users;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//@RestController
//public class IndexController {
//    //logintest小程序的后台
//    @Autowired
//    private UsersService userService;
////    @RequestMapping("/login")
////    public String login(String user_name,String user_pwds){
////        System.out.println("用户名："+user_name+"  ....用户密码："+user_pwds);
////        boolean login = this.userService.login(user_name, user_pwds);
////        System.out.println(login);
////        if (login){
////            return true;
////        }
////        return false;
////    }
//    @RequestMapping("/login")
//    public String login(Users user, HttpSession session, HttpServletRequest request){
//        Users u=userService.login(user);
//        if(u!=null){
//            u.setUser_pwds(null);
//            session.setAttribute("user", u);
//            if(u.getRole()==0){
//                return "0";
//            }else{
//                return "1";
//            }
//        }else{
//            return "555";
//        }
//
//    }
//    @RequestMapping("/count")
//    public Integer count(Integer t1,Integer t2){
//        Integer t3=t1+t2;
//        System.out.println("a："+t1+"  ....b："+t2);
//        return t3;
//    }
//
//}