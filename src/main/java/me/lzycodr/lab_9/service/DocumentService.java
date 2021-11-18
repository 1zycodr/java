package me.lzycodr.lab_9.service;

import lombok.extern.java.Log;
import me.lzycodr.lab_9.domain.Document;
import me.lzycodr.lab_9.repository.DocumentRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Log
public class DocumentService {
    private final DocumentRepository repository;
    private final String storageDirectory = "./storage/";

    public DocumentService(DocumentRepository repository) {
        this.repository = repository;
    }

    public void saveFile(MultipartFile file) {
        if (file == null) {
            throw new RuntimeException("File is null");
        }

        try {
            Files.write(Paths.get(storageDirectory + file.getOriginalFilename()), file.getBytes());
            repository.save(
                    Document.builder().uri(storageDirectory + file.getOriginalFilename())
                            .build()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Resource loadFileAsResource(String uri) {
        try {
            Path filePath = Paths.get(uri);
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found " + uri);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File not found " + uri, ex);
        }
    }

    public Resource getById(Long id) {
        return loadFileAsResource(repository.getById(id).getUri());
    }
}
