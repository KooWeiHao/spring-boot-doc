import IDocument from "../../types/document.type";
import {DocumentActionType, IDocumentAction} from "../actions/types/document.type";

const initialState: IDocument | null = null;

const documentReducer = (state: IDocument | null = initialState, action: IDocumentAction)=>{
    const {type, payload} = action;

    switch (type) {
        case DocumentActionType.UPLOAD:
            return payload;

        default:
            return state;
    }
};

export default documentReducer;