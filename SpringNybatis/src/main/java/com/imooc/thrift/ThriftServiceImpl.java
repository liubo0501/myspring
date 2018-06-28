package com.imooc.thrift;

/********************************************************************************
 ** Copyright(c) 2014-2017 湖南万为智能机器人技术有限公司 All Rights Reserved.
 ** 作者：yiwenjie@anbot.cn 日期：2017/5/18 描述：Thrift的入口函数 版本：v1.0
 *********************************************************************************/

public class ThriftServiceImpl implements ThriftService.Iface {

	public String httpRequest(String data) {
		String result = "hello world";
		return result;
	}
}