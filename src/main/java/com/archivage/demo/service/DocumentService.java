package com.archivage.demo.service;


import com.archivage.demo.model.Document;
import com.archivage.demo.model.User;
import com.archivage.demo.repository.DocumentRepository;
import com.archivage.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

private final DocumentRepository documentRepository;
private final UserRepository userRepository;

    public DocumentService(DocumentRepository documentRepository, UserRepository userRepository) {
        this.documentRepository = documentRepository;
        this.userRepository= userRepository;

    }
    public Document save( String title,String content,String userId, byte[] file){
Document doc=new Document(title, content, userId, LocalDateTime.now(),file);
return documentRepository.save(doc);
}

    public List<Document> searchByTitle(String keyword) {
        return documentRepository.findByTitleContaining(keyword);
    }
    public List<Document> findByUserId(String userId) {
        return documentRepository.findByUserId(userId);
    }

    public Optional<Document>  findById(String id){ return documentRepository.findById(id);}
     /* public List<Document> listDocumentsByUser(String userId){
return documentRepository.listDocumentsByUser(userId);}

*/
     public boolean canAddDocument(String userId) {
         User user = userRepository.findById(userId).orElseThrow();
         long count = documentRepository.countByUserId(userId);
         return user.isPremium() || count < 3; // Freemium limité à 3
     }

    public List<Document> getDocumentsByUser(String userId){
        return documentRepository.findByUserId(userId);
    }

    public void delete(String documentId) {
        documentRepository.deleteById(documentId);
    }

    public Document save(Document doc) {
        return documentRepository.save(doc);
    }

}