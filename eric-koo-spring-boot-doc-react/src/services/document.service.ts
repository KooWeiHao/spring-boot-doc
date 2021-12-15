import qs from "qs";
import http from "../configs/http.config";
import IDocument from "../types/document.type";

class DocumentService {
    upload(document: File) {
        const headers ={
            "Content-Type": "multipart/form-data"
        };

        const params = new FormData();
        params.append("file", document);

        return http.post<IDocument>("/doc-rest/upload", params, {headers: headers});
    }

    getDocumentByUuid(uuid: string) {
        const headers ={
            "Content-Type": "application/x-www-form-urlencoded"
        };

        const params = {
            "uuid": uuid
        };

        return http.post('/doc-rest/get-document-by-uuid', qs.stringify(params), {headers: headers});
    }
}

export default new DocumentService();