package com.terabits.wapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.terabits.utils.AESUtil;

/** 
* @author 作者Vladimir E-mail: gyang.shines@gmail.com
* @version 创建时间：2017年12月25日 上午10:18:50 
* 类说明 
*/
public class HttpRequestWapper extends HttpServletRequestWrapper {

	public HttpRequestWapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	private String key;
	@Override
	public String[] getParameterValues(String name) {
		String[] strs=super.getParameterValues(name);
		System.out.println();
		if(name.equals("id")){
			for(int i=0;i<strs.length;i++)
			{
				System.out.println(key+"******************************************************");
				System.out.println(strs[i]+"******************************************************");
				byte[] Message = AESUtil.hex2byte(strs[i]);
				System.out.println(strs[i]+"******************************************************");
				byte[] decodeMessage = AESUtil.AESJDKDecode(Message, key);
				System.out.println(key+"******************************************************");
				strs[i]=new String(decodeMessage);//AES解密
				System.out.println("****************"+strs[i]+"******************************************************");
			}
		}
		return strs;
	}
	public void setKey(String key) {
		this.key = key;
	}

}
