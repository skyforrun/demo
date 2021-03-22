package com.hgnu.study;

import com.hgnu.study.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;

/**
 * @description:
 * @author: zj
 * @time: 2020/12/18 9:28
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailSendTest {

    @Autowired
    MailService mailService;

    @Test
    public void test1(){
        mailService.sendSimpleMail("18717121440@163.com","发送简单邮件测试","springboot发送简单邮件",null);
    }

    @Test
    public void test2() throws MessagingException {
        mailService.sendHtmlMail("18717121440@163.com","发送html邮件测试","<h1 style=\"color:#f33b45;\">springboot发送html邮件</h1>",null);
    }

    @Test
    public void test3() throws MessagingException {
        mailService.sendAttachmentsMail("18717121440@163.com","发送附件邮件测试","springboot发送带附件的邮件","F:\\timg.jpg");
        //mailService.sendAttachmentsMail("18717121440@163.com","发送附件邮件测试","springboot发送带附件的邮件","./public/qrcode/4072737.jpg");
    }

    @Test
    public void test4() throws MessagingException{
        mailService.sendResourceMail("18717121440@163.com","发送静态资源邮件测试","springboot发送静态资源测试","F:\\error.html","1",null);
    }
}
