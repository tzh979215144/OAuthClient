package com.hoperun.utils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

public class ReadProperties {
	public static String GetValueByKey(String filePath, String key) {
        Properties pps = new Properties();
        filePath = "/home/tanpang/eclipse-workspace/eclipse-workspace-2.0/oauthclient01/src/main/resources/properties/"+filePath;
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
//        System.out.println(new File(".").getAbsolutePath());
        try {
            InputStream in = new BufferedInputStream (new FileInputStream(filePath));  
            pps.load(in);
            String value = pps.getProperty(key);
            System.out.println(key + " = " + value);
            return value;
            
        }catch (IOException e) {
        	System.out.println("读文件出错了");
            e.printStackTrace();
            return null;
        }
    }
    
    //读取Properties的全部信息
    public static void GetAllProperties(String filePath) throws IOException {
        Properties pps = new Properties();
        filePath = "src/main/resources/properties/"+filePath;
        InputStream in = new BufferedInputStream(new FileInputStream(filePath));
        pps.load(in);
        Enumeration en = pps.propertyNames(); //得到配置文件的名字
        
        while(en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = pps.getProperty(strKey);
            System.out.println(strKey + "=" + strValue);
        }
        
    }
    
    //写入Properties信息
    public static void WriteProperties (String filePath, String pKey, String pValue) throws IOException {
Properties pps = new Properties();
        filePath = "src/main/resources/properties/"+filePath;
        InputStream in = new FileInputStream(filePath);
        //从输入流中读取属性列表（键和元素对） 
        pps.load(in);
        //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。  
        //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
        OutputStream out = new FileOutputStream(filePath);
        pps.setProperty(pKey, pValue);
        //以适合使用 load 方法加载到 Properties 表中的格式，  
        //将此 Properties 表中的属性列表（键和元素对）写入输出流  
        pps.store(out, "Update " + pKey + " name");
    }
    
    public static void test(String [] args) throws IOException{
        //String value = GetValueByKey("db.properties", "name");
        //System.out.println(value);
    	File file = new File("dbdd.properties");
    	WriteProperties("dbdd.properties","fsf", "2fdf12");
    	GetAllProperties("dbdd.properties");
    }
}
