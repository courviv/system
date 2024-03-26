package com.example.demo.Controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.Service.teacherService;
import com.example.demo.Utlis.JWTUtils;
import com.example.demo.entity.student;
import com.example.demo.entity.teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class teacherController {
    @Autowired
    private teacherService teacherService;
    //学生登录
    @GetMapping("/teacher/login")
    public Map<String, Object> login(teacher teacher) {
        log.info("ID：[{}]", teacher.getTeacher_id());
        log.info("密码：[{}]", teacher.getPassword());
        Map<String, Object> map = new HashMap<>();
        try {
            teacher teacherDB = teacherService.login(teacher);
            Map<String, String> payload = new HashMap<>();
            payload.put("teacher_id", teacherDB.getTeacher_id());
            payload.put("password", teacherDB.getPassword());
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

    @PostMapping("/teacher/test")
    public Map<String, Object> test(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String token = request.getHeader("token");
        try {
            // 验证令牌
            DecodedJWT verify = JWTUtils.verify(token);
            log.info("用户id：[{}]", verify.getClaim("teacher_id").asString());
            log.info("用户密码：[{}]", verify.getClaim("password").asString());
            String teacher_id= verify.getClaim("teacher_id").asString();
            map.put("用户",teacher_id);
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
