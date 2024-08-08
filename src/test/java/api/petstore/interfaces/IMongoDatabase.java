package api.petstore.interfaces;

import org.bson.Document;

public interface IMongoDatabase extends IBaseDatabase{

    void insertDocument(String collectionName, Document document);
    Document findDocumentById(String collectionName, String id);
    void updateDocument(String collectionName, String id, Document updatedDocument);
    void deleteDocument(String collectionName, String id);

}
