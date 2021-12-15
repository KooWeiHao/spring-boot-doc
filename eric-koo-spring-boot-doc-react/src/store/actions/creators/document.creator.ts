import {Dispatch} from "react";
import {DocumentActionType, IDocumentAction} from "../types/document.type";
import documentService from "../../../services/document.service";

export const upload = (document: File) =>(dispatch: Dispatch<IDocumentAction>) =>{
    return documentService.upload(document)
        .then(response =>{
            dispatch({
                type: DocumentActionType.UPLOAD,
                payload: response.data
            });

            return Promise.resolve(response.data.uuid);
        })
        .catch(error =>{
            const message = (error.response && error.response.data && error.response.data['message']) || error.message || error.toString();
            return Promise.reject(message);
        });
};

export const getDocumentByUuid = (uuid: string) =>(dispatch: Dispatch<IDocumentAction>) =>{
    return documentService.getDocumentByUuid(uuid)
        .then(response =>{
            dispatch({
                type: DocumentActionType.GET_DOCUMENT_BY_UUID,
                payload: response.data
            });

            return Promise.resolve();
        })
        .catch(error =>{
            const message = (error.response && error.response.data && error.response.data['message']) || error.message || error.toString();
            return Promise.reject(message);
        });
}