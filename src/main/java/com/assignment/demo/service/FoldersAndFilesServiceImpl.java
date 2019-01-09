/**
 * 
 */
package com.assignment.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.demo.exceptions.ServiceException;
import com.assignment.demo.persistance.dao.FoldersAndFilesDAO;
import com.assignment.demo.persistance.dto.FnFDTO;

/**
 * @author gsiva
 *
 */
@Service
public class FoldersAndFilesServiceImpl implements FoldersAndFilesService {

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	FoldersAndFilesDAO foldersAndFilesDAO;

	public int[] saveFoldersAndFiles(List<FnFDTO> fnfList) throws ServiceException {
		try {
			logger.info("Batch inserting Folders and Files info of the given location into database");
			return foldersAndFilesDAO.insertFnfs(fnfList);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<String> getFoldersAndFiles(String location) throws ServiceException {
		try {
			logger.info("Getting Folders and Files info of the given location from the database");
			return foldersAndFilesDAO.getFnfs(location).stream().map(p -> getFnFName(p.getName(), p.getCategory()))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public String getFnFName(String name, char category) {
		if ('F' == category) {
			return String.join("-", "File", name);
		}
		return String.join("-", "Folder", name);
	}

}
