package com.chansoft.Controllers;

import java.io.File;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chansoft.Services.FilesServices;

@RestController
public class FilesController {

	@Autowired
	FilesServices filesServices;

	@RequestMapping(path = "/indexItems", method = RequestMethod.GET)
	public String updateItem(@RequestParam(value = "base64Directory") String directory) {
		if (directory.isBlank()) {
			return "Please enter a directory in base64 format";
		}
		byte[] decoded = Base64.decodeBase64(directory);
		try {
			File file = new File(new String(decoded, "UTF-8"));
			if (!file.isDirectory()) {
				return "Invalid File Location";
			}
			return filesServices.scanDirectory(file, UUID.randomUUID().toString());
		} catch (Exception e) {
			return "Invalid base64 string";
		}
	}

	@RequestMapping(path = "/lol", method = RequestMethod.GET)
	public String returnLOL() {
		return "LOL";
	}
}
