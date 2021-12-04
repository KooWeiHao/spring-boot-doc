package eric.koo.spring.boot.doc.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="DOCUMENT")
public class DocumentBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DOCUMENT_ID")
    private Long documentId;

    @Column(name="UUID", columnDefinition = "varchar(36)", nullable = false)
    private String uuid = UUID.randomUUID().toString();

    @Column(name="NAME", columnDefinition = "varchar(256)", nullable = false)
    private String name;

    @Column(name="TYPE", columnDefinition = "varchar(256)", nullable = false)
    private String type;

    @Lob
    @Column(name="DATA", nullable = false)
    private byte[] data;

    @Column(name="SIZE", nullable = false)
    private Long size;

    @Column(name="CREATED_DATE", columnDefinition="timestamp", nullable = false, updatable = false)
    private Date createdDate = Date.from(Instant.now());

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof DocumentBean)) return false;
        DocumentBean that = (DocumentBean)obj;
        return this.documentId.equals(that.documentId);
    }

    @Override
    public int hashCode() {
        return 41 * (41 + documentId.hashCode());
    }
}
