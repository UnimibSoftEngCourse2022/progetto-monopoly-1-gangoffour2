import React, {FunctionComponent, useState} from "react";
import ICasellaProprieta from "../../../interfaces/caselle/ICasellaProprieta";
import ICasellaTererno from "../../../interfaces/caselle/ICasellaTererno";
import "./proprieta.css"
import StompController from "../../../application/stompController";

const translate: {[key: string]: (props: ICasellaProprieta) => JSX.Element} = {
    "Terreno": (props) => <>
        <button><i className="fas fa-angle-up"></i></button>
        <button><i className="fas fa-angle-down"></i></button>

    </>,
}

const translateDescrizione: {[key: string]: (casella: ICasellaProprieta) => string} = {
    "Terreno": casella => "Case possedute: " + (casella as ICasellaTererno).listaCase?.length ?? 0,
}

const Proprieta: FunctionComponent<ICasellaProprieta> = (proprieta) => {

    const [open, set_open] = useState(false)

    const avviaAsta = () => StompController.avviaAsta();
    const ipoteca = () => StompController.ipoteca(proprieta);

    return (
        <div className={"property_list_element"}>
            <div className={"property_list_dropdown"}>
                <div>
                    <div>
                        {proprieta.nome}
                    </div>
                    <div className={"subtitle_dashboard"}>
                        {translateDescrizione[proprieta.type] !== undefined ?
                            translateDescrizione[proprieta.type](proprieta) :
                            "Valore ipoteca: " + proprieta.ipoteca
                        }
                    </div>
                </div>
                <button onClick={() => set_open(!open)}><i className="fas fa-chevron-down"></i></button>
            </div>
            <div className={"open_close_property"} aria-selected={open}>
                <button onClick={avviaAsta}><i className="fas fa-gavel"></i></button>
                <button onClick={ipoteca}><i className="fas fa-dollar-sign"></i></button>
                {translate[proprieta.type] !== undefined ?
                    translate[proprieta.type](proprieta) :
                    <div></div>
                }
            </div>
        </div>
    )
}

export default Proprieta