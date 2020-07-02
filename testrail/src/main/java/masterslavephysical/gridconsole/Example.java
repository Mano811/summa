package masterslavephysical.gridconsole;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Example {
	static String jira_attachment_baseURL = "https://ustqualityengineering.atlassian.net/rest";


	public static void main(String[] args) {
		
		
		try {
			boolean isPassed = addAttachmentToIssue("STEP-58", "D:\\sample_Images\\jira-zephyr.png");
			System.out.println(isPassed);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static boolean addAttachmentToIssue(String issueKey, String fullfilename) throws IOException{

		CloseableHttpClient httpclient = HttpClients.createDefault();
	//	System.setProperty("http.proxyHost", "http://pac.zscaler.net/ust-global.com/ust.pac");
        //System.setProperty("http.proxyPort", "9400");

		HttpPost httppost = new HttpPost("https://ustglobal.zephyrdemo.com/flex/upload/defect/attachment?dtsId=1");
		//httppost.setHeader("X-Atlassian-Token", "nocheck");
		httppost.setHeader("Authorization", "Basic QWRtaW4uTWFuYWdlcjpDb21wYW55XzM=");
		httppost.setHeader("Accept","application/json");
		httppost.setHeader("Content-Type","multipart/form-data");
		File fileToUpload = new File(fullfilename);
		FileBody fileBody = new FileBody(fileToUpload);
		
		HttpEntity entity = MultipartEntityBuilder.create()
				.addPart("file", fileBody)
				.build();
		
		httppost.setEntity(entity);
        String mess = "executing request " + httppost.getRequestLine();
       // System.clearProperty("http.proxyHost");
        CloseableHttpResponse response;
		
        try {
			response = httpclient.execute(httppost);
		} finally {
			httpclient.close();
		}
        
		if(response.getStatusLine().getStatusCode() == 200)
			return true;
		else
			return false;

	}
	
	public boolean getAttachmentFromIssue(String contentURI, String fullfilename) throws IOException {
        
		CloseableHttpClient httpclient = HttpClients.createDefault();
        
        try {
            HttpPost httpget = new HttpPost(contentURI);
           // httpget.setHeader("X-Atlassian-Token", "nocheck");
            httpget.setHeader("Authorization", "Basic "+"QW52YXIuS2FubmFtcGFyYW1iaWxAdXN0LWdsb2JhbC5jb21AdXN0LWdsb2JhbC5jb206c1BQVXhXNnZpQlQ2eGJpSHVOemhGRjIz");
            httpget.setHeader("Content-Type", "multipart/form-data");
            System.out.println("executing request " + httpget.getURI());

            CloseableHttpResponse response = httpclient.execute(httpget);
            
            int status = response.getStatusLine().getStatusCode();
          //  if (status &gt;=200 &amp;&amp; status &lt; 300) {
            	HttpEntity entity = response.getEntity();
            	if (entity.isStreaming()) {
            		byte data[] = EntityUtils.toByteArray(entity);
            		FileOutputStream fout = new FileOutputStream(new File(fullfilename));
            		fout.write(data);
            		fout.close();
            	}
           // }
		} finally {
            httpclient.close();
        }
        
        return true;

}
}
