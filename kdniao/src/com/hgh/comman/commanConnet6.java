package com.hgh.comman;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class commanConnet6 {
	public String postForm(String url,String data) throws UnsupportedEncodingException, Exception {
        // 创建默认的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        // 创建httppost    
        HttpPost httppost = new HttpPost(url);  
        // 创建参数队列    
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
        formparams.add(new BasicNameValuePair("RequestData", data));
        formparams.add(new BasicNameValuePair("EBusinessID","1282148" ));
        formparams.add(new BasicNameValuePair("RequestType", "1008"));
        formparams.add(new BasicNameValuePair("DataSign",encrypt(data, "835f42ca-32a0-40cc-9c2d-5337dd14c4f9", "UTF-8")));
        formparams.add(new BasicNameValuePair("DataType", "2"));
        UrlEncodedFormEntity uefEntity;  
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) { 
                	String content=EntityUtils.toString(entity, "UTF-8");
                    return content;
                }
            } finally {
                response.close();
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return null;
    }
	
	/**
     * 电商Sign签名生成
     * @param content 内容   
     * @param keyValue Appkey  
     * @param charset 编码方式
	 * @throws UnsupportedEncodingException ,Exception
	 * @return DataSign签名
     */
	@SuppressWarnings("unused")
	private String encrypt (String content, String keyValue, String charset) throws UnsupportedEncodingException, Exception
	{
		if (keyValue != null)
		{
			return base64(MD5(content + keyValue, charset), charset);
		}
		return base64(MD5(content, charset), charset);
	}
	/**
     * MD5加密
     * @param str 内容       
     * @param charset 编码方式
	 * @throws Exception 
     */
	@SuppressWarnings("unused")
	private String MD5(String str, String charset) throws Exception {
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(str.getBytes(charset));
	    byte[] result = md.digest();
	    StringBuffer sb = new StringBuffer(32);
	    for (int i = 0; i < result.length; i++) {
	        int val = result[i] & 0xff;
	        if (val <= 0xf) {
	            sb.append("0");
	        }
	        sb.append(Integer.toHexString(val));
	    }
	    return sb.toString().toLowerCase();
	}
	
	/**
     * base64编码
     * @param str 内容       
     * @param charset 编码方式
	 * @throws UnsupportedEncodingException 
     */
	private String base64(String str, String charset) throws UnsupportedEncodingException{
		String encoded = base64Encode(str.getBytes(charset));
		return encoded;    
	}	
	
	@SuppressWarnings("unused")
	private String urlEncoder(String str, String charset) throws UnsupportedEncodingException{
		String result = URLEncoder.encode(str, charset);
		return result;
	}
	 private static char[] base64EncodeChars = new char[] { 
	            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 
	            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 
	            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
	            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 
	            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
	            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
	            'w', 'x', 'y', 'z', '0', '1', '2', '3', 
	            '4', '5', '6', '7', '8', '9', '+', '/' }; 
	    	
	    public static String base64Encode(byte[] data) { 
	        StringBuffer sb = new StringBuffer(); 
	        int len = data.length; 
	        int i = 0; 
	        int b1, b2, b3; 
	        while (i < len) { 
	            b1 = data[i++] & 0xff; 
	            if (i == len) 
	            { 
	                sb.append(base64EncodeChars[b1 >>> 2]); 
	                sb.append(base64EncodeChars[(b1 & 0x3) << 4]); 
	                sb.append("=="); 
	                break; 
	            } 
	            b2 = data[i++] & 0xff; 
	            if (i == len) 
	            { 
	                sb.append(base64EncodeChars[b1 >>> 2]); 
	                sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]); 
	                sb.append(base64EncodeChars[(b2 & 0x0f) << 2]); 
	                sb.append("="); 
	                break; 
	            } 
	            b3 = data[i++] & 0xff; 
	            sb.append(base64EncodeChars[b1 >>> 2]); 
	            sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]); 
	            sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]); 
	            sb.append(base64EncodeChars[b3 & 0x3f]); 
	        } 
	        return sb.toString(); 
	    }
}
