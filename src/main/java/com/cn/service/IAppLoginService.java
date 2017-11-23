package com.cn.service;
import com.cn.model.AppLogin;
import java.util.List;

public interface IAppLoginService {
    public List<AppLogin> getAppLoginListByEntity(AppLogin appLogin);
    public AppLogin getAppLoginByEntity(AppLogin appLogin);

    public void modifyAppLogin(AppLogin appLogin);
    public void deleteAppLogin(AppLogin appLogin);

    //客户登录
    public void addApplogin( AppLogin appLogin,AppLogin lastAppLogin) throws Exception;
    public  AppLogin getLastApploginByEntity(AppLogin appLogin);
    

}
