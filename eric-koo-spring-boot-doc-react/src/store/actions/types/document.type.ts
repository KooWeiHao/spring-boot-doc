import IDocument from "../../../types/document.type";

export enum DocumentActionType {
    UPLOAD = "UPLOAD",
    GET_DOCUMENT_BY_UUID = "GET_DOCUMENT_BY_UUID"
}

interface IGetDocument {
    type: DocumentActionType.UPLOAD | DocumentActionType.GET_DOCUMENT_BY_UUID,
    payload: IDocument
}

export type IDocumentAction = IGetDocument;