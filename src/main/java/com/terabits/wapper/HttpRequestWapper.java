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
//		改写Http Request的方法就是调用其父类HttpServletRequestWrapper的构造方法
//		在父类的构造方法中，会调用getParameter,getParameterValues等方法，这里重载了getParameterValues方法
//		在重载的getParameterValues方法中，执行对参数的解密操作
//		具体执行父类的构造方法时，会循环调用getParameterValues方法，因而可以对参数逐个解密，当然也可以在getParameterValues方法设置一些条件，如下所示，只解密部分参数
	}
	private String key;
	
//	这个方法和getParameter方法类似，该方法返回一个包含给定参数名称的所有值的String数组。它主要用于获取多选列表框的值。
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
				byte[] decodeMessage = AESUtil.AESJDKDecode(Message, key);
				strs[i]=new String(decodeMessage);//AES解密
				System.out.println(strs[i]+"******************************************************");
			}
		}
		if(name.equals("token")){
			for(int i=0;i<strs.length;i++)
			{
				System.out.println(key+"******************************************************");
				System.out.println(strs[i]+"******************************************************");
				byte[] Message = AESUtil.hex2byte(strs[i]);
				byte[] decodeMessage = AESUtil.AESJDKDecode(Message, key);
				strs[i]=new String(decodeMessage);//AES解密
				System.out.println(strs[i]+"******************************************************");
			}
		}
		return strs;
	}
	public void setKey(String key) {
		this.key = key;
	}

}
