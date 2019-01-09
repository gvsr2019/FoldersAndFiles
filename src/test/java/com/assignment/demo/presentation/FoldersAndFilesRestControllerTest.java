package com.assignment.demo.presentation;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.assignment.demo.exceptions.ServiceException;
import com.assignment.demo.service.FoldersAndFilesService;

@RunWith(SpringRunner.class)
@WebMvcTest(FoldersAndFilesRestController.class)
public class FoldersAndFilesRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FoldersAndFilesService fnFServiceImpl;

	private List<String> fnFList = Arrays.asList("Folder-folder1", "File-file1.json");
	private String testParamURL = "C:\\Users\\gsiva\\Desktop\\in";

	@Test
	public void testGetFoldersAndFiles() throws Exception {
		when(fnFServiceImpl.getFoldersAndFiles(anyString())).thenReturn(fnFList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getLoc").param("loc", testParamURL)
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().json("[\"Folder-folder1\",\"File-file1.json\"]")).andReturn();
	}

	@Test
	public void testGetFnFControllerException() throws Exception {
		when(fnFServiceImpl.getFoldersAndFiles(anyString())).thenReturn(fnFList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getLoc").param("loca", "badURL")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
				.andExpect(content().string("The request URL has some syntax error")).andReturn();
	}

	@Test
	public void testGetFnFServiceException() throws Exception {
		when(fnFServiceImpl.getFoldersAndFiles(anyString())).thenThrow(ServiceException.class);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getLoc").param("loc", testParamURL)
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isServiceUnavailable())
				.andExpect(content().string("Service is unavaible for the request due to internal error")).andReturn();
	}

	@Test
	public void testSaveFoldersAndFiles() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveLoc").param("loc", testParamURL)
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
	}

	@Test
	public void testSaveFoldersAndFilesForMisLocation() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveLoc").param("loc", "badLocation")
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
				.andExpect(content().string("The request URL has some syntax error")).andReturn();
	}

}
