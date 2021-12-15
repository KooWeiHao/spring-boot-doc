import React, {useEffect} from 'react';
import {Helmet} from "react-helmet-async";
import './App.scss';
import RouteConfig from "./configs/route.config";
import FooterComponent from "./components/template/footer.component";
import HeaderComponent from "./components/template/header.component";

import { library } from '@fortawesome/fontawesome-svg-core';
import {fas} from '@fortawesome/free-solid-svg-icons';
import {fab} from '@fortawesome/free-brands-svg-icons';
import {far} from '@fortawesome/free-regular-svg-icons';
library.add(fas, fab, far);

function App() {
    useEffect(() =>{
        document.body.classList.add("bg-dark", "text-light");
    });

    return (
        <div className={"font-epilogue"}>
            <Helmet defaultTitle={"Eric Koo's Document"} titleTemplate={"Eric Koo's Document - %s"}/>

            <HeaderComponent />
            <FooterComponent />

            <RouteConfig />
        </div>
    );
}

export default App;
