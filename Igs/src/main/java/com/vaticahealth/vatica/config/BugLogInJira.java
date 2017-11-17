package com.vaticahealth.vatica.config;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.AuthenticationException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterSuite;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;

public class BugLogInJira {

	MonitoringMail mail = new MonitoringMail();

	@SuppressWarnings("unchecked")
	@AfterSuite(description = "Logs bug In JIRA On Failure", enabled = false, dependsOnMethods = { "endReport" })
	public Object logBugInJiraOnFailure(int failedcount) throws AddressException, MessagingException,
			AuthenticationException, ClientHandlerException, UniformInterfaceException, ParseException {

		String auth = new String(Base64.encode("npachauri@vaticahealth.com:suspension"));
		String url = "https://vaticahealth.atlassian.net/rest/api/2/issue";
		JSONObject jsonRes = null;

		JSONObject json = new JSONObject();
		JSONObject objProject = new JSONObject();
		JSONObject objTemp = new JSONObject();
		JSONObject objTemp2 = new JSONObject();

		objTemp.put("key", "NW");
		objProject.put("project", objTemp);
		objProject.put("summary", "Automation Test on QA Build Failed. Build is unstable.");
		objProject.put("description",
				failedcount + " test case/cases failed during automation test execution on the QA build.");
		objTemp2.put("name", "Bug");
		objProject.put("issuetype", objTemp2);
		json.put("fields", objProject);
		String data = json.toJSONString();

		if (mail.getPassedTestCount() < 42) {

			Client client = Client.create();
			WebResource webResource = client.resource(url);

			ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
					.accept("application/json").post(ClientResponse.class, data);

			int statusCode = response.getStatus();
			if (statusCode == 401) {
				throw new AuthenticationException("Invalid Username or Password");
			} else if (statusCode == 403) {
				throw new AuthenticationException("Forbidden");
			} else if (statusCode == 200 || statusCode == 201) {
				System.out.println("Ticket Created succesfully");
			} else {
				System.out.print("Http Error : " + statusCode);
			}

			JSONParser parser = new JSONParser();
			jsonRes = (JSONObject) parser.parse(response.getEntity(String.class));

		}
		return jsonRes.get("key");

	}
}