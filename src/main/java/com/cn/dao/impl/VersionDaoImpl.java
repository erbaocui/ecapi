package com.cn.dao.impl;

import com.cn.dao.IVersionDao;
import com.cn.model.Version;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("versionDao")
public class VersionDaoImpl extends BaseDaoImpl implements IVersionDao {

    @Override
    public List<Version> pageList(Version version) {
        return (List<Version>)list("com.cn.dao.VersionMapper.selectPageByEntity", version);

    }


    @Override
    public void insert(Version version) {
        addObject("com.cn.dao.VersionMapper.insert", version);
    }

    @Override
    public void update(Version version) {
        updateObject("com.cn.dao.VersionMapper.update", version);
    }

    @Override
    public Version find(Version version) {
        return (Version)findObject("com.cn.dao.VersionMapper.selectOneByEntity", version);
    }
    @Override
    public void delete(Version version) {
        deleteObject("com.cn.dao.VersionMapper.deleteByPrimaryKey", version.getId());
    }
}
