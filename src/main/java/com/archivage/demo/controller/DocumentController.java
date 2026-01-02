package com.archivage.demo.controller;

import com.archivage.demo.model.Document;
import com.archivage.demo.service.DocumentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "*")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService= documentService;
    }
/*
  @PostMapping("/upload")
  public Document upload(@RequestBody Document doc){
      doc.setCreatedAt(LocalDateTime.now());
      return documentService.save(doc);
  }
*/
    // Upload d’un PDF
    /*
    @PostMapping("/upload")
    public Document uploadDocument(@RequestParam String title,@RequestParam(required = false) String content,@RequestParam String userId, @RequestParam MultipartFile file) throws IOException {
        Document doc = new Document();
        doc.setTitle(title);
        doc.setContent(content);
        doc.setUserId(userId);
        doc.setFile(file.getBytes());
        doc.setCreatedAt(LocalDateTime.now());
        return documentService.save(doc);
    }
*/
    @PostMapping("/upload")
    public ResponseEntity<?> upload(
            @RequestParam String title,
            @RequestParam(required = false) String content,
            @RequestParam MultipartFile file,
            @RequestParam String userId) throws IOException {

        if (!documentService.canAddDocument(userId)) {
            return ResponseEntity.status(403).body("Limite freemium atteinte, passez au Premium !");
        }

        Document doc = documentService.save(title,content,userId,file.getBytes());
        return ResponseEntity.ok(doc);


    }
    // Télécharger un PDF
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable String id) {
        Optional<Document> optionalDoc = documentService.findById(id);
        if (optionalDoc.isEmpty()) return ResponseEntity.notFound().build();

        Document doc = optionalDoc.get();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + doc.getTitle() + ".pdf\"")
                .body(doc.getFile());
    }


   @GetMapping("/list/{userId}")
   public List<Document> list(@PathVariable String userId){
   return documentService.findByUserId(userId);
   }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        documentService.delete(id);
    }
/*
    @GetMapping("/list")
    public List<Document> documentList(@RequestParam String userId){
        return documentService.listDocumentsByUser(userId)  ;  }
*/

    @GetMapping("/list")
    public List<Document> listDocuments(@RequestParam String userId){
        return documentService.getDocumentsByUser(userId);
    }

    @GetMapping("/search")
    public List<Document> search(@RequestParam String keyword) {
        return documentService.searchByTitle(keyword);
    }
}
