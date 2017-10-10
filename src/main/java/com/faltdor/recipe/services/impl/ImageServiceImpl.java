package com.faltdor.recipe.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.faltdor.recipe.services.IImageService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImageServiceImpl implements IImageService{

	public void saveImageFile(Long valueOf, MultipartFile file) {
		// TODO Auto-generated method stub
		log.info("*************image recive*********");
	}

}
