package eric.koo.spring.boot.doc.core.service.impl;

import eric.koo.spring.boot.doc.api.entity.DocumentBean;
import eric.koo.spring.boot.doc.api.service.DocumentService;
import eric.koo.spring.boot.doc.core.dao.DocumentDao;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.Optional;

@Service
class DocumentServiceImpl implements DocumentService {
    private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

    private final DocumentDao documentDao;

    DocumentServiceImpl(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }

    @SneakyThrows
    @Transactional
    @Override
    public DocumentBean addDocument(MultipartFile file) {
        DocumentBean document = new DocumentBean();
        document.setName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
        document.setType(file.getContentType());
        document.setData(file.getBytes());
        document.setSize(file.getSize());
        documentDao.save(document);

        return document;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<DocumentBean> getDocumentByUuid(String uuid) {
        return documentDao.getByUuid(uuid);
    }
}
