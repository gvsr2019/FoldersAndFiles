package com.assignment.demo.persistance.dao;

import java.util.List;

import com.assignment.demo.persistance.dto.FnFDTO;

public interface FoldersAndFilesDAO {

	public int[] insertFnfs(List<FnFDTO> fnfList) throws Exception;

	public List<FnFDTO> getFnfs(String location) throws Exception;

}
