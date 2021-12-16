package eric.koo.spring.boot.doc.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DocumentModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String uuid;
    private String name;
    private String type;
    private Long size;
    private String previewUrl;
    private String downloadUrl;
}
