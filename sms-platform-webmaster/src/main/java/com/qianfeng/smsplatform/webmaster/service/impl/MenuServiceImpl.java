package com.qianfeng.smsplatform.webmaster.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.smsplatform.webmaster.dao.TMenuMapper;
import com.qianfeng.smsplatform.webmaster.dto.DataGridResult;
import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TMenu;
import com.qianfeng.smsplatform.webmaster.service.MenuService;
import com.qianfeng.smsplatform.webmaster.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private TMenuMapper tMenuMapper;

    @Override
    public DataGridResult findMenu(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(), queryDTO.getLimit());
        if (queryDTO.getSort() != null && !queryDTO.getSort().equals("")) {
            queryDTO.setSort("id");
        }
        List<TMenu> menuByPage = tMenuMapper.findMenuByPage(queryDTO);
        PageInfo<TMenu> info = new PageInfo<TMenu>(menuByPage);
        long total = info.getTotal();

        DataGridResult result = new DataGridResult(total, info.getList());
        return result;
    }

    //    @Transactional(propagation = Propagation.REQUIRED)
    public R deleteMenu(List<Long> ids) {
        for (Long id : ids) {
            if (id < 51) {
                return R.error("系统菜单，不支持删除");
            }
        }

        int i = tMenuMapper.deleteMenu(ids);
        if (i > 0) {
            return R.ok();
        } else {
            return R.error(-200, "删除失败");
        }
    }

    @Override
    public R selectMenu() {
        List<TMenu> menu = tMenuMapper.findMenu();
        //添加一个根目录
        TMenu sysMenu = new TMenu();
        sysMenu.setId(0);
        sysMenu.setType((short) 0);
        sysMenu.setParentId(-1);
        sysMenu.setName("一级菜单");
        menu.add(sysMenu);

        return R.ok().put("menuList", menu);
    }

    @Override
    public R saveMenu(TMenu tMenu) {
        int i = tMenuMapper.insertSelective(tMenu);
        return i > 0 ? R.ok() : R.error("新增失败");
    }

    @Override
    public R findMenuById(Integer menuId) {
        TMenu tMenu = tMenuMapper.selectByPrimaryKey(menuId);
        return R.ok().put("menu", tMenu);
    }

    @Override
    public R updateMenu(TMenu tMenu) {
        int i = tMenuMapper.updateByPrimaryKeySelective(tMenu);
        return i > 0 ? R.ok() : R.error("修改失败");
    }

    @Override
    public List<String> findPermsByUserId(Integer userId) {
        List<String> permsByUserId = tMenuMapper.findPermsByUserId(userId);
        Set<String> set = new HashSet<String>();
        for (String s : permsByUserId) {
            if (s != null && !s.equals("")) {
                String[] split = s.split(",");
                for (String s1 : split) {
                    set.add(s1);
                }
            }
        }
        List<String> perms = new ArrayList<>();
        perms.addAll(set);
        return perms;
    }

    @Override
    public R findUserMenu(Integer userId) {
        //查询用户的一级目录
        List<Map<String, Object>> dirMenuByUserId = tMenuMapper.findDirMenuByUserId(userId);
        //查询目录对应的子菜单
        for (Map<String, Object> map : dirMenuByUserId) {
            Integer menuId = Integer.parseInt(map.get("id") + "");
            List<Map<String, Object>> subList = tMenuMapper.findMenuNotButtonByUserId(userId, menuId);
            map.put("list", subList);
        }
        List<String> permsByUserId = findPermsByUserId(userId);
        return R.ok().put("menuList", dirMenuByUserId).put("permissions", permsByUserId);
    }
}
