import React from "react";
import './carta.css';
import ICasellaStazione from "../../interfaces/caselle/ICasellaStazione";

interface Props{
    casella: ICasellaStazione;
}

export default class CartaStazione extends React.Component<Props, {}>{


    render() : JSX.Element {

        let {casella} = this.props;
        return <div className="table-carta">
            <div className="carta-d">
                <summary className="terreno-carta grigio">
                    <span className="eyebrow"/>
                    {casella.nome}
                </summary>
                <p className="hotel">Rendita: {casella.rendita}</p>
                <p className="disclaimer">If a player owns ALL the Lots of any Color-Group, the<br/>rent is Doubled on
                    Unimproved Lots in that group.<br/>&copy;1935 Hasbro, Inc.</p>
            </div>
        </div>
    }
}
