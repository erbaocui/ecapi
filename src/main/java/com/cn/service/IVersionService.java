package com.cn.service;

import com.cn.model.Version;

import java.util.List;

public interface IVersionService {
    //用户
    public List<Version> getVersionPageByEntity(Version version);
    public Version getVersionByEntity(Version version);
    public void addVersion(Version version);
    public void modifyVersion(Version version);
    public void deleteVersion(Version version);
    public void modifyEffectVersion(Version version);

}
