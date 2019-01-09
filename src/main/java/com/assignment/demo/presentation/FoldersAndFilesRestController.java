/**
 * 
 */
package com.assignment.demo.presentation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.demo.exceptions.ServiceException;
import com.assignment.demo.persistance.dto.FnFDTO;
import com.assignment.demo.service.FoldersAndFilesService;

/**
 * @author gsiva
 *
 */
@RestController()
public class FoldersAndFilesRestController {

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	FoldersAndFilesService foldersAndFilesService;

	private char FOLDER;

	private char FILE;

	@GetMapping(path = "/getLoc", produces = MediaType.APPLICATION_JSON_VALUE)
	List<String> getFoldersAndFiles(@RequestParam String loc) throws ServiceException {
		List<String> result = foldersAndFilesService.getFoldersAndFiles(loc.replace('/', '\\'));
		logger.info("Got Files and folders from the data base for the given location");
		return result;
	}

	@PostMapping("/saveLoc")
	int[] saveFoldersAndFiles(@RequestParam String loc) throws Exception {
		try {
			int[] result;
			result = foldersAndFilesService.saveFoldersAndFiles(getFnfsFromLoc(loc));
			logger.info("Saved the folders and files info of the entered location into database");
			return result;
		} catch (Exception e) {
			throw e;
		}
	}

	private List<FnFDTO> getFnfsFromLoc(String loc) throws Exception {
		File locDir = new File(loc);
		if (locDir.exists()) {
			return handleLocDirectory(locDir);
		}
		throw new Exception();
	}

	private List<FnFDTO> handleLocDirectory(File locDir) {
		List<FnFDTO> fnfList = new ArrayList<>();
		for (File file : locDir.listFiles()) {
			if (file.isDirectory()) {
				fnfList.add(new FnFDTO(file.getParent().toString(), file.getName(), FOLDER));
				handleLocDirectory(file);
			} else {
				fnfList.add(new FnFDTO(file.getParent(), file.getName(), FILE));
			}
		}
		logger.info("Executing folders and files info extraction asynschrounsly from the entered location");
		return fnfList;
	}

}
