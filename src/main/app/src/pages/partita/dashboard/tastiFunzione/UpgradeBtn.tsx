import React, {FunctionComponent} from "react";
import ICasellaTerreno from "../../../../interfaces/caselle/ICasellaTererno";

interface Props {
    casella: ICasellaTerreno
}

const UpgradeBtn: FunctionComponent<Props> = (props) => {

    const upgradeTerreno = () => {}//StompController.ipoteca(props.casella);

    return <button onClick={upgradeTerreno}><i className="fas fa-angle-up"></i></button>
}

export default UpgradeBtn