package controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import play.cache.*;
/**
 * This Class is used to make an API call
 * @author Parth Parekh
 */
public class ApiCall {

	/**
	 * This method makes an API call
	 * @param url apiurl
	 * @param queryParamters contains query parameters to be added to the url
	 * @return future
	 */
	public static CompletionStage<String> getApiCall(String url, HashMap<String, String> queryParamters) {
		CompletableFuture<String> future = new CompletableFuture<>();
		//		JSONObject jsonObject = null;
		String responseBody = null;
		String authStr = "parth2347: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";
		String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());

		try {
			URIBuilder builder = new URIBuilder(url);
			for(Map.Entry<String, String> entry: queryParamters.entrySet()) {
				builder.addParameter(entry.getKey(),entry.getValue());
			}

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpResponse resp = null;

			HttpGet getAPI = new HttpGet(builder.build());
			getAPI.setHeader("Authorization", "Basic " + authEncoded);
			resp = httpclient.execute(getAPI);

			StatusLine statusLine = resp.getStatusLine();
			System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
			responseBody = EntityUtils.toString(resp.getEntity(), StandardCharsets.UTF_8);
			System.out.println(responseBody.length());
			
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		future.complete(responseBody);
		return future;
	}

}
