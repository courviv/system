package com.example.demo.Controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.Service.answerdetailService;
import com.example.demo.Utlis.JWTUtils;
import com.example.demo.entity.answerdetails;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class answerdetailController {
    @Autowired
    private answerdetailService answerdetailService;
    @RequestMapping("/details")
    public List<PageInfo<Map<String, Object>>> getAnswerDetailsByTestPaperId(@RequestParam("test_paper_id") String  test_paper_id,
                                                                             @RequestParam("student_id") String  student_id,
                                                                             @RequestParam("pageNum") int pageNum,
                                                                             @RequestParam(value = "pageSize", defaultValue = "1") int pageSize) {
        return answerdetailService.getAnswerDetailsByTestPaperId(test_paper_id,student_id,pageNum,pageSize);

    }
//试题详情
    @RequestMapping("/detail")
//    public  PageInfo<answerdetails> getAnswerDetailsById(@RequestParam("test_paper_id") String test_paper_id, @RequestParam("student_id") String student_id,
//                                                         @RequestParam("pageNum") int pageNum,
//                                                         @RequestParam(value = "pageSize", defaultValue = "1") int pageSize) {
//        return answerdetailService.getAnswerDetailsById(test_paper_id,student_id,pageNum,pageSize);
//    }
    public  PageInfo<answerdetails> getAnswerDetailsBytoken(@RequestParam("test_paper_id") String test_paper_id,
                                                         @RequestParam("pageNum") int pageNum,
                                                         @RequestParam(value = "pageSize", defaultValue = "1") int pageSize,
                                                            HttpServletRequest request){
        // 在这里解析 token 获取 student_id，假设您有一个名为 parseToken 的方法来解析 token
        String token = request.getHeader("Authorization");
        log.info(token);
        try {
            // 验证令牌
            DecodedJWT verify = JWTUtils.verify(token);
            String student_id= verify.getClaim("student_id").asString();
            log.info(student_id);
            return answerdetailService.getAnswerDetailsById(test_paper_id, student_id, pageNum, pageSize);
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
//            map.put("msg","无效签名！");
            return null;
        }catch (TokenExpiredException e){
            e.printStackTrace();
//            map.put("msg","token过期");
            return null;
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
//            map.put("msg","算法不一致");
            return null;
        }catch (Exception e){
            e.printStackTrace();
//            map.put("msg","token无效！");
            return new PageInfo<>();
        }

    }

}
