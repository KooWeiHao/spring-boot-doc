import IDocument from "../../types/document.type";
import {DocumentActionType, IDocumentAction} from "../actions/types/document.type";

const initialState: IDocument = {
    uuid: "",
    name: "",
    type: "",
    size: 0,
    previewUrl: "",
    downloadUrl: ""
};

const documentReducer = (state: IDocument = initialState, action: IDocumentAction)=>{
    const {type, payload} = action;

    switch (type) {
        case DocumentActionType.UPLOAD:
        case DocumentActionType.GET_DOCUMENT_BY_UUID:
            return payload;

        default:
            return state;
    }
};

export default documentReducer;