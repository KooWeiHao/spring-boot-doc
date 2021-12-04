package eric.koo.spring.boot.doc.core.dao.custom;

import eric.koo.spring.boot.doc.api.entity.DocumentBean;

import java.util.Optional;

public interface DocumentDaoCustom {
    Optional<DocumentBean> getByUuid(String uuid);
}
