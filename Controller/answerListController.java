package com.example.demo.Controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.Service.answerListService;
import com.example.demo.Utlis.JWTUtils;
import com.example.demo.entity.answer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@Slf4j
public class answerListController {
    @Autowired
    private answerListService answerListService;
    //考试列表
    @PostMapping("/exam")
    public List<answer> getAnswerSheetsByToken(HttpServletRequest request) {
        // 在这里解析 token 获取 student_id，假设您有一个名为 parseToken 的方法来解析 token
        String token = request.getHeader("Authorization");
        log.info(token);
        try {
            // 验证令牌
            DecodedJWT verify = JWTUtils.verify(token);
            String student_id= verify.getClaim("student_id").asString();
            log.info(student_id);
            // 使用 student_id 进行数据库查询
            List<answer> answers = answerListService.getAnswerSheetsByStudentId(student_id);
            return answers;
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
            return null;
        }

    }
//根据学生id获取历次考试总分



    @PostMapping("/getRecentExam")
    public  List<Map<String, Object>> combinedExam(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        log.info(token);
        try {
            // 验证令牌
            DecodedJWT verify = JWTUtils.verify(token);
            String student_id= verify.getClaim("student_id").asString();
            log.info(student_id);
            // 使用 student_id 进行数据库查询
            List<answer> answers = answerListService.getAnswerSheetsByStudentId(student_id);
            log.info(String.valueOf(answers));
            List<Map<String, Object>> combinedExamScores = new ArrayList<>(); // 存储整合后的考试信息和成绩
            //answer是初步整合的考试信息
            for (answer answer :answers){
                String examId = answer.getExam_id();
                boolean found = false;
                for (Map<String, Object> combinedExam : combinedExamScores) {
                    if (combinedExam.get("examId").equals(examId)) {
                        Map<String, Object> subjectScore = new HashMap<>();
                        subjectScore.put("subject", answer.getSubject());
                        subjectScore.put("sumscore", answer.getSumscore());
                        subjectScore.put("psumscore", answer.getPsumscore());
                        ((List<Map<String, Object>>) combinedExam.get("scores")).add(subjectScore);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    Map<String, Object> combinedExam = new HashMap<>();
                    combinedExam.put("examId", examId);
                    combinedExam.put("examName", answer.getExam_name());
                    combinedExam.put("examTime", answer.getExam_time());
                    List<Map<String, Object>> scores = new ArrayList<>();
                    Map<String, Object> subjectScore = new HashMap<>();
                    subjectScore.put("subject", answer.getSubject());
                    subjectScore.put("sumscore", answer.getSumscore());
                    subjectScore.put("psumscore", answer.getPsumscore());
                    scores.add(subjectScore);
                    combinedExam.put("scores", scores);
                    combinedExamScores.add(combinedExam);
                }
            }
            for (Map<String, Object> combinedExam : combinedExamScores) {
                int totalScore = 0;
                List<Map<String, Object>> scores = (List<Map<String, Object>>) combinedExam.get("scores");
                for (Map<String, Object> subject : scores) {
                    totalScore += Integer.parseInt(subject.get("sumscore").toString());
                }
                combinedExam.put("totalScore", totalScore);
            }
            // 先按考试时间从晚到早排序
            combinedExamScores.sort((exam1, exam2) -> {
                Date examTime1 = new Date(((java.sql.Date) exam1.get("examTime")).getTime());
                Date examTime2 = new Date(((java.sql.Date) exam2.get("examTime")).getTime());
                return examTime2.compareTo(examTime1);
            });

            // 对于相同考试时间的数据，按照 test_paper_id 从小到大排序
            for (Map<String, Object> combinedExam : combinedExamScores) {
                if (combinedExam.containsKey("scores") && combinedExam.get("scores") instanceof List) {
                    List<Map<String, Object>> scores = (List<Map<String, Object>>) combinedExam.get("scores");
                    scores.sort(Comparator.comparingInt(subject -> {
                        if (subject.get("test_paper_id") instanceof Integer) {
                            return (int) subject.get("test_paper_id");
                        } else {
                            return 0; // 或者其他默认值，视情况而定
                        }
                    }));
                }
            }
            log.info(String.valueOf(combinedExamScores));
            return combinedExamScores;
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
            return null;
        }
    }

//    public List<answer> getAnswerSheetsByStudentId(String student_id){
//        return answerListService.getAnswerSheetsByStudentId(student_id);
//    }

}
