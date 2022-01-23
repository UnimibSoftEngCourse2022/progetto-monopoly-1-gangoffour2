import React from 'react';
import ICasellaTerreno from '../../interfaces/caselle/ICasellaTererno'
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
        return <div className="property">
            <div className = "container">
                <div className={"color-bar " + this.props.casella.colore}/>
                <div className="name">{this.props.casella.name}</div>
                <div className="price">{this.props.casella.costoBase}</div>
            </div>
        </div>
    }
}