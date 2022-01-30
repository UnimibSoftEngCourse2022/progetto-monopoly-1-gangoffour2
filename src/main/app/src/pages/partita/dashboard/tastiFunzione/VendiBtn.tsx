import React, {FunctionComponent} from "react";
import ICasellaProprieta from "../../../../interfaces/caselle/ICasellaProprieta";

interface Props {
    casella: ICasellaProprieta
}

const VendiBtn: FunctionComponent<Props> = (props) => {

    const vendi = () => {}//StompController.ipoteca(props.casella);

    return <button onClick={vendi}><i className="fas fa-gavel"></i></button>
}

export default VendiBtn