package com.qianfeng.smsplatform.webmaster.service;

import com.github.pagehelper.PageInfo;
import com.qianfeng.smsplatform.webmaster.dto.QueryDTO;
import com.qianfeng.smsplatform.webmaster.pojo.TRole;

import java.util.List;

public interface RoleService {
    public List<String> findRolsByUserID(Integer userId);

    PageInfo<TRole> getRoleList(QueryDTO queryDTO);

    List<TRole> getRoleList(TRole role);

    void addRoleMenu(Integer roleId, Integer[] menuIds);

    List<Integer> getRoleMenuIds(Integer roleId);

    void addRole(TRole role);

    void updateRole(TRole role);

    void deleteRole(int id);

    TRole getRoleId(Integer id);
}
