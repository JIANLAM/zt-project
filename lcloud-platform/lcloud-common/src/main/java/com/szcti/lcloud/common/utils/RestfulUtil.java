package com.szcti.lcloud.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

import org.apache.http.util.TextUtils;

public class RestfulUtil {

	public static String get(String urlParam){
		//读取请求返回值
		String result=null;
		byte bytes[]= new byte[1024];
		try {
			URL url = new URL(urlParam);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 提交模式
			conn.setRequestMethod("GET");// POST GET PUT DELETE
			conn.setRequestProperty("Authorization", "Basic YWRtaW46YWRtaW4=");//YWRtaW46YWRtaW4=");
			// 设置访问提交模式，表单提交
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setConnectTimeout(15000);// 连接超时 单位毫秒
			conn.setReadTimeout(15000);// 读取超时 单位毫秒      
			//System.out.println("----getResponseCode----"+conn.getResponseCode());
			if(HttpURLConnection.HTTP_ACCEPTED==conn.getResponseCode() || conn.getResponseCode()==HttpURLConnection.HTTP_OK || conn.getResponseCode()==HttpURLConnection.HTTP_CREATED) {
				InputStream inStream=conn.getInputStream();
				inStream.read(bytes, 0, inStream.available());
			}else {
				InputStream inStream=conn.getErrorStream();
				inStream.read(bytes, 0, inStream.available());
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			result=new String(bytes, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void uploadInformation(String  path,String obj){
        //创建连接
        URL url = null;
        HttpURLConnection connection ;
        StringBuffer sbuffer=null;
        try {
        	url= new URL(path);
	    //添加 请求内容
            connection= (HttpURLConnection) url.openConnection();
            //设置http连接属性
            connection.setDoOutput(true);// http正文内，因此需要设为true, 默认情况下是false;
            connection.setDoInput(true);// 设置是否从httpUrlConnection读入，默认情况下是true;
            connection.setRequestMethod("PUT"); // 可以根据需要 提交 GET、POST、DELETE、PUT等http提供的功能
            //connection.setUseCaches(false);//设置缓存，注意设置请求方法为post不能用缓存
            // connection.setInstanceFollowRedirects(true);

            connection.setRequestProperty("Host", "*******");  //设置请 求的服务器网址，域名，例如***.**.***.***
            connection.setRequestProperty("Content-Type", " application/json");//设定 请求格式 json，也可以设定xml格式的
            connection.setRequestProperty("Accept-Charset", "utf-8");  //设置编码语言
            connection.setRequestProperty("X-Auth-Token", "token");  //设置请求的token
            connection.setRequestProperty("Connection", "keep-alive");  //设置连接的状态
            connection.setRequestProperty("Transfer-Encoding", "chunked");//设置传输编码
            connection.setRequestProperty("Content-Length", obj.toString().getBytes().length + ""); //设置文件请求的长度  
            connection.setReadTimeout(10000);//设置读取超时时间          
            connection.setConnectTimeout(10000);//设置连接超时时间           
            connection.connect();            
            OutputStream out = connection.getOutputStream();//向对象输出流写出数据，这些数据将存到内存缓冲区中          
            out.write(obj.toString().getBytes());            //out.write(new String("测试数据").getBytes());            //刷新对象输出流，将任何字节都写入潜在的流中       
            out.flush();     
            // 关闭流对象,此时，不能再向对象输出流写入任何数据，先前写入的数据存在于内存缓冲区中          
            out.close();           
            //读取响应           
            if (connection.getResponseCode()==200)            {
                // 从服务器获得一个输入流
        InputStreamReader inputStream =new InputStreamReader(connection.getInputStream());//调用HttpURLConnection连接对象的getInputStream()函数, 将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。
        BufferedReader reader = new BufferedReader(inputStream);  
		String lines;                
		sbuffer= new StringBuffer("");  
  		while ((lines = reader.readLine()) != null) {                
			lines = new String(lines.getBytes(), "utf-8");                    
			sbuffer.append(lines);                }                
			reader.close();         
 	   }else{          
		        System.out.println("请求失败"+connection.getResponseCode());    
        	}    
		//断开连接           
 		connection.disconnect();    
 	} catch (IOException e) {  
         	 e.printStackTrace();     
     }   
 }
	
	public static String doJsonPost(String urlPath, String Json) {
        String result = "";
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            // 设置接收类型否则返回415错误
            //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
          conn.setRequestProperty("accept","application/json");
            // 往服务器里面发送数据
            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 设置文件长度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                //System.out.println("doJsonPost: conn"+conn.getResponseCode());
            }
            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                result = reader.readLine();
            }else {
            	
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
	
	private static final String SERVLET_DELETE = "DELETE" ;
	public static void doDelete(String urlStr,Map<String,Object> paramMap){  
        try {
        	if(paramMap!=null) {
			String paramStr = prepareParam(paramMap);  
			if(paramStr == null || paramStr.trim().length()<1){  
			      
			}else{  
			    urlStr +="?"+paramStr;  
			}     
        	}
			//System.out.println(urlStr);  
			URL url = new URL(urlStr);  
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
			conn.setDoOutput(true);  
			conn.setRequestMethod(SERVLET_DELETE);  
			//屏蔽掉的代码是错误的，java.net.ProtocolException: HTTP method DELETE doesn't support output  
/*      OutputStream os = conn.getOutputStream();      
			os.write(paramStr.toString().getBytes("utf-8"));      
			os.close();  */   
			  
			if(conn.getResponseCode() ==200){  
			    System.out.println("成功");  
			}else{  
			    System.out.println(conn.getResponseCode());  
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    }  
	
	private static String prepareParam(Map<String,Object> paramMap){  
        StringBuffer sb = new StringBuffer();  
        if(paramMap.isEmpty()){  
            return "" ;  
        }else{  
            for(String key: paramMap.keySet()){  
                String value = (String)paramMap.get(key);  
                if(sb.length()<1){  
                    sb.append(key).append("=").append(value);  
                }else{  
                    sb.append("&").append(key).append("=").append(value);  
                }  
            }  
            return sb.toString();  
        }  
    }  
}
