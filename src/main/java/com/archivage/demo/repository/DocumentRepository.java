package com.archivage.demo.repository;

import com.archivage.demo.model.Document;
import com.archivage.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface DocumentRepository extends MongoRepository<Document,String > {

    List<Document> findByTitleContaining(String keyword);
    List<Document> findByUserId(String userId);
    Optional<Document> findById(String id);
    Long countByUserId(String userId);
   // List<Document> listDocumentsByUser(String userId);
}
