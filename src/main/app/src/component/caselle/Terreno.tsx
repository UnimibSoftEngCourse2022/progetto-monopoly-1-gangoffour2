import React from 'react';
import ICasellaTerreno from '../../interfaces/caselle/ICasellaTererno'
import './caselle.scss'
import CasellaSingleton from "./CasellaSingleton";


interface State {

}

interface Props {
    casella: ICasellaTerreno,
    caHover: () => {}
}

export class Terreno extends React.Component<Props, State> {

    render() {
        let casella : ICasellaTerreno = this.props.casella;
        let style = {}
        if (casella.proprietario !== null){
            let colore = CasellaSingleton.giocatoreColore[casella.proprietario];
            style = {backgroundColor: colore};
        }
        const caseJsx: JSX.Element[] = [];

        for(let i = 0; i < this.props.casella.numeroCase; ++i){
            caseJsx.push(<div className={"casa"}></div>)
        }

        return <div className="space property" onMouseLeave={this.props.caHover} onMouseEnter={this.props.caHover} style={style}>
            <div className = "container" aria-disabled={this.props.casella.stato.type === "TerrenoIpotecato"}>
                <div className={"color-bar " + this.props.casella.colore}>
                    {
                        this.props.casella.hasAlbergo ? <div className={"albergo"}></div> : caseJsx
                    }
                </div>
                <div className="name three-line-name">{this.props.casella.nome}</div>
                <div className="price">{this.props.casella.costoBase}</div>
                {this.props.children}
            </div>
        </div>
    }
}