import React, {FunctionComponent} from "react";
import ICasellaProprieta from "../../../../interfaces/caselle/ICasellaProprieta";
import StompController from "../../../../application/stompController";

interface Props {
    casella: ICasellaProprieta
}

const IpotecaBtn: FunctionComponent<Props> = (props) => {

    const ipoteca = () => StompController.ipoteca(props.casella);

    return <button onClick={ipoteca}><i className="fas fa-dollar-sign"></i></button>
}

export default IpotecaBtn;