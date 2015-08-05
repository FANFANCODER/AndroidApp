package cn.edu.uestc.bbs.qshp.net;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by feifei on 15-8-4.
 *
 * 这个类执行httpGet和httpPost的请求
 */
public class CommonHttpRequest {


    /**
     * 处理get请求，需要两个参数，第一个是请求url，第二个是请求参数的map
     */
    public static CommonReturn doGet(String url, Map paras) {
        //初始化结果，设置status为false,message和data都为空
        CommonReturn result = new CommonReturn();
        //google建议使用URLConnection，但是还是觉得这个比较好用
        HttpClient client = new DefaultHttpClient();
        //使用请求url和参数拼凑成最终请求的字符串
        HttpGet httpGet = new HttpGet(url +getUrl(url,paras));
        HttpParams params=client.getParams();
        //设置ｈｔｔｐ连接超时时间
        HttpConnectionParams.setConnectionTimeout(params, 3000);
        //设置ｓｏｃｋｅｔl连接超时时间
        HttpConnectionParams.setSoTimeout(params,3000);
        try {
            HttpResponse response=client.execute(httpGet);
            if (response.getStatusLine().getStatusCode()==200)
            {
                //首先将返回结果写入字符传里面
                result.setData(EntityUtils.toString(response.getEntity()));
                result.setStatus(true);
            }
            else {
                result.setMessage("网络连接出错,ERRORCODE:"+response.getStatusLine().getStatusCode());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            result.setMessage("网络连接出错");
        } catch (IOException e) {
            e.printStackTrace();
            result.setMessage("网络连接出错");
        }
        return result;
    }

    /**
     * 处理post请求，需要两个参数，第一个是请求url，第二个是请求参数的map
     */

    public static CommonReturn doPost(String url, Map<String,String> paras) {
        //初始化结果，默认构造函数设置status为false,message和data为空字符串
        CommonReturn result = new CommonReturn();
        //使用httpclient处理请求
        HttpClient client=new DefaultHttpClient();
        //设置连接超时时间
        HttpParams params=client.getParams();
        HttpConnectionParams.setSoTimeout(params,3000);
        HttpConnectionParams.setConnectionTimeout(params,3000);
        HttpPost post=new HttpPost(url);
        List<NameValuePair> pairs=new ArrayList<NameValuePair>();
        //使用basicnamevaluepair组织参数
        for (String name:paras.keySet())
        {
            pairs.add(new BasicNameValuePair(name,paras.get(name)));
        }
        try {
            //将参数添加到HttpPost实例中
            post.setEntity(new UrlEncodedFormEntity(pairs,"utf-8"));
            HttpResponse response=client.execute(post);
            if (response.getStatusLine().getStatusCode()==200)
            {
                result.setData(EntityUtils.toString(response.getEntity()));
                result.setStatus(true);
            }
            else
            {
                result.setMessage("网络连接出错");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            result.setMessage("网络连接出错");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            result.setMessage("网络连接出错");
        } catch (IOException e) {
            e.printStackTrace();
            result.setMessage("网络连接出错");
        }
        return result;
    }

    //httpget请求所使用的url合成方法，使用请求的uri和参数做为合成参数
    private static String getUrl(String url,Map<String,String> paras)
    {
        StringBuilder sb=new StringBuilder(url);
        sb.append("?");
        for (String name: paras.keySet())
        {
            sb.append(name);
            sb.append("=");
            sb.append(paras.get(name));
            sb.append("&");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
