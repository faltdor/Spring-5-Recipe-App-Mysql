package com.faltdor.recipe.services;

import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
	public void saveImageFile(Long valueOf, MultipartFile file) ;
}
