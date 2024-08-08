package api.petstore.interfaces;

import api.petstore.payloads.User;
import org.bson.Document;
import java.sql.ResultSet;

// S: Single Responsibility Principle - Defines common database operations
// D: Dependency Inversion Principle - High-level module depends on an abstraction (interface) rather than a concrete implementation
public interface IDatabase {

        void connect(String dbName);
        void insertDocument(String collectionName, Document document);
        Document findDocumentById(String collectionName, String id);
        void updateDocument(String collectionName, String id, Document updatedDocument);
        void deleteDocument(String collectionName, String id);
        void close();

}