package eric.koo.spring.boot.doc.api.service;

import eric.koo.spring.boot.doc.api.entity.DocumentBean;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface DocumentService {
    DocumentBean addDocument(MultipartFile file);
    Optional<DocumentBean> getDocumentByUuid(String uuid);
}
