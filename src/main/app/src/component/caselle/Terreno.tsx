import React from 'react';
import ICasellaTerreno from '../../interfaces/caselle/ICasellaTererno'
import './caselle.scss'


interface State {

}

interface Props {
    casella: ICasellaTerreno,
    caHover: () => {}
}

export class Terreno extends React.Component<Props, State> {

    render() {

        const caseJsx: JSX.Element[] = [];

        for(let i = 0; i < this.props.casella.numeroCase; ++i){
            caseJsx.push(<div className={"casa"}></div>)
        }

        return <div className="space property" onMouseLeave={this.props.caHover} onMouseEnter={this.props.caHover}>
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