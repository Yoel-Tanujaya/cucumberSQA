package com.example.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.json.JSONArray;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class People {
	int jmlrec;
	String baseUrl = "http://localhost:8080/persons";
	
	@Given("^Ambil jumlah total record saat ini$")
	public void given() throws Throwable {
		HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/persons/all")
				.header("Accept", "application/json")
				.asJson();
		JSONArray myArray = jsonResponse.getBody().getArray();
		
		jmlrec = myArray.length();
	}

//	@When("^Ditambahkan record baru firstName:(.+), lastName:(.+), umur:(\\d+) tahun, hp: (\\d+), no ktp: (\\d*)$")
//	public void when(String fn, String ln, int umur, String hp, String noktp) throws Throwable {
//		/*{
//		    "firstName": "Testing",
//		    "lastName": "Lagi",
//		    "age": 2,
//		    "regis": {
//		        "noregis": "123123"
//		    },
//		    "phones": [{
//		        "phonenumber": "084657373123"
//		    }]
//		}*/
//		
//		StringBuilder jsonStr = new StringBuilder();
//		jsonStr.append("{\"firstName\": \"" + fn + "\",").
//				append("\"lastName\": \"" + ln + "\",").
//				append("\"age\": " + umur + ",").
//				append("\"regis\": {").
//				append("\"noregis\": \"" + noktp + "\"").
//				append("}, ").
//				append("\"phones\": [{\"phonenumber\": \"" + hp + "\"}]").
//				append("}");
//		
//		@SuppressWarnings("unused")
//		HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:8080/persons/add")
//				  .header("Content-Type", "application/json")
//				  .header("Accept", "application/json")
//				  .body(jsonStr.toString())
//				  .asJson();
//	}
	
	@When("^Hapus record id ke: (\\d+)$")
	public void when(String id) throws Throwable {		
		HttpResponse<String> delResponse = Unirest.delete(baseUrl+"/delete/{id}").routeParam("id", id).asString();
		System.out.println(delResponse.getStatusText() + " " + delResponse.getBody());
	}
	
	@Then("^Data Person id ke: (\\d+) kosong$")
	public void then(String id) throws Throwable {
		HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/persons/{id}").routeParam("id", id)
				.header("Accept", "application/json")
				.asJson();
		String jsonRes = jsonResponse.getBody().toString();
		assertThat(jsonRes, equalTo("[]"));
	}

}
