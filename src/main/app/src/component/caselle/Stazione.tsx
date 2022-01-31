import React from 'react';
import './caselle.scss'
import ICasellaStazione from "../../interfaces/caselle/ICasellaStazione";
import ICasellaTerreno from "../../interfaces/caselle/ICasellaTererno";
import CasellaSingleton from "./CasellaSingleton";

interface State {

}

interface Props {
    casella: ICasellaStazione,
    caHover: () => {}
}

export class Stazione extends React.Component<Props, State> {

    render() {

        let casella : ICasellaStazione = this.props.casella;
        let style = {}
        if (casella.proprietario !== null){
            style = {backgroundColor: CasellaSingleton.giocatoreColore[casella.proprietario]};
        }
        return <div className="space railroad" style={style}>
            <div className="container" onMouseLeave={this.props.caHover} onMouseEnter={this.props.caHover} aria-disabled={this.props.casella.stato.type === "StazioneIpotecata"}>
                <div className="name">{this.props.casella.nome}</div>
                    <i className="drawing fa fa-subway"/>
                    <div className="price">{this.props.casella.costoBase}</div>
                {this.props.children}
            </div>
        </div>
    }
}