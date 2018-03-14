package com.example.test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class People {
	int jmlrec;
	String baseUrl = "http://localhost:8080/persons/";
	String awal = "";
	String akhir = "";
	String selectPerson = "";
	int arrPhone;
	
	@Given("^Ambil jumlah total record saat ini$")
	public void givenTotalRecord() throws Throwable {
		HttpResponse<JsonNode> jsonResponse = Unirest.get(baseUrl+"all")
				.header("Accept", "application/json")
				.asJson();
		JSONArray myArray = jsonResponse.getBody().getArray();
		
		jmlrec = myArray.length();
	}
	
	@Given("^Ambil record person id ke: (\\d+)$")
	public void givenSelectPerson(String id) throws Throwable {
		HttpResponse<JsonNode> jsonResponse = Unirest.get(baseUrl+"{id}").routeParam("id", id)
				.header("Accept", "application/json")
				.asJson();
		selectPerson = jsonResponse.getBody().toString();
	}
	
	@Given("^Jumlah total record phone untuk person id: (\\d+)$")
	public void givenTotalPhoneRecordPerson(String id) throws Throwable {
		HttpResponse<JsonNode> jsonResponse = Unirest.get(baseUrl+"{id}").routeParam("id", id)
				.header("Accept", "application/json")
				.asJson();
		JSONObject jsonObj = jsonResponse.getBody().getObject().optJSONObject("phones");
		JSONArray jsonArr = jsonObj.getJSONArray("phones");
//		String[] phone = new String[jsonArr.length()]; 
		for (int i=0;i<jsonArr.length();i++) {
			arrPhone++;
		}
	}

	@When("^Ditambahkan record baru firstName:(.+), lastName:(.+), umur:(\\d+) tahun, hp: (\\d+), no ktp: (\\d*)$")
	public void whenAddPerson(String fn, String ln, int umur, String hp, String noktp) throws Throwable {
		/*{
		    "firstName": "Testing",
		    "lastName": "Lagi",
		    "age": 2,
		    "regis": {
		        "noregis": "123123"
		    },
		    "phones": [{
		        "phonenumber": "084657373123"
		    }]
		}*/
		
		StringBuilder jsonStr = new StringBuilder();
		jsonStr.append("{\"firstName\": \"" + fn + "\",").
				append("\"lastName\": \"" + ln + "\",").
				append("\"age\": " + umur + ",").
				append("\"regis\": {").
				append("\"noregis\": \"" + noktp + "\"").
				append("}, ").
				append("\"phones\": [{\"phonenumber\": \"" + hp + "\"}]").
				append("}");
		
		@SuppressWarnings("unused")
		HttpResponse<JsonNode> jsonResponse = Unirest.post(baseUrl+"add")
				  .header("Content-Type", "application/json")
				  .header("Accept", "application/json")
				  .body(jsonStr.toString())
				  .asJson();
		
	}
	
	@When("^Delete phone id ke: (\\d+)$")
	public void whenDeletePhone(String id) throws Throwable {
		StringBuilder jsonStr = new StringBuilder();
		jsonStr.append("{\"id\": \"" + id + "\"").
		append("}");
		
		@SuppressWarnings("unused")
		HttpResponse<JsonNode> jsonResponse = Unirest.post(baseUrl+"add")
				  .header("Content-Type", "application/json")
				  .header("Accept", "application/json")
				  .body(jsonStr.toString())
				  .asJson();
	}
	
//	@When("^Mengakses data personid: (\\d+)$")
//	public void whenSelectPerson(String id) throws Throwable {
//		HttpResponse<JsonNode> jsonResponse = Unirest.get(baseUrl+"{id}").routeParam("id", id)
//				.header("Accept", "application/json")
//				.asJson();
//		awal = jsonResponse.getBody().toString();
//	}
	
	@When("^Mengakses data person ke record awal \\+ (\\d+)$")
	public void whenSelectPersonObject(int jml) throws Throwable {
		jml = jmlrec + 1;
		HttpResponse<JsonNode> jsonResponse = Unirest.get(baseUrl+"{id}").routeParam("id", String.valueOf(jml))
				.header("Accept", "application/json")
				.asJson();
		
		JSONObject jsonObj = jsonResponse.getBody().getObject();
		arrPhone = jsonObj.getJSONArray("phones").length();
	}
	
	@When("^Hapus record id ke: (\\d+)$")
	public void whenDeletePerson(String id) throws Throwable {
		StringBuilder jsonStr = new StringBuilder();
		jsonStr.append("{\"id\": \"" + id + "\"").
				append("}");
		
		@SuppressWarnings("unused")
		HttpResponse<JsonNode> jsonResponse = Unirest.delete(baseUrl+"delete/{id}").routeParam("id", id)
				.header("Content-Type", "application/json")
				.header("Accept", "application/json")
				.body(jsonStr.toString())
				.asJson();
	}
	
	@When("^Data person id ke: (\\d+) diupdate menjadi firstName:(.+), lastName:(.+), umur:(\\d+) tahun$s")
	public void whenUpdatePerson(String id, String fn, String ln, int umur) throws Throwable {
		StringBuilder jsonStr = new StringBuilder();
		jsonStr.append("{\"firstName\": \"" + fn + "\",").
				append("\"lastName\": \"" + ln + "\",").
				append("\"age\": " + umur).
				append("}");
		
		@SuppressWarnings("unused")
		HttpResponse<JsonNode> jsonResponse = Unirest.put(baseUrl+"delete/{id}").routeParam("id", id)
				.header("Content-Type", "application/json")
				.header("Accept", "application/json")
				.body(jsonStr.toString())
				.asJson();
	}
	
	@When("^Update phone id ke: (\\d+) menjadi: (\\d+)$")
	public void whenUpdatePhone(String id, String hp) throws Throwable {
		StringBuilder jsonStr = new StringBuilder();
		jsonStr.append("{\"id\": \"" + id + "\",").
				append("\"phonenumber\": \"" + hp + "\"").
				append("}");
		
		@SuppressWarnings("unused")
		HttpResponse<JsonNode> jsonResponse = Unirest.put(baseUrl+"updatephone")
				.header("Content-Type", "application/json")
				.header("Accept", "application/json")
				.body(jsonStr.toString())
				.asJson();
	}
	
	@Then("^Jumlah total data terakhir adalah record awal \\+ (\\d+)$")
	public void then(int rec) throws Throwable {
		HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/persons/all")
				.header("Accept", "application/json")
				.asJson();
		JSONArray myArray = jsonResponse.getBody().getArray();
		assertThat(jmlrec+rec, equalTo(myArray.length()));
	}
	
	@Then("^Jumlah total data terakhir adalah record awal \\- (\\d+)$")
	public void thenFinalRecordCount(int rec) throws Throwable {
		HttpResponse<JsonNode> jsonResponse = Unirest.get(baseUrl+"all")
				.header("Accept", "application/json")
				.asJson();
		JSONArray myArray = jsonResponse.getBody().getArray();
		assertThat(jmlrec-1, equalTo(myArray.length()));
	}
	
	@Then("^Data person yang didapatkan saat insert dan yang diakses adalah sama")
	public void thenDataIsEqual() throws Throwable {
		HttpResponse<JsonNode> jsonRes = Unirest.get(baseUrl+"{id}").routeParam("id", String.valueOf(jmlrec+1))
				.header("Accept", "application/json")
				.asJson();
		akhir = jsonRes.getBody().toString();
		
		assertThat(akhir, is(not(selectPerson)));
	}
	
	@Then("^Jumlah record phone id ke: (\\d+) sama dengan jumlah awal$")
	public void thenCheckTotalPhone(String id) throws Throwable {
		HttpResponse<JsonNode> jsonResponse = Unirest.get(baseUrl+"{id}").routeParam("id", id)
				.header("Accept", "application/json")
				.asJson();
		JSONObject jsonObj = jsonResponse.getBody().getObject();
		int x = jsonObj.getJSONArray("phone").length();
		assertThat(arrPhone, equalTo(x));
	}
	
	@Then("^Jumlah terakhir record phone person id ke: (\\d+) sama dengan record awal - (\\d+)$")
	public void thenCheckTotalPhone(String id, int rec) throws Throwable {
		HttpResponse<JsonNode> jsonResponse = Unirest.get(baseUrl+"{id}").routeParam("id", id)
				.header("Accept", "application/json")
				.asJson();
		JSONObject jsonObj = jsonResponse.getBody().getObject();
		int x = jsonObj.getJSONArray("phone").length();
		assertThat(x, equalTo(arrPhone-rec));
	}
	
	@Then("^Person id ke: (\\d+) memiliki nomor telepon: (\\d+)$")
	public void thenCheckPhone(String id, String hp) throws UnirestException {
		HttpResponse<JsonNode> jsonResponse = Unirest.get(baseUrl+"{id}").routeParam("id", id)
				.header("Accept", "application/json")
				.asJson();
		String jsonData = jsonResponse.getBody().getObject().getString("phones");
		assertThat(jsonData, containsString(hp));
	}
	
}
