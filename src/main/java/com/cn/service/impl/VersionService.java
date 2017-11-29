package com.cn.service.impl;

import com.cn.dao.IVersionDao;
import com.cn.model.Version;
import com.cn.service.IVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("versionService")
public class VersionService implements IVersionService {


    @Autowired
    private IVersionDao versionDao;



    @Override
    public List<Version> getVersionPageByEntity(Version version){
        return versionDao.pageList(version);
    }


    @Override
    public Version getVersionByEntity(Version version) {
        return versionDao.find(version);
    }
    @Override
    public void addVersion(Version version) {
        versionDao.insert(version);
    }

    @Override
     public void modifyVersion(Version version) {
        versionDao.update(version);
    }
    @Override
    public void  deleteVersion(Version version) {
        versionDao.delete(version);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class},timeout=10,isolation= Isolation.SERIALIZABLE)
    public void modifyEffectVersion(Version version) {
        Version v;
        v=new Version();
        v.setType(version.getType());
        v.setStatus("1");
        versionDao.update(v);
        v=new Version();
        v.setId(version.getId());
        v.setV(version.getV());
        v.setType(version.getType());
        v.setStatus("0");
        versionDao.update(v);
    }

    public IVersionDao getVersionDao() {
        return versionDao;
    }

    public void setVersionDao(IVersionDao versionDao) {
        this.versionDao = versionDao;
    }

}
