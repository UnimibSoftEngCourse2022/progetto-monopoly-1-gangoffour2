import React from 'react';
import ICasellaTerreno from '../../interfaces/caselle/ICasellaTererno'
import './caselle.scss'
import CasellaSingleton from "./CasellaSingleton";


interface State {

}

interface Props {
    casella: ICasellaTerreno
}

export class Terreno extends React.Component<Props, State> {
    render() {
        let casella : ICasellaTerreno = this.props.casella;
        let style = {}
        if (casella.proprietario !== null){
            let colore = CasellaSingleton.giocatoreColore[casella.proprietario];
            style = {backgroundColor: colore};
        }
        let opacity = style !== {} ? 0.5 : 1
        return <div className="space property" style={style}>
            <div className = "container" aria-disabled={this.props.casella.stato.type === "TerrenoIpotecato"}>
                <div className={"color-bar " + casella.colore}/>
                <div className="name three-line-name">{this.props.casella.nome}</div>
                <div className="price">{this.props.casella.costoBase}</div>
                {this.props.children}
            </div>
        </div>
    }
}