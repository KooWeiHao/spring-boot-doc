import {Helmet} from "react-helmet-async";
import {useDropzone} from "react-dropzone";
import "./document.scss";
import {useState} from "react";
import classNames from "classnames/bind";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faFileAlt, faFileArchive,
    faFileAudio, faFileCode, faFileCsv,
    faFileExcel,
    faFileImage,
    faFilePdf, faFilePowerpoint,
    faFileVideo, faFileWord
} from "@fortawesome/free-solid-svg-icons";
import {upload} from "../../store/actions/creators/document.creator";
import {useAppDispatch} from "../../store/store";
import {useNavigate} from "react-router-dom";

function DocumentAddComponent() {
    const navigate = useNavigate();
    const dispatch = useAppDispatch();
    const [error, setError] = useState("");
    const [fileIcon, setFileIcon] = useState(faFileAlt);
    const [isUploading, setIsUploading] = useState(false);

    const {acceptedFiles, getRootProps, getInputProps} = useDropzone({
        maxFiles:1,
        maxSize:2097152,

        disabled: isUploading,

        onDropAccepted: files => {
            setError("");

            const fileType = files[0].type;
            if(fileType.match('image.*')){
                setFileIcon(faFileImage);
            }
            else if(fileType.match('audio.*')){
                setFileIcon(faFileAudio);
            }
            else if(fileType.match('video.*')){
                setFileIcon(faFileVideo);
            }
            else if(fileType === 'application/pdf'){
                setFileIcon(faFilePdf);
            }
            else if(['application/msword', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document'].includes(fileType)){
                setFileIcon(faFileWord);
            }
            else if(['application/vnd.ms-excel', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'].includes(fileType)){
                setFileIcon(faFileExcel);
            }
            else if(fileType === 'text/csv'){
                setFileIcon(faFileCsv);
            }
            else if(['application/vnd.ms-powerpoint', 'application/vnd.openxmlformats-officedocument.presentationml.presentation'].includes(fileType)){
                setFileIcon(faFilePowerpoint);
            }
            else if(['application/x-bzip', 'application/x-bzip2', 'application/gzip', 'application/zip', 'application/x-7z-compressed', 'application/java-archive', 'application/vnd.rar', 'application/x-tar', 'application/x-zip-compressed'].includes(fileType)){
                setFileIcon(faFileArchive);
            }
            else if(['text/css', 'text/html', 'text/javascript', 'application/json', 'application/x-httpd-php', 'application/x-sh', 'application/xml'].includes(fileType)){
                setFileIcon(faFileCode);
            }
            else {
                setFileIcon(faFileAlt);
            }
        },

        onDropRejected: fileRejections => {
            if(fileRejections.length > 1){
                setError("One document only!");
            } else {
                const errorCode = fileRejections[0].errors[0].code;
                if(errorCode === "file-too-large"){
                    setError("Document is larger than 2MB!");
                }
            }
        }
    });

    const onUpload = ()=>{
        setIsUploading(true);
        const acceptedDocument = acceptedFiles[0];

        dispatch(upload(acceptedDocument)).then(uuid =>{
            navigate(`/document/complete/${uuid}`);
        }).catch(error =>{
            setIsUploading(false);
            switch (error){
                case "max.upload.size.exceeded":
                    setError("Document is larger than 2MB!");
                    break;

                case "one.file.only":
                    setError("One document only!");
                    break;

                case "no.file.selected":
                    setError("No document selected!");
                    break;

                default:
                    setError(`Upload Failed: ${acceptedDocument.name}!`);
            }
        });
    };

    return (
        <>
            <Helmet>
                <title>Upload</title>
            </Helmet>

            <section className="container-lg row center">
                <form onSubmit={ e =>{ e.preventDefault(); onUpload()}}>
                    <div {...getRootProps({className: classNames('dropzone bg-black bg-gradient', {'padding-top-bottom-file-accepted dropzone-accepted': acceptedFiles.length > 0}, {'dropzone-rejected': error}, {'dropzone-disabled': isUploading})})}>
                        {isUploading && (
                            <div className={"position-absolute padding-left-10-vh"}>
                                <div className="spinner-grow me-2 spinner" role="status"/>
                                <div className="spinner-grow me-2 spinner" role="status"/>
                                <div className="spinner-grow me-2 spinner" role="status"/>
                            </div>
                        )}
                        <input {...getInputProps()}/>
                        {acceptedFiles.length <= 0 && (
                            <div className={"padding-left-10-vh"}>Drag and drop your document here, or click to select document</div>
                        )}
                        {acceptedFiles.length > 0 && (
                            <div className={"w-100"}>
                                <div className={"text-center padding-left-10-vh pb-2"}>
                                    <FontAwesomeIcon icon={fileIcon} size={"4x"} className={"file-icon-stroke"}/>
                                </div>
                                <div className="row">
                                    <label className="col-6 text-end pe-0">NAME:</label>
                                    <div className="col-6">{acceptedFiles[0].name}</div>
                                </div>
                                <div className="row">
                                    <label className="col-6 text-end pe-0">SIZE:</label>
                                    <div className="col-6">{acceptedFiles[0].size} bytes</div>
                                </div>
                                <div className="row">
                                    <label className="col-6 text-end pe-0">TYPE:</label>
                                    <div className="col-6">{acceptedFiles[0].type}</div>
                                </div>
                            </div>
                        )}
                    </div>
                    <aside className={"ps-0 pe-0"}>
                        <div className="error fst-italic">
                            {error}
                        </div>
                    </aside>

                    <div className={"pt-2 d-grid gap-2 col-6 mx-auto"}>
                        <button type={"submit"} className="btn btn-success" disabled={acceptedFiles.length <=0 || isUploading}>UPLOAD</button>
                    </div>
                </form>
            </section>
        </>
    );
}

export default DocumentAddComponent;