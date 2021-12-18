import {Navigate, Route, Routes} from "react-router-dom";
import React from "react";
import DocumentAddComponent from "../components/document/document-add.component";
import DocumentCompleteComponent from "../components/document/document-complete.component";

function RouteConfig() {
    return (
        <Routes>
            <Route path="*" element={<Navigate to={"document"}/>}/>

            <Route path="document">
                <Route index element={<Navigate to={"add"}/>}/>
                <Route path="add" element={<DocumentAddComponent />}/>
                <Route path="complete/:uuid" element={<DocumentCompleteComponent />}/>
            </Route>
        </Routes>
    );
}

export default RouteConfig;
