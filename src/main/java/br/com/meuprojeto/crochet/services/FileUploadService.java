package br.com.meuprojeto.crochet.services;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	private String path = "D:\\amigurumi\\test\\";

	public String upload(MultipartFile file) { // TODO passar receitaId para nomear a pasta? ainda assim pode sobrepor o
												// arquivo.

		// TODO verificar se arquivo existe

		String nomeArquivo = file.getOriginalFilename();

		try {

			// para quando for criar diretorios por receita
			File dirPath = new File(path);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}

			// TODO path + \\idReceita\\ +nome arquivo. Ou, gerar um hash e gravar, caso
			// permita fotos.. Neste caso. alterar pra indicar qual é a receita e quais sao
			// as fotos..

			file.transferTo(new File(path + nomeArquivo));

		} catch (IOException e) {

		}

		return (path + nomeArquivo);

	}

}
