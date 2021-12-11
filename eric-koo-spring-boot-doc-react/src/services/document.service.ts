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
}

export default new DocumentService();