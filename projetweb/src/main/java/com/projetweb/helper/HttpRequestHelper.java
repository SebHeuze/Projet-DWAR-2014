package com.projetweb.helper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLEncoder;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;



import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;


public class HttpRequestHelper {

	/**
	 * Logger
	 */
	private final static Logger LOG = Logger.getLogger(HttpRequestHelper.class.getName());
	
	public static Reader postHttpRequest(String url, Map<String, String> params){
		Reader reader = null;
		try {  
			NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();
			HttpRequestFactory httpRequestFactory = HTTP_TRANSPORT.createRequestFactory();
			HttpRequest req = httpRequestFactory.buildPostRequest(new GenericUrl(url), new UrlEncodedContent(params));
			
			req.setFollowRedirects(true);
			HttpResponse response = req.execute();
	            
	        
			if(response.getStatusCode() == 200) {
				InputStream content = response.getContent();
				//Read the server response and attempt to parse it as JSON
				reader = new InputStreamReader(content);
				
				
			} else {
				System.out.println(response.getStatusCode());
			}
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "HttpRequestHelper::postHttpRequest L'appel POST a échoué ",e);
		} 
		return reader;
	}
	
	public static Reader getHttpRequest(String url, Map<String, String> params){
		Reader reader = null;
		try {  
			NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();
			HttpRequestFactory httpRequestFactory = HTTP_TRANSPORT.createRequestFactory();
			url = appendQueryParams(url, params);
			LOG.info("HttpRequestHelper::getHttpRequest Appel GET "+ url);
			HttpRequest req = httpRequestFactory.buildGetRequest(new GenericUrl(url));
			
			req.setFollowRedirects(true);
			HttpResponse response = req.execute();
	            
	        
			if(response.getStatusCode() == 200) {
				InputStream content = response.getContent();
				//Read the server response and attempt to parse it as JSON
				reader = new InputStreamReader(content);
				
				
			} else {
				System.out.println(response.getStatusCode());
			}
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "HttpRequestHelper::getHttpRequest L'appel get a échoué ",e);
		} 
		return reader;
	}
	
	/**
	 * Append query parameters to given url
	 * @param url         Url as string
	 * @param params      Map with query parameters
	 * @return url        Url with query parameters appended
	 * @throws IOException 
	 */
	static public String appendQueryParams(String url, 
			Map<String, String> params) throws IOException {
		String fullUrl = new String(url);

		if (params != null) {
			boolean first = (fullUrl.indexOf('?') == -1);
			for (String param : params.keySet()) {
				if (first) {
					fullUrl += '?';
					first = false;
				}
				else {
					fullUrl += '&';
				}
				String value = params.get(param);
				fullUrl += URLEncoder.encode(param, "UTF-8") + '=';
				fullUrl += URLEncoder.encode(value, "UTF-8");
			}
		}

		return fullUrl;
	}
	
}
