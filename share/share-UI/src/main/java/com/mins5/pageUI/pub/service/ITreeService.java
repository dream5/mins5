package com.mins5.pageUI.pub.service;

import com.mins5.pageUI.entity.TreeData;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public abstract interface ITreeService
{
  public abstract TreeData getRootTreeData();

  public abstract List<TreeData> getSnsyc(String paramString, HttpServletRequest paramHttpServletRequest);
}

/* Location:           E:\server\apache-tomcat-6.0.29_end\IDP_2\WEB-INF\lib\DAPUI-1.2.0.jar
 * Qualified Name:     com.cattsoft.dapui.pub.service.ITreeService
 * JD-Core Version:    0.6.2
 */