package anthropaedic.messenger;

import java.io.IOException;

import com.google.gson.JsonObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FacebookManager
{
    public FacebookManager()  
    {
        String webhookUrl = "your slack webhook incoming url";

        OkHttpClient client = new OkHttpClient();
        JsonObject json = new JsonObject();

        json.addProperty("text", "hi from test case\nnext line from test case");

        Request request = new Request.Builder()
                .url(webhookUrl)
                .post(RequestBody.create(MediaType.parse("application/javascript; charset=utf-8"), json.toString()))
                .build();

	try
	{
	    Response response = client.newCall(request).execute();
	    if (!response.isSuccessful()) 
	    {
		throw new IOException("Unexpected code " + response);
	    }
	    
	    System.out.println(response.body().string());
	} 
	catch (IOException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
