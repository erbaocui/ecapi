package com.cn.service.impl;

import com.cn.constant.Qiniu;
import com.cn.service.IQiniuService;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("qiniuService")
public class QiniuService implements IQiniuService {
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	private String ACCESS_KEY = Qiniu.ACCESS_KEY;
	private String SECRET_KEY = Qiniu.SECRET_KEY;
	// 要上传的空间
	private String BUCKET_NAME = Qiniu.BUCKET_NAME;
	// 密钥配置
	private Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	// 创建上传对象
	private UploadManager uploadManager = new UploadManager();
	// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public String getUpToken() {
		return auth.uploadToken(BUCKET_NAME);
	}

	public void upload(byte[] localData,String remoteFileName) throws IOException {
		Response res = uploadManager.put(localData, remoteFileName, getUpToken());
		// 打印返回的信息
		System.out.println(res.bodyString());
	}

}
