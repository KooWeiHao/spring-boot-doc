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

            return Promise.resolve();
        })
        .catch(error =>{
            const message = (error.response && error.response.data && error.response.data['message']) || error.message || error.toString();
            console.log(message)
            return Promise.reject(message);
        });
};