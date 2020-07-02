package masterslavephysical.gridconsole;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;


public class DefectUpdate {

	public static void main(String[] args) {
		
		/*Client client = ClientBuilder.newClient();
		Entity<String> payload = Entity.json("{  \"executions\": [    \"STEP-1\",    \"STEP-12\"  ],  \"defects\": [    \"STEP-58\",    \"STEP-57\"  ]}");
		Response response = client.target("https://ustqualityengineering.atlassian.net/jira/rest/zapi/latest/execution/updateWithBulkDefects")
		  .request(MediaType.APPLICATION_JSON_TYPE)
		  .put(payload);

		System.out.println("status: " + response.getStatus());
		System.out.println("headers: " + response.getHeaders());
		System.out.println("body:" + response.readEntity(String.class));*/
		
		
		
		Client client = ClientBuilder.newClient();
		Entity payload = Entity.json("{  \"type\": [{\"name\": \"is tested by\"}], \\\"inwardIssue\\\": [{\\\"key\\\": \\\"STEP-58\\\"}],  \\\"outwardIssue\\\": [{\\\"key\\\": \\\"STEP-1\\\"}]}");
		Response response = client.target("https://ustqualityengineering.atlassian.net/rest/api/2/issueLink")
		  .request(MediaType.APPLICATION_JSON_TYPE)
		  .post(payload);

		System.out.println("status: " + response.getStatus());
		System.out.println("headers: " + response.getHeaders());
		System.out.println("body:" + response.readEntity(String.class));
		
		
		

	}

}
