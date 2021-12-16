package eric.koo.spring.boot.doc.core.mvc;

import eric.koo.spring.boot.doc.api.entity.DocumentBean;
import eric.koo.spring.boot.doc.api.model.DocumentModel;
import eric.koo.spring.boot.doc.api.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping(value = "/doc-rest")
class DocumentController {
    private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @Value("${spring.boot.doc.server}")
    private String springBootDocServer;

    private final DocumentService documentService;

    @Autowired
    DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("get-document-by-uuid")
    DocumentModel getDocumentByUuid(@RequestParam(value = "uuid") String uuid){
        return documentService.getDocumentByUuid(uuid)
                .map(this::documentBean2DocumentModel)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("%s not found!", uuid)));
    }

    @PostMapping("upload")
    DocumentModel upload(@RequestParam(value = "file") List<MultipartFile> files){
        final MultipartFile file = files.get(0);

        if(files.size() > 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "one.file.only");
        }

        if(file.getSize() <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no.file.selected");
        }

        try{
            final DocumentBean document = documentService.addDocument(file);
            return documentBean2DocumentModel(document);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, String.format("Upload failed: %s !", file.getOriginalFilename()));
        }
    }

    private DocumentModel documentBean2DocumentModel(DocumentBean document){
        final String uuid = document.getUuid();
        DocumentModel documentModel = new DocumentModel();

        BeanUtils.copyProperties(document, documentModel);
        documentModel.setPreviewUrl(String.format("%s/doc-rest/preview/%s", springBootDocServer, uuid));
        documentModel.setDownloadUrl(String.format("%s/doc-rest/preview/%s", springBootDocServer, uuid));

        return documentModel;
    }

    @GetMapping("download/{uuid}")
    ResponseEntity<byte[]> download(@PathVariable String uuid){
        return getDocumentResponse(uuid, false);
    }

    @GetMapping("preview/{uuid}")
    ResponseEntity<byte[]> preview(@PathVariable String uuid){
        return getDocumentResponse(uuid, true);
    }

    private ResponseEntity<byte[]> getDocumentResponse(String uuid, Boolean isPreview){
        return documentService.getDocumentByUuid(uuid)
                .map(document -> {
                    final String returnType = isPreview ? "inline" : "attachment";
                    final String filename = UriUtils.encode(document.getName(), StandardCharsets.UTF_8);

                    return ResponseEntity.ok()
                            .contentType(MediaType.parseMediaType(document.getType()))
                            .header(HttpHeaders.CONTENT_DISPOSITION, String.format("%s; filename=\"%s\"; filename*=UTF-8''%s", returnType, filename, filename))
                            .body(document.getData());
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("%s not found!", uuid)));
    }
}
