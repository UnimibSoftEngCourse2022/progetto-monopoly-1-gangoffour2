import React from 'react';
import './caselle.scss'
import ICasellaStazione from "../../interfaces/caselle/ICasellaStazione";

interface State {

}

interface Props {
    casella: ICasellaStazione
}

export class Stazione extends React.Component<Props, State> {
    render() {
        return <div className="space railroad">
            <div className="container" aria-disabled={this.props.casella.stato.type === "StazioneIpotecata"}>
                <div className="name">{this.props.casella.nome}</div>
                    <i className="drawing fa fa-subway"/>
                    <div className="price">{this.props.casella.costoBase}</div>
                {this.props.children}
            </div>
        </div>
    }
}