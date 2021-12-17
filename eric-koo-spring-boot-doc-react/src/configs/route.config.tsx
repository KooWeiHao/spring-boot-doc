import {Navigate, Route, Routes} from "react-router-dom";
import React from "react";
import DocumentAddComponent from "../components/document/document-add.component";
import DocumentCompleteComponent from "../components/document/document-complete.component";

function RouteConfig() {
    return (
        <Routes>
            <Route path="/eric-koo" element={<Navigate to={"/eric-koo/document/add"}/>}/>
            <Route path="*" element={<Navigate to={"/eric-koo"}/>}/>

            <Route path="eric-koo/document/add" element={<DocumentAddComponent />} />
            <Route path="eric-koo/document/complete/:uuid" element={<DocumentCompleteComponent />} />
        </Routes>
    );
}

export default RouteConfig;
