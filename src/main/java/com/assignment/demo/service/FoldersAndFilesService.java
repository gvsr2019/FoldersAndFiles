package com.assignment.demo.service;

import java.util.List;

import com.assignment.demo.exceptions.ServiceException;
import com.assignment.demo.persistance.dto.FnFDTO;

public interface FoldersAndFilesService {

	public int[] saveFoldersAndFiles(List<FnFDTO> fnfList) throws ServiceException;

	public List<String> getFoldersAndFiles(String location) throws ServiceException;

}
