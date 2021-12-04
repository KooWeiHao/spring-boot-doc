package eric.koo.spring.boot.doc.core.dao;

import eric.koo.spring.boot.doc.api.entity.DocumentBean;
import eric.koo.spring.boot.doc.core.dao.custom.DocumentDaoCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentDao extends JpaRepository<DocumentBean, Long>, DocumentDaoCustom {
}
