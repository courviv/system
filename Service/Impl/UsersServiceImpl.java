//package com.example.demo.Service.Impl;
//import com.example.demo.Service.UsersService;
//import com.example.demo.entity.Users;
//import com.example.demo.mapper.UsersMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional
//public class UsersServiceImpl implements UsersService{
//    @Autowired
//    private UsersMapper usersMapper;
//    @Override
//    @Transactional(propagation = Propagation.SUPPORTS)
//    public Users login(Users users){
//        Users userDB = usersMapper.login(users);
//        if(userDB!=null){
//            return userDB;
//        }
//        throw  new RuntimeException("登录失败 -.-");
//
//    }
////    public boolean login(String user_name, String user_pwds) {
////        Users users = new Users();
////        users.setUser_name(user_name);
////        users.setUser_pwds(user_pwds);
////        Users allUsers = this.usersMapper.findAllUsers(users);
////        if (allUsers != null){
////            return true;
////        }
////        return false;
////    }
//
//
//}
//
