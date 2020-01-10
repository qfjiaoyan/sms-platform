package com.qianfeng.smsplatform.webmaster.service;

import com.qianfeng.smsplatform.webmaster.dto.DataGridResult;
import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TMenu;
import com.qianfeng.smsplatform.webmaster.util.R;

import java.util.List;

public interface MenuService {
    public DataGridResult findMenu(QueryDTO queryDTO);

    public R deleteMenu(List<Long> ids);

    public R selectMenu();

    public R saveMenu(TMenu tMenu);

    public R findMenuById(Integer menuId);

    public R updateMenu(TMenu tMenu);

    public List<String> findPermsByUserId(Integer userId);

    public R findUserMenu(Integer userId);

}
