package com.cn.service;


import java.io.IOException;


public interface IQiniuService  {

	public void upload(byte[] localData,String remoteFileName) throws IOException;

}
