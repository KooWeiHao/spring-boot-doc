package eric.koo.spring.boot.doc.core.dao.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import eric.koo.spring.boot.doc.api.entity.DocumentBean;
import eric.koo.spring.boot.doc.api.entity.QDocumentBean;
import eric.koo.spring.boot.doc.core.dao.custom.DocumentDaoCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
class DocumentDaoImpl implements DocumentDaoCustom {
    private static final Logger logger = LoggerFactory.getLogger(DocumentDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<DocumentBean> getByUuid(String uuid) {
        final JPAQueryFactory q = new JPAQueryFactory(entityManager);
        final QDocumentBean document = QDocumentBean.documentBean;

        return Optional.ofNullable(q.selectFrom(document).where(document.uuid.eq(uuid)).fetchOne());
    }
}
