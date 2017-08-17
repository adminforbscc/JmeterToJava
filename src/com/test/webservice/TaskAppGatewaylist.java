package com.test.webservice;

import java.util.HashMap;
import java.util.Map;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import com.test.unit.AESEncryptor;
import com.test.unit.HttpRequest;
import com.test.unit.MD5util;
import com.test.unit.Map2Json;

public class TaskAppGatewaylist extends AbstractJavaSamplerClient {
    private String phone;
    private String password;
    private String baseurl;
    /** Holds the result data (shown as Response Data in the Tree display). */
    private String resultData;

    // 这个方法是用来自定义java方法入参的。
    // params.addArgument("num1","");表示入参名字叫num1，默认值为空。
    //设置可用参数及的默认值；
    @Override
	public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        
        params.addArgument("baseurl", "http://192.168.1.100:8080/");
        params.addArgument("phone", "17767188213");
        params.addArgument("password", "7ujMko0");																																																																								
        return params;
    }

    //每个线程测试前执行一次，做一些初始化工作；
    @Override
	public void setupTest(JavaSamplerContext arg0) {
    }

    //开始测试，从arg0参数可以获得参数值；
    @Override
	@SuppressWarnings("deprecation")
	public SampleResult runTest(JavaSamplerContext arg0) {
    	//此处需要修改（weishichao） 
        phone = arg0.getParameter("phone");
        password = arg0.getParameter("password");
        baseurl=arg0.getParameter("baseurl");
        
        //System.out.println(phone);
        //System.out.println(password);
        //System.out.println(baseurl);
        SampleResult sr = new SampleResult();
        sr.setSampleLabel( "Taskgatewaylist请求");
        try {
            
            
            //发送数据组装（修改）
            HashMap< String, String> paramap=new  HashMap< String, String>();
            paramap.put("phone", phone);
            MD5util temp=new MD5util();
            String md5passwd=temp.MD5(password);
            paramap.put("password", md5passwd);
            
            
			String datajson=Map2Json.mapToJson(paramap);
			String result;
			String encryptdata=AESEncryptor.encrypt(datajson);
			System.out.println(encryptdata);
			sr.sampleStart();// jmeter 开始统计响应时间标记
			//修改 http
			result=HttpRequest.sendPost(baseurl+"user/gateway_list", encryptdata);
			sr.sampleEnd();// jmeter 结束统计响应时间标记
			
  		    Map<String, String> resultmap=Map2Json.jsonTomap(AESEncryptor.decryptLocal(result));
  		    //System.out.println(resultmap);
            sr.setDataType(SampleResult.TEXT);
            if(resultmap.get("state").equals("success")){
            	sr.setSuccessful(true);
            	sr.setResponseData("param is "+paramap+"\nresult is "+result,null);
            	System.out.println(resultmap.get("state"));
            	//System.out.println("sss");
            }
            else{
            	sr.setSuccessful(false);
            	sr.setResponseData("param is "+paramap+"\nresult is "+result,null);
            	System.out.println(resultmap.get("state"));
            }
        } catch (Throwable e) {
            sr.setSuccessful(false);
            sr.setResponseData(e.toString(),null);
            e.printStackTrace();
            
            
        } finally {
            
        }
        return sr;
    }

    //测试结束时调用；
    @Override
	public void teardownTest(JavaSamplerContext arg0) {
        // System.out.println(end);
        // System.out.println("The cost is"+(end-start)/1000);
    }
    
    
    /**/
    public static void main(String[] args) 
    { // TODO Auto-generated method stub
		Arguments params = new Arguments(); 
		params.addArgument("baseurl", "http://192.168.1.100:8080/");
        params.addArgument("phone", "17767188213");
        params.addArgument("password", "7ujMko0");	
        
    	JavaSamplerContext arg0 = new JavaSamplerContext(params); 
    	TaskAppGatewaylist test = new TaskAppGatewaylist(); 
        test.setupTest(arg0); 
        test.runTest(arg0);
        test.teardownTest(arg0);
        while(true);
    }
    

}