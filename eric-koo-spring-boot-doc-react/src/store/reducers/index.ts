import {combineReducers} from "redux";
import documentReducer from "./document.reducer";

const reducers =  combineReducers({
    document: documentReducer
});

export default reducers;

export type RootState = ReturnType<typeof reducers>;
