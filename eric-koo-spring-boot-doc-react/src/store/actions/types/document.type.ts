import IDocument from "../../../types/document.type";

export enum DocumentActionType {
    UPLOAD = "UPLOAD"
}

interface IAddDocument {
    type: DocumentActionType.UPLOAD,
    payload: IDocument
}

export type IDocumentAction = IAddDocument;