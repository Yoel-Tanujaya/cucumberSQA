$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("persons.feature");
formatter.feature({
  "line": 1,
  "name": "CRUD Person dan Phone",
  "description": "",
  "id": "crud-person-dan-phone",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 2,
  "name": "Penghapusan Data Person",
  "description": "",
  "id": "crud-person-dan-phone;penghapusan-data-person",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 3,
  "name": "Ambil jumlah total record saat ini",
  "keyword": "Given "
});
formatter.step({
  "line": 4,
  "name": "Hapus record id ke: 1",
  "keyword": "When "
});
formatter.step({
  "line": 5,
  "name": "Data Person id ke: 1 kosong",
  "keyword": "Then "
});
formatter.match({
  "location": "People.given()"
});
formatter.result({
  "duration": 543437421,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 20
    }
  ],
  "location": "People.when(String)"
});
formatter.result({
  "duration": 15041375,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 19
    }
  ],
  "location": "People.then(String)"
});
formatter.result({
  "duration": 60203835,
  "error_message": "java.lang.AssertionError: \nExpected: \"[]\"\n     but: was \"{\\\"firstName\\\":\\\"Budi\\\",\\\"lastName\\\":\\\"Susanto\\\",\\\"phones\\\":[{\\\"phonenumber\\\":\\\"987854626272\\\",\\\"id\\\":1}],\\\"@id\\\":1,\\\"id\\\":1,\\\"registrasi\\\":{\\\"noregis\\\":\\\"88888888888\\\",\\\"person\\\":1,\\\"@id\\\":2,\\\"id\\\":1},\\\"age\\\":17}\"\n\tat org.hamcrest.MatcherAssert.assertThat(MatcherAssert.java:20)\n\tat org.junit.Assert.assertThat(Assert.java:956)\n\tat org.junit.Assert.assertThat(Assert.java:923)\n\tat com.example.test.People.then(People.java:72)\n\tat ✽.Then Data Person id ke: 1 kosong(persons.feature:5)\n",
  "status": "failed"
});
});