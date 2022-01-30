import React, {FunctionComponent} from "react";
import ICasellaTerreno from "../../../../interfaces/caselle/ICasellaTererno";
import StompController from "../../../../application/stompController";

interface Props {
    casella: ICasellaTerreno
}

const UpgradeBtn: FunctionComponent<Props> = (props) => {

    const upgradeTerreno = () => StompController.upgradeTerreno(props.casella)

    return <button onClick={upgradeTerreno}><i className="fas fa-angle-up"></i></button>
}

export default UpgradeBtn