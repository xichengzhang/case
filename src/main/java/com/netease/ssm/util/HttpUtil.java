package com.netease.ssm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	private static final int REQUEST_COUNT=3;
	
	/**
	  * 功能：检测当前URL是否可连接或是否有效,
	  * 描述：最多连接网络 3 次, 如果 3 次都不成功，视为该地址不可用
	  * @param  urlStr 指定URL网络地址
	  * @return URL
	  */
	
	 public static  String isConnect(String urlStr) {
		 int counts = 0;
		String retu = "";
		 if (urlStr == null || urlStr.length() <= 0) {                       
			 return null;                 
		 } 
		 while (counts < REQUEST_COUNT) {
			 long start = 0;
			 try {
				 URL url = new URL(urlStr);
				 start = System.currentTimeMillis();
				 HttpURLConnection con = (HttpURLConnection) url.openConnection();
				int state = con.getResponseCode();
//				System.out.println("请求断开的URL一次需要："+(System.currentTimeMillis()-start)+"毫秒");
				 if (state == 200) {
					 retu = "ok";
//					 System.out.println(urlStr+"--可用");
				 }
				 break;
			 }catch (Exception ex) {
				 counts++; 
//				 System.out.println("请求断开的URL一次需要："+(System.currentTimeMillis()-start)+"毫秒");
//				 System.out.println("连接第 "+counts+" 次，"+urlStr+"--不可用");
				 continue;
			 }
		 }
		 return retu;
	 }
	

	public static String httpget(String url, String charsetName) {
		try {
			URL u = new URL(url);
			StringBuffer sb = new StringBuffer();
			String rLine = null;
			URLConnection conn = u.openConnection();
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charsetName));

			char[] buffer = new char[1024];
			int offset = -1;
			while ((offset = br.read(buffer)) != -1) {
				sb.append(buffer, 0, offset);
			}

			br.close();
			return sb.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "[]";
	}

	public static String httpProxyGet(String url, String charsetName) {
		try {
			System.getProperties().setProperty("http.proxyHost", "113.108.225.182");
			System.getProperties().setProperty("http.proxyPort", "31298");
			URL u = new URL(url);
			StringBuffer sb = new StringBuffer();
			String rLine = null;
			URLConnection conn = u.openConnection();
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charsetName));

			char[] buffer = new char[1024];
			int offset = -1;
			while ((offset = br.read(buffer)) != -1) {
				sb.append(buffer, 0, offset);
			}

			br.close();
			return sb.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "[]";
	}

	public static String httpget(String url) {
		try {
			URL u = new URL(url);
			StringBuffer sb = new StringBuffer();
			String rLine = null;
			URLConnection conn = u.openConnection();
			conn.setConnectTimeout(90000);
			conn.setReadTimeout(90000);
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((rLine = br.readLine()) != null) {
				sb.append(rLine);
			}
			br.close();
			return sb.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "[]";
	}

	public static String httpProxyGet(String url) {
		String content = null;
		DefaultHttpClient httpclient = null;
		try {
			httpclient = new DefaultHttpClient();
			/** 设置代理IP **/
			HttpHost proxy = new HttpHost("113.108.225.182", 31298);
			httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);

			HttpGet httpget = new HttpGet(url);

			httpget.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,1000*30);  //设置请求超时时间
			httpget.setHeader("Proxy-Authorization","Basic eXVsb3JlOll1bG9yZVByb3h5MjAxMzo=");
			httpget.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.79 Safari/537.1");
			httpget.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

			HttpResponse responses = httpclient.execute(httpget);
			HttpEntity entity = responses.getEntity();
			content = EntityUtils.toString(entity);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();   //关闭连接
		}
		return content;
	}


	/**
	 * post 数据到指定地址，并获取返回结果.
	 * @throws IOException 
	 */
	public static String postContent(String url, Map<String, Object> param, String encoding) throws Exception {
		return postContent(url, param, encoding, 10000, false);
	}

	/**
	 * post 数据到指定地址，并获取返回结果.
	 * @throws IOException 
	 */
	public static String postContent(String url, Map<String, Object> param, String encoding, boolean isSSL)
			throws Exception {
		return postContent(url, param, encoding, 10000, isSSL);
	}

	/**
	 * post 数据到指定地址，并获取返回结果.
	 * @throws IOException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public static String postContent(String url, Map<String, Object> param, String encoding, int readTimeout,
			boolean isSSL) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		if (isSSL) {
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
						throws CertificateException {
				}

				public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
						throws CertificateException {
				}
			} }, new SecureRandom());
			SSLSocketFactory sf = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			Scheme httpScheme = new Scheme("http", 80, PlainSocketFactory.getSocketFactory());
			Scheme httpsScheme = new Scheme("https", 443, sf);
			httpClient = new DefaultHttpClient();
			httpClient.getConnectionManager().getSchemeRegistry().register(httpScheme);
			httpClient.getConnectionManager().getSchemeRegistry().register(httpsScheme);
		}
		// 重试
		httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(3, false));
		//超时
		httpClient.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, 10000);
		httpClient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, readTimeout);
		HttpPost httppost = new HttpPost(url);
		try {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (String key : param.keySet()) {
				nvps.add(new BasicNameValuePair(key, String.valueOf(param.get(key))));
			}
			httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");
			httppost.setHeader("Referer", url);
			httppost.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
			httppost.setEntity(new UrlEncodedFormEntity(nvps, encoding));
			HttpResponse response = httpClient.execute(httppost);
			return EntityUtils.toString(response.getEntity(), encoding);
		} catch (IOException e) {
			throw e;
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}

	public static int exists(String URLName) {
		try {
			//设置此类是否应该自动执行 HTTP 重定向（响应代码为 3xx 的请求）。
			HttpURLConnection.setFollowRedirects(false);
			//到 URL 所引用的远程对象的连接
			HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
			/* 设置 URL 请求的方法， GET POST HEAD OPTIONS PUT DELETE TRACE 以上方法之一是合法的，具体取决于协议的限制。*/
			con.setRequestMethod("HEAD");
			//从 HTTP 响应消息获取状态码
			//return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
			return (con.getResponseCode());
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
	public static String sentGet(String url) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String result = "";
		try {
//			HttpHost targetHost = new HttpHost("www.baidu.com");
			// 创建get连接
			HttpGet httpget = new HttpGet(url);
			// 设置超时时间
			//HttpHost proxy = new HttpHost("218.107.55.138", 31298);
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(200000).setConnectTimeout(1000 * 10)
					.build();// 设置请求和传输超时时间
			httpget.setConfig(requestConfig);
			httpget.setHeader("Accept-Charset",
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2");
			// 发送请求
			CloseableHttpResponse response = httpclient.execute(httpget);
/*			System.out.println("URI：" + httpget.getURI());
			// 获取状态码
			System.out.println("状态码："
					+ response.getStatusLine().getStatusCode());
			System.out.println("头部信息："
					+ httpget.getFirstHeader("Accept-Charset").getValue());
*/
			// BufferedReader reader= new BufferedReader(new
			// InputStreamReader(response.get));

			// 获取所有的请求头信息
			/*Header headers[] = response.getAllHeaders();
			int ii = 0;
			while (ii < headers.length) {
				System.out.println(headers[ii].getName() + ":"
						+ headers[ii].getValue());
				++ii;
			}*/
			// 抓取网页内容
			HttpEntity entity = response.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					entity.getContent(), "UTF-8"));
			String line;
			while ((line = br.readLine()) != null) {
				result += line;
			}

			br.close();

			// 终止请求
			httpget.abort();
		} catch (ClientProtocolException e) {
			System.out.println("ClientProtocolException ==" + e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException ==" + e);
			e.printStackTrace();
		} finally {
			// 终止请求
			httpclient.close();
		}
		return result;
	}
	
	public static String sentGetForToutiao(String url) throws Exception {
		// 创建HttpClientBuilder  
//		String[] res = getRandomIp();
//		Random r = new Random();
//		String ips = res[r.nextInt(res.length)];
//		System.out.println(ips);
//		String[] ip = ips.split(":");
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
        // HttpClient  
        CloseableHttpClient httpclient = httpClientBuilder.build();  
//        HttpHost proxy = new HttpHost(ip[0], Integer.parseInt(ip[1]));
        HttpHost proxy = new HttpHost("220.181.29.165", 8268);
        //RequestConfig config = RequestConfig.custom().setProxy(proxy).build(); 
		String result = "";
		try {
			// 创建get连接
			HttpGet httpget = new HttpGet(url);
			// 设置超时时间
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(200000).setConnectTimeout(1000 * 10).setProxy(proxy)
					.build();// 设置请求和传输超时时间
			httpget.setConfig(requestConfig);
			httpget.setHeader("Accept-Charset",
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2");
			// 发送请求
			CloseableHttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					entity.getContent(), "UTF-8"));
			String line;
			while ((line = br.readLine()) != null) {
				result += line;
			}

			br.close();

			// 终止请求
			httpget.abort();
		} catch (ClientProtocolException e) {
			System.out.println("ClientProtocolException ==" + e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException ==" + e);
			e.printStackTrace();
		} finally {
			// 终止请求
			httpclient.close();
		}
		return result;
	}
	
	public static String sentGet(String url,String token) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String result = "";
		try {
//			HttpHost targetHost = new HttpHost("www.baidu.com");
			// 创建get连接
			HttpGet httpget = new HttpGet(url);
			// 设置超时时间
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(200000).setConnectTimeout(1000 * 10)
					.build();// 设置请求和传输超时时间
			httpget.setConfig(requestConfig);
			httpget.setHeader("Accept-Charset",
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2");
			httpget.setHeader("token", token);    
			// 发送请求
			CloseableHttpResponse response = httpclient.execute(httpget);
/*			System.out.println("URI：" + httpget.getURI());
			// 获取状态码
			System.out.println("状态码："
					+ response.getStatusLine().getStatusCode());
			System.out.println("头部信息："
					+ httpget.getFirstHeader("Accept-Charset").getValue());
*/
			// BufferedReader reader= new BufferedReader(new
			// InputStreamReader(response.get));

			// 获取所有的请求头信息
			/*Header headers[] = response.getAllHeaders();
			int ii = 0;
			while (ii < headers.length) {
				System.out.println(headers[ii].getName() + ":"
						+ headers[ii].getValue());
				++ii;
			}*/
			// 抓取网页内容
			HttpEntity entity = response.getEntity();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					entity.getContent(), "UTF-8"));
			String line;
			while ((line = br.readLine()) != null) {
				result += line;
			}

			br.close();

			// 终止请求
			httpget.abort();
		} catch (ClientProtocolException e) {
			System.out.println("ClientProtocolException ==" + e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException ==" + e);
			e.printStackTrace();
		} finally {
			// 终止请求
			httpclient.close();
		}
		return result;
	}
	
	
	 public static void main(String[] args)  {
		 try {
			 HttpUtil aa = new HttpUtil();
			//System.out.println(aa.getRandomIp());
			String bb = "/home/app/workspace/videolib2_release/release-current/dist/webhtml/image/snapshot/2016/12/E/H/VC7DBBMEH";
			System.out.println(bb.substring(bb.indexOf("/image")));
			//System.out.println(aa.sentGetForToutiao("http://m.toutiao.com/pgc/ma/?media_id=6102033587&page_type=1&max_behot_time=0&count=10&version=2&platform=pc&as=A18528A4AA5694A&cp=584A5669342AFE1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	    }

	    public static String sendGet (String url,String param,String charsetName) {
	        String result="";
	        BufferedReader in = null;
	        try {
	            String urlNameString;
	            if(!param.equals("")) {
	                urlNameString = url + "?" + param;
	            }else {
	                urlNameString = url;
	            }

	            URL realUrl = new URL(urlNameString);
	            //打开连接
	            URLConnection connection = realUrl.openConnection();
	            //设置通用的请求属性
	            connection.setRequestProperty("Host", "toutiao.com");
	            connection.setRequestProperty("accept","*/*");
	            connection.setRequestProperty("connection","Keep-Alive");
	            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

	            //建立实际的连接
	            connection.connect();
	            //获取所有响应头字段
	            Map map = connection.getHeaderFields();
	            //遍历响应头字段
//	            for (String key : map.keySet()){
//	                System.out.println(key + "--->" + map.get(key));
//	            }
	            //定义 bufferedReader输入流来读取URL的响应
	            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charsetName));
	            String line;
	            while ((line = in.readLine())!=null) {
	                result += line;
	            }

	        }catch (Exception e) {
	            System.out.println("发送Get请求出现异常!" + e);
	            e.printStackTrace();
	        }
	        finally {
	            try {
	                if(in != null) {
	                    in.close();
	                }
	            }catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        }
	        return result;
	    }
	    
		public static String getToken() {
			String token="";
			final Map mmm = new HashMap();
			mmm.put("username", "media_video");
			mmm.put("password", "f3Cdf2p");
			try {
				String resultJson = HttpUtil.postContent("http://api.n.netease.com/ip/getUserToken",mmm,"UTF-8");
				System.out.println("resultJson       "+resultJson);
			   	 JSONObject jsonObject = JSONObject.fromObject(resultJson);
			   	 String result = (String) jsonObject.get("result");
			   	 System.out.println("rtrtrt                "+result);
			   	if ("success".equals(result)) {
			   		JSONObject data = jsonObject.getJSONObject("data");
			   		Map<String, Object> mapNext = JSONObject.fromObject(data);
			   		token = (String) mapNext.get("token");
			   	}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return token;
		}
		
		public static String[] getRandomIp(){
			String[] set = new String[10];
			try {
			 String resultJson = HttpUtil.sentGet("http://api.n.netease.com/ip/proxy?type=private&quality=2",getToken());
			 System.out.println(resultJson);
		   	 JSONObject jsonObject = JSONObject.fromObject(resultJson);
		   	 String result = (String) jsonObject.get("result");
		   	 System.out.println(result);
		   	 if ("success".equals(result)) {
		   		 
		   		 JSONObject data = jsonObject.getJSONObject("data");
		   		 JSONArray array = data.getJSONArray("proxy_list");
		   		 List<Map<String, Object>> mapListJson = (List) array;
		   		 for (int i = 0; i < mapListJson.size(); i++) {
		   			 Map<String, Object> obj = mapListJson.get(i);
		   			 String ip = (String) obj.get("ip");
		   			 System.out.println(ip);
		   			 set[i]=ip;
		   		 }
		   	 }
			}catch (Exception e) {
				e.printStackTrace();
			}
			return set;
		}
		
}
