package com.projet.helper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;

public class HttpRequestHelper {

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
			e.printStackTrace();
		} 
		return reader;
	}
}
