package no.hvl.dat110.aciotdevice.client;

import java.io.IOException;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestClient {


	private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	public RestClient() {
		// TODO Auto-generated constructor stub
	}

	private static String logpath = "/accessdevice/log";

	public void doPostAccessEntry(String message) {

		// TODO: implement a HTTP POST on the service to post the message
		Gson gson = new Gson();
		String json = gson.toJson(new AccessMessage(message));
		
		OkHttpClient client = new OkHttpClient();
		
		RequestBody reqBody = RequestBody.create(JSON, json);
		
		
		Request req = new Request.Builder()
				.url("http://localhost:8080" + logpath)
				.post(reqBody)
				.build();
		
		System.out.println(req);
		try {
			Response response = client.newCall(req).execute();
			System.out.println(response.body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String codepath = "/accessdevice/code";
	
	public AccessCode doGetAccessCode() {

		AccessCode code = null;
		
		// TODO: implement a HTTP GET on the service to get current access code
		
		OkHttpClient client = new OkHttpClient();
		Gson gson = new Gson();
		Request request = new Request.Builder()
				.url("http://localhost:8080" + codepath)
				.get()
				.build();
		
		System.out.println(request.toString());
		
		try {
			Response response = client.newCall(request).execute();
			String responseString = response.body().string();
			System.out.println(responseString);
			code = gson.fromJson(responseString, AccessCode.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return code;
	}
}
