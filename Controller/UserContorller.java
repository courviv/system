package com.example.demo.Controller;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.Service.studentService;
import com.example.demo.Utlis.JWTUtils;
import com.example.demo.entity.student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class UserContorller {
    @Autowired
    private studentService studentService;
//学生登录
    @GetMapping("/student/login")
    public Map<String, Object> login(student student) {
        log.info("ID：[{}]", student.getStudent_id());
        log.info("密码：[{}]", student.getPassword());

        Map<String, Object> map = new HashMap<>();
        try {
           student studentDB = studentService.login(student);
           log.info(String.valueOf(studentDB));
            Map<String, String> payload = new HashMap<>();
            payload.put("student_id", studentDB.getStudent_id());
            payload.put("password", studentDB.getPassword());
            // 生成jwt令牌
            String token = JWTUtils.getToken(payload);
            map.put("state", true);
            map.put("msg", "认证成功！");
//            map.put("role",userDB.getRole());
            map.put("token", token);  // 响应token
        } catch (Exception e) {
            map.put("state", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }

//    @PostMapping("/user/test")
//    public Map<String,Object> test(String token){
//        log.info("当前token为：[{}]",token);
//        Map<String,Object> map = new HashMap<>();
//        try {
//            // 验证令牌
//            DecodedJWT verify = JWTUtils.verify(token);
//            map.put("state",true);
//            map.put("msg","请求成功");
//            return map;
//        } catch (SignatureVerificationException e) {
//            e.printStackTrace();
//            map.put("msg","无效签名！");
//        }catch (TokenExpiredException e){
//            e.printStackTrace();
//            map.put("msg","token过期");
//        }catch (AlgorithmMismatchException e){
//            e.printStackTrace();
//            map.put("msg","算法不一致");
//        }catch (Exception e){
//            e.printStackTrace();
//            map.put("msg","token无效！");
//        }
//        map.put("state",false);
//        return map;
//    }
    @PostMapping("/student/test")
    public Map<String, Object> test(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        // 验证令牌  交给拦截器去做
        // 只需要在这里处理自己的业务逻辑
        String token = request.getHeader("token");
        try {
            // 验证令牌
            DecodedJWT verify = JWTUtils.verify(token);
            log.info("用户id：[{}]", verify.getClaim("student_id").asString());
            log.info("用户密码：[{}]", verify.getClaim("password").asString());
            String student_id= verify.getClaim("student_id").asString();
            map.put("用户",student_id);
            map.put("state", true);
            map.put("msg", "请求成功");
            return map;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg","无效签名！");
        }catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("msg","token过期");
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("msg","算法不一致");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","token无效！");
        }
        map.put("state",false);
        return map;
    }
}