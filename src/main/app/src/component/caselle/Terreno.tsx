import React from 'react';
import ICasellaTerreno, {Colore} from '../../interfaces/caselle/ICasellaTererno'
import './caselle.scss'


interface State {

}

interface Props {
    casella: ICasellaTerreno
}

export class Terreno extends React.Component<Props, State> {

    constructor(props: Props) {
        super(props);
    }

    render() {
        return <div className="space property">
            <div className = "container">
                <div className={"color-bar " + this.props.casella.colore}/>
                <div className="name three-line-name">{this.props.casella.nome}</div>
                <div className="price">{this.props.casella.costoBase}</div>
                {this.props.children}
            </div>
        </div>
    }
}