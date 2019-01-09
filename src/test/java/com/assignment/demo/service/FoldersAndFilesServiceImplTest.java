package com.assignment.demo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.assignment.demo.exceptions.ServiceException;
import com.assignment.demo.persistance.dao.FoldersAndFilesDAO;
import com.assignment.demo.persistance.dto.FnFDTO;

@RunWith(MockitoJUnitRunner.class)
public class FoldersAndFilesServiceImplTest {

	@InjectMocks
	FoldersAndFilesServiceImpl foldersAndFilesServiceImpl;

	@Mock
	FoldersAndFilesDAO foldersAndFilesDAO;

	List<FnFDTO> fnFList = Arrays.asList(new FnFDTO("\fnfs", "folder1", 'D'), new FnFDTO("\fnfs", "file1.json", 'F'));
	List<String> fnFResultList = Arrays.asList("Folder-folder1", "File-file1.json");

	@SuppressWarnings("unchecked")
	@Test
	public void testSaveFoldersAndFiles() throws Exception {
		when(foldersAndFilesDAO.insertFnfs((List<FnFDTO>) any(List.class))).thenReturn(new int[1]);
		assertEquals(1, Arrays.asList(foldersAndFilesServiceImpl.saveFoldersAndFiles(fnFList)).size());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = ServiceException.class)
	public void testSaveFoldersAndFilesException() throws Exception {
		when(foldersAndFilesDAO.insertFnfs((List<FnFDTO>) any(List.class))).thenThrow(Exception.class);
		foldersAndFilesServiceImpl.saveFoldersAndFiles(fnFList);
	}

	@Test
	public void testGetFoldersAndFiles() throws Exception {
		when(foldersAndFilesDAO.getFnfs(anyString())).thenReturn(fnFList);
		List<String> result = foldersAndFilesServiceImpl.getFoldersAndFiles("");
		assertEquals(2, result.size());
		assertEquals("Folder-folder1", result.get(0));
		assertEquals("File-file1.json", result.get(1));
	}

}
