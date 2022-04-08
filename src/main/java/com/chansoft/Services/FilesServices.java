package com.chansoft.Services;

import java.io.File;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chansoft.Entities.FilesEntity;
import com.chansoft.Repositories.FilesRepository;
import com.google.common.io.Files;

@Service
public class FilesServices {

	@Autowired
	FilesRepository filesRepository;

	static HashMap<String, Integer> fileNumbers = new HashMap<>();

	public String scanDirectory(File file, String requestUUID) {
		for (File curr : file.listFiles()) {
			if (!fileNumbers.containsKey(requestUUID)) {
				fileNumbers.put(requestUUID, 0);
			}
			if (curr.isFile() && Files.getFileExtension(curr.getAbsolutePath()).toLowerCase().contains("pdf")) {
				if (filesRepository.findByFileDirectoryIgnoreCase(curr.getAbsolutePath()).size() == 0) {
					FilesEntity filesEntity = new FilesEntity();
					filesEntity.setOcrCompleted(false);
					filesEntity.setOcrDownloaded(false);
					filesEntity.setOcrIndexed(false);
					filesEntity.setUploaded(false);
					filesEntity.setFileDirectory(curr.getAbsolutePath());
					filesRepository.save(filesEntity);
					fileNumbers.put(requestUUID, (fileNumbers.getOrDefault(requestUUID, 0) + 1));
				}
			} else if (curr.isDirectory()) {
				scanDirectory(curr, requestUUID);
			}
			if (fileNumbers.getOrDefault(requestUUID, 0) % 100 == 0) {
				System.out.println("Scanned " + fileNumbers.getOrDefault(requestUUID, 0) + "files so far");
			}
		}
		return "Scanned " + fileNumbers.get(requestUUID) + " files";
	}

}
