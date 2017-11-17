package com.igs.api;

import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import net.minidev.json.JSONObject;

public class IgsApi {

	// API to receive the OTP of the desired Mobile Number from the APIs
	public String OTPSender(String MobileNumber) {
		String Url = "http://180.179.117.4/cas-webapp/cas/v1/otp/send?j_username=" + MobileNumber + "";

		JSONObject json = new JSONObject();

		Response response = RestAssured.given()
				.header("OTPAuthorization",
						"Basic MWJhZDY0NDItZjdhZC00YWNiLTg3YjQtNGEzZDI3YTE0Y2NlOjE1MDk3MTE3NzQxMDQ=")
				.header("APIKeyAuthorization", "Basic cGxheXN0b3JlOldFZTM0ckFTd1JOTnhvcDM6MTUwOTcxMTc3NDEwNA==")
				.header("device_id", "e9be0308f2b0ab4e").accept("application/json").get(Url);

		// response.then().statusCode(200);

		String Otp = response.then().extract().path("code").toString();
		return Otp;
	}

}
