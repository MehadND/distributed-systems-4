package ModuleClient;

import java.net.URI;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import Module.Module;

public class ModuleClient {
	public static void main(String[] args) throws Exception {
		
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;

			URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080).setPath("/j3moduleServer/rest/module").build();
			
			System.out.println(uri.toString());
			
			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader("Accept", "application/xml");
			
			httpClient = HttpClients.createDefault();
			httpResponse = httpClient.execute(httpGet);
			
			String text;
			
			HttpEntity entity = httpResponse.getEntity();
			
			text = EntityUtils.toString(entity);
			//System.out.println(text);
			
			ParseModule moduleParser = null;
			List<Module> modulesList = new ParseModule().startParsing(text);
			
			System.out.println("------------------------------------");
			
			for(Module module : modulesList)
			{
				System.out.println("ID: "+module.getId());
				System.out.println("Name: "+module.getName());
				System.out.println("Lecturer: "+module.getLecturer());
				System.out.println("Hours Per Week: "+module.getHoursPerWeek());
				System.out.println("------------------------------------");
			}
			
			//System.out.println(inputStream);
			
		
		
	}
}
