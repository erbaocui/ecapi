package com.cn.service;
import com.cn.model.AppLogin;
import java.util.List;

public interface IAppLoginService {
    public List<AppLogin> getAppLoginListByEntity(AppLogin appLogin);
    public AppLogin getAppLoginByEntity(AppLogin appLogin);

    public void addAppLogin(AppLogin appLogin);
    public void modifyAppLogin(AppLogin appLogin);
    public void deleteAppLogin(AppLogin appLogin);
    

}
