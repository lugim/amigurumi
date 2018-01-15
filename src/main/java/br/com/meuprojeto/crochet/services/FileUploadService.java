package br.com.meuprojeto.crochet.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService{

    String upload(MultipartFile file);

}
