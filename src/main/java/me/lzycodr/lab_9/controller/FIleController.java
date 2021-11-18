package me.lzycodr.lab_9.controller;

import me.lzycodr.lab_9.domain.Document;
import me.lzycodr.lab_9.repository.DocumentRepository;
import me.lzycodr.lab_9.service.DocumentService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FIleController {
    private final DocumentService documentService;
    private final DocumentRepository documentRepository;

    public FIleController(DocumentService documentService, DocumentRepository documentRepository) {
        this.documentService = documentService;
        this.documentRepository = documentRepository;
    }

    @PostMapping
    public ResponseEntity<?> postFile(@RequestParam("file") MultipartFile multiFile)  {
        documentService.saveFile(multiFile);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Document>> getAll() {
        return ResponseEntity.ok(documentRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getById(@PathVariable Long id, HttpServletRequest request) {
        Resource resource = documentService.getById(id);
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            throw new RuntimeException("Could not determine file type.");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
