package com.cn.dao.impl;

import com.cn.dao.IRoleMenuDao;
import com.cn.model.RoleMenu;
import com.cn.vo.MenuEx;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("roleMenuDao")
public class RoleMenuDaoImpl extends BaseDaoImpl implements IRoleMenuDao {

    @Override
    public void insertBatch(List<RoleMenu> list) {
        addObject("com.cn.dao.RoleMenuMapper.insertBatch",list);
    }

    @Override
    public List<MenuEx> selectRoleMenuList(RoleMenu roleMenu) {
        return (List<MenuEx>)list("com.cn.dao.RoleMenuMapper.selectRoleMenuByParentId", roleMenu);

    }

    @Override
    public List<MenuEx> selectRoleMenuShowList(RoleMenu roleMenu) {
        return (List<MenuEx>)list("com.cn.dao.RoleMenuMapper.selectRoleMenuShowByParentId", roleMenu);
    }

    @Override
    public void delete(String roleId) {
            deleteObject("com.cn.dao.RoleMenuMapper.deleteByRoleId", roleId);
    }
}
