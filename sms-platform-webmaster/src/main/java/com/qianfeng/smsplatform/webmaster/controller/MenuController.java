package com.qianfeng.smsplatform.webmaster.controller;

import com.qianfeng.smsplatform.webmaster.dto.DataGridResult;
import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TMenu;
import com.qianfeng.smsplatform.webmaster.service.MenuService;
import com.qianfeng.smsplatform.webmaster.util.R;
import com.qianfeng.smsplatform.webmaster.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/sys/menu/list")
    @ResponseBody
    @RequiresPermissions("sys:menu:list")
    public DataGridResult findMenu(QueryDTO queryDTO) {
        return menuService.findMenu(queryDTO);
    }

    @RequestMapping("/sys/menu/del")
    @ResponseBody
    public R deleteMenu(@RequestBody List<Long> ids) {
        return menuService.deleteMenu(ids);
    }

    @RequestMapping("/sys/menu/select")
    @ResponseBody
    public R selectMenu() {
        return menuService.selectMenu();
    }

    @RequestMapping("/sys/menu/save")
    @ResponseBody
    public R saveMenu(@RequestBody TMenu tMenu) {
        return menuService.saveMenu(tMenu);
    }


    @RequestMapping("/sys/menu/info/{menuId}")
    @ResponseBody
    public R findMenuById(@PathVariable("menuId") Integer menuId) {
        return menuService.findMenuById(menuId);
    }

    @RequestMapping("/sys/menu/update")
    @ResponseBody
    public R updateMenu(@RequestBody TMenu tMenu) {
        return menuService.updateMenu(tMenu);
    }


    @RequestMapping("/sys/menu/user")
    @ResponseBody
    public R userMenu() {
        Integer userId = ShiroUtils.getUserId();
        return menuService.findUserMenu(userId);
    }


}
