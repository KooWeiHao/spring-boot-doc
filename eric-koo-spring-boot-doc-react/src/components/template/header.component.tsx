import {useEffect} from "react";
import { Link } from "react-router-dom";
import {ReactComponent as LogoImage} from "../../assets/images/logo.svg";

function HeaderComponent(){
    useEffect(()=>{
        document.body.style.paddingTop = "70px";
        document.body.style.paddingBottom = "70px";
        return ()=>{
            document.body.style.paddingTop = "unset";
            document.body.style.paddingBottom = "unset";
        }
    });

    return (
        <nav className="container-fluid navbar navbar-expand-lg navbar-dark shadow-sm mb-1 fixed-top bg-gradient font-bio-rhyme-light">
            <Link className="navbar-brand mb-0 h1 d-flex align-items-center" to={'/document/add'}>
                <LogoImage className="align-top me-2"/>
                Eric Koo's Document
            </Link>
        </nav>
    );
}

export default HeaderComponent;
