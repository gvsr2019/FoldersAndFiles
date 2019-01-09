package com.assignment.demo.persistance.dao;

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
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.assignment.demo.persistance.dto.FnFDTO;

@RunWith(MockitoJUnitRunner.class)
public class FoldersAndFilesDaoImplTest {

	@InjectMocks
	FoldersAndFilesDaoImpl foldersAndFilesDaoImpl;

	@Mock
	JdbcTemplate jdbcTemplate;

	List<FnFDTO> fnFList = Arrays.asList(new FnFDTO("/fnfs", "folder1", 'D'), new FnFDTO("/fnfs", "file1", 'F'));

	@Test
	public void testInsertFnfs() throws Exception {
		when(jdbcTemplate.batchUpdate(anyString(), any(BatchPreparedStatementSetter.class)))
				.thenReturn(new int[] { 1 });
		assertEquals(1, Arrays.asList(foldersAndFilesDaoImpl.insertFnfs(fnFList)).size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetFnfs() throws Exception {
		when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(fnFList);
		List<FnFDTO> result = foldersAndFilesDaoImpl.getFnfs("");
		assertEquals(2, result.size());
	}

}
