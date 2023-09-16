package com.food.recipe.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	
	// 파일 여러 개
	public ArrayList<String> fileUpload(ArrayList<MultipartFile> files) throws IOException{
		
		String path = "C:/springWorkspace/recipeImages/";
		
		ArrayList<String> orginalFileNameList  = new ArrayList<String>();
		
		for(MultipartFile file : files) {

			String originalFileName = file.getOriginalFilename();
			
			UUID uuid = UUID.randomUUID();
			String savedFileName = uuid.toString() + "_" + originalFileName;
			
			orginalFileNameList.add(savedFileName);
			
			File sendFile = new File(path + savedFileName);
			
			file.transferTo(sendFile);
		}
		
		return orginalFileNameList;
	}
	// 1개 파일
	public String fileUpload(MultipartFile file) throws IOException{
		
		String path = "C:/springWorkspace/recipeImages/";
		
		String originalFileName = file.getOriginalFilename();
		
		UUID uuid = UUID.randomUUID();
		String savedFileName = uuid.toString() + "_" + originalFileName;
		
		File sendFile = new File(path + savedFileName);
		
		file.transferTo(sendFile);
		
		return savedFileName;
	}
	
	
	public void fileDownload(String file, String path) throws IOException{
		
	}
}
