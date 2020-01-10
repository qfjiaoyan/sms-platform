package com.qianfeng.smsplatform.webmaster.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.qianfeng.smsplatform.webmaster.dto.DataGridResult;
import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.dto.UserDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TAdminUser;
import com.qianfeng.smsplatform.webmaster.service.AdminUserService;
import com.qianfeng.smsplatform.webmaster.util.MD5Utils;
import com.qianfeng.smsplatform.webmaster.util.R;
import com.qianfeng.smsplatform.webmaster.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private DefaultKaptcha kaptcha;

    @RequestMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response) {
        // 缓存设置-设置不缓存（可选操作）
        response.setHeader("Cache-Control", "no-store, no-cache");
        // 设置响应内容
        response.setContentType("image/jpg");
        //生成验证码
        String text = kaptcha.createText();//文本
        //生成图片
        BufferedImage image = kaptcha.createImage(text);
        //验证码存储到shiro的 session
        ShiroUtils.setKaptcha(text);
        try {
            //返回到页面
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/sys/login")
    @ResponseBody
    public R login(@RequestBody UserDTO userDTO) {
        //比对验证码
        String serverKaptcha = ShiroUtils.getKaptcha();
        if (!serverKaptcha.equalsIgnoreCase(userDTO.getCaptcha())) {
            return R.error("验证码错误");
        }
        Subject subject = SecurityUtils.getSubject();
        String newPass = MD5Utils.md5(userDTO.getPassword(), userDTO.getUsername(), 1024);
        UsernamePasswordToken token = new UsernamePasswordToken(userDTO.getUsername(), newPass);
        if (userDTO.isRememberMe()) {
            token.setRememberMe(true);
        }
        subject.login(token);
        //会去调用自定义的realm
        return R.ok();
    }

    @RequestMapping("/sys/user/list")
    @ResponseBody
    public DataGridResult findUser(QueryDTO queryDTO) {
        return adminUserService.findUserByPage(queryDTO);
    }


    @RequestMapping("/logout")
    public String logout() {
        ShiroUtils.logout();
        return "redirect:login.html";
    }

    @RequestMapping("/sys/user/info")
    @ResponseBody
    public R userinfo() {
        //可以从shiro中获取
        TAdminUser userEntity = ShiroUtils.getUserEntity();
        return R.ok().put("user", userEntity);
    }

    @ResponseBody
    @RequestMapping("/sys/user/del")
    public R delAdminUser(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            adminUserService.delAdminUser(id);
        }
        return R.ok();
    }

    @ResponseBody
    @RequestMapping("/sys/user/info/{id}")
    public R findById(@PathVariable("id") Integer id) {
        TAdminUser tAdminUser = adminUserService.findById(id);
        return R.ok().put("user", tAdminUser);
    }

    @ResponseBody
    @RequestMapping("/sys/user/save")
    public R addAdminUser(@RequestBody TAdminUser tAdminUser) {
        int i = adminUserService.addAdminUser(tAdminUser);
        return i > 0 ? R.ok() : R.error("添加失败");
    }

    @ResponseBody
    @RequestMapping("/sys/user/update")
    public R updateAdminUser(@RequestBody TAdminUser tAdminUser) {
        int i = adminUserService.updateAdminUser(tAdminUser);
        return i > 0 ? R.ok() : R.error("修改失败");
    }

}
