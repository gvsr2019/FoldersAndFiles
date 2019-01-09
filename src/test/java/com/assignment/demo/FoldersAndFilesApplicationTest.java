package com.assignment.demo;

import static org.junit.Assert.assertEquals;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FoldersAndFilesApplicationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void testIntegrationForGet() throws JSONException {
		String response = this.testRestTemplate.getForObject("/getLoc?loc=/fnfs", String.class);
		JSONAssert.assertEquals("[\"Folder-folder1\",\"File-file1.json\"]", response, false);
	}

	@Test
	public void testIntegrationForGetException() throws JSONException {
		String response = this.testRestTemplate.getForObject("/getLoc?loca=/fnfs", String.class);
		assertEquals("The request URL has some syntax error", response);
	}

	@Test
	public void testIntegrationForPost() throws JSONException {
		ResponseEntity<String> postResponse = this.testRestTemplate
				.postForEntity("/saveLoc?loc=/Users/gsiva/Desktop/in", null, String.class);
		assertEquals(HttpStatus.OK.value(), postResponse.getStatusCodeValue());
	}

	@Test
	public void testIntegrationForPostException() throws JSONException {
		String response = this.testRestTemplate.getForObject("/saveLoc?loca=fnfs", String.class);
		assertEquals("The request URL has some syntax error", response);
	}

	@Test
	public void testIntegrationForPostExceptionForMisLocation() throws JSONException {
		String response = this.testRestTemplate.getForObject("/saveLoc?loc=&fnfs", String.class);
		assertEquals("The request URL has some syntax error", response);
	}

	@Test
	public void testIntegrationForServiceException() throws JSONException {
		String response = this.testRestTemplate.getForObject("/saveLoc?loca=fnfs", String.class);
		assertEquals("The request URL has some syntax error", response);
	}

}
