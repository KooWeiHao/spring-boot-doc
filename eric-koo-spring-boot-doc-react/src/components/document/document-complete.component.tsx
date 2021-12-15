import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {Helmet} from "react-helmet-async";
import {useSelector} from "react-redux";
import {RootState} from "../../store/reducers";
import {faClipboard} from "@fortawesome/free-regular-svg-icons";
import { useNavigate, useParams } from "react-router-dom";
import {useEffect} from "react";
import {useAppDispatch} from "../../store/store";
import {getDocumentByUuid} from "../../store/actions/creators/document.creator";

function DocumentCompleteComponent() {
    const dispatch = useAppDispatch();
    const navigate = useNavigate();
    const params = useParams();
    const document = useSelector((state: RootState) => state.document);

    useEffect(()=>{
        dispatch(getDocumentByUuid(params.uuid as string)).catch(()=>{
            navigate(-1);
        });
    }, [dispatch, navigate, params.uuid]);

    const onCopy = (text: string)=>{
        return navigator.clipboard.writeText(text);
    };

    return (
        <>
            <Helmet>
                <title>Done</title>
            </Helmet>

            <div className={"container-lg row center"}>
                <div className={"card bg-black bg-gradient border-light"}>
                    <div className="card-body">
                        <div className={"text-center"}>
                            <h4 className={"row card-title"}>
                                <div className={"col-12"}>
                                    {document?.name}
                                </div>
                            </h4>
                            <div className={"card-subtitle text-muted mb-2"}>
                                <div className={"row"}>
                                    <div className={"col-12"}>Type: {document?.type}</div>
                                </div>
                                <div className={"row"}>
                                    <div className={"col-12"}>Size: {document?.size} bytes</div>
                                </div>
                            </div>
                        </div>

                        <div className={"card bg-dark"}>
                            <div className="card-body">
                                <div className={"row mb-3"}>
                                    <div className={"col-md-3 col-sm-4 label-text-align align-self-center"}>Preview URL:</div>
                                    <div className={"col-sm-8"}>
                                        <div className="input-group">
                                            <input type="text" className="form-control bg-black bg-opacity-50 text-muted pe-none" value={document?.previewUrl} readOnly/>
                                            <button type={"button"} className="btn btn-success border-light" onClick={() => document?.previewUrl && onCopy(document.previewUrl)}>
                                                <FontAwesomeIcon icon={faClipboard}/>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div className={"row"}>
                                    <div className={"col-md-3 col-sm-4 label-text-align align-self-center"}>Download URL:</div>
                                    <div className={"col-sm-8"}>
                                        <div className="input-group">
                                            <input type="text" className="form-control bg-black bg-opacity-50 text-muted pe-none" value={document?.downloadUrl} readOnly/>
                                            <button type={"button"} className="btn btn-success border-light" onClick={() => document?.downloadUrl && onCopy(document.downloadUrl)}>
                                                <FontAwesomeIcon icon={faClipboard}/>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div className={"pt-2 d-grid gap-2 col-6 mx-auto"}>
                    <button type={"button"} className="btn btn-success" onClick={() => navigate("/document/add")}>UPLOAD AGAIN</button>
                </div>
            </div>
        </>
    );
}

export default DocumentCompleteComponent;