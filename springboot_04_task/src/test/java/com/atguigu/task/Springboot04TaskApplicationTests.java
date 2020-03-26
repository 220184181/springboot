package com.atguigu.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot04TaskApplicationTests {

    @Autowired
    JavaMailSender mailSender;

    /**
     * 简单
     */
    @Test
    void contextLoads() {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setSubject("今晚开会");
        simpleMailMessage.setText("七点开会");
        simpleMailMessage.setTo("zc220184181@163.com");
        simpleMailMessage.setFrom("1582713083@qq.com");

        mailSender.send(simpleMailMessage);
    }

    /**
     * 复杂
     */
    @Test
    void contextLoads1() throws Exception {

        //1、创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //邮件设置
        helper.setSubject("通知-今晚开会");
        helper.setText("<b style='color:red'>今天 7:30 开会</b>",true);
        helper.setTo("zc220184181@163.com");
        helper.setFrom("1582713083@qq.com");

        //上传文件
        helper.addAttachment("1.jpg",new File("C:\\Users\\小班超\\Pictures\\1.jpg"));
        helper.addAttachment("2.png",new File("C:\\Users\\小班超\\Pictures\\2.png"));

        mailSender.send(mimeMessage);
    }

}
