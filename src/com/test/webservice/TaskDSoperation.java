package com.test.webservice;


import java.util.HashMap;
import java.util.Map;

import org.apache.http.protocol.HTTP;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.regexp.REDebugCompiler;

import com.test.unit.MD5util;
import com.test.constant.TestConstants;
import com.test.unit.*;

public class TaskDSoperation  extends AbstractJavaSamplerClient {
    private String guuid;
    private String baseurl;
    /** Holds the result data (shown as Response Data in the Tree display). */
    private String resultData;

    // 这个方法是用来自定义java方法入参的。
    // params.addArgument("num1","");表示入参名字叫num1，默认值为空。
    //设置可用参数及的默认值；
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        
        params.addArgument("baseurl", "http://120.55.182.40:8080/");
        params.addArgument("guuid", "cc1bb9dd14754600a896ef16c0987c71");
        																																																																							
        return params;
    }

    //每个线程测试前执行一次，做一些初始化工作；
    public void setupTest(JavaSamplerContext arg0) {
    }

    //开始测试，从arg0参数可以获得参数值；
    @SuppressWarnings("deprecation")
	public SampleResult runTest(JavaSamplerContext arg0) {
    	//此处需要修改（weishichao） 
        
        baseurl=arg0.getParameter("baseurl");
        guuid=arg0.getParameter("guuid");
        
        //System.out.println(phone);
        //System.out.println(password);
        //System.out.println(baseurl);
        SampleResult sr = new SampleResult();
        sr.setSampleLabel( "TaskDS请求操作");
        try {
            
            
            //发送数据组装（修改）
            
            
            
			String guuidAES=guuid;
			//System.out.println(guuidAES);
			//修改 http
			sr.sampleStart();// jmeter 开始统计响应时间标记
			String result=HttpRequest.sendGet(baseurl+"DS/gateway/operation","guuid="+guuidAES);
			sr.sampleEnd();// jmeter 结束统计响应时间标记
			//System.out.println("result is"+result);
			
            sr.setDataType(SampleResult.TEXT);
            if(!result.isEmpty()){
            	sr.setSuccessful(true);
            	sr.setResponseData("param is "+guuid+"\nresult is "+result,null);
            }
            else{
            	sr.setSuccessful(false);
            	System.out.println("failed");
            	sr.setResponseData("param is "+guuid+"\nresult is "+result,null);
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
    public void teardownTest(JavaSamplerContext arg0) {
        // System.out.println(end);
        // System.out.println("The cost is"+(end-start)/1000);
    }
    
    
    /* 
   public static void main(String[] args) 
    { // TODO Auto-generated method stub
		Arguments params = new Arguments(); 
		
        params.addArgument("baseurl", "http://120.55.182.40:8080/");	
        params.addArgument("guuid", "cc1bb9dd14754600a896ef16c0987c71");	
        
    	JavaSamplerContext arg0 = new JavaSamplerContext(params); 
    	TaskDSoperation test = new TaskDSoperation(); 
        test.setupTest(arg0); 
        test.runTest(arg0);
        test.teardownTest(arg0); 
    }
   */

}

