import React, {FunctionComponent} from "react";
import ICasellaTerreno from "../../../../interfaces/caselle/ICasellaTererno";
import StompController from "../../../../application/stompController";

interface Props {
    casella: ICasellaTerreno
}

const DowngradeBtn: FunctionComponent<Props> = (props) => {

    const downgradeTerreno = () => StompController.downgradeTerreno(props.casella)

    return <button onClick={downgradeTerreno}><i className="fas fa-angle-down"></i></button>
}

export default DowngradeBtn