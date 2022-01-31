import React from 'react';
import './caselle.scss'
import ICasellaSocieta from "../../interfaces/caselle/ICasellaSocieta";
import ICasellaStazione from "../../interfaces/caselle/ICasellaStazione";
import CasellaSingleton from "./CasellaSingleton";

interface State {

}

interface Props {
    casella: ICasellaSocieta
}

export class Societa extends React.Component<Props, State> {
    render() {
        let icona: string = "fa-lightbulb";
        let classe: string = "electric-company";
        if (!this.props.casella.nome.includes("Elettrica")){
            icona = "fa-faucet";
            classe = "waterworks"
        }

        let casella : ICasellaSocieta = this.props.casella;
        let style = {}
        if (casella.proprietario !== null){
            style = {backgroundColor: CasellaSingleton.giocatoreColore[casella.proprietario]};
        }
        return <div className={"space utility " + classe} style={style}>
            <div className="container" aria-disabled={this.props.casella.stato.type === "SocietaIpotecata"}>
                <div className="name">{this.props.casella.nome}</div>
                <i className={"drawing fa " + icona}/>
                <div className="price">{this.props.casella.costoBase}</div>
                {this.props.children}
            </div>
        </div>
    }
}