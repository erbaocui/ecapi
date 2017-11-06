package com.cn.dao.impl;

import com.cn.dao.IAdminDao;
import com.cn.dao.IUserDao;
import com.cn.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("adminDao")
public class AdminDaoImpl extends BaseDaoImpl implements IAdminDao {
    @Override
    public String find(String seqName) {
        return (String) findObject("com.cn.dao.AdminMapper.selectSeq", seqName);
    }


}
