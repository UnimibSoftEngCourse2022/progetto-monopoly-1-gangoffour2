import React from 'react';
import ICasellaTerreno from '../../interfaces/caselle/ICasellaTererno'
import './caselle.scss'
import ICasellaStazione from "../../interfaces/caselle/ICasellaStazione";

interface State {

}

interface Props {
    casella: ICasellaStazione
}

export class Stazione extends React.Component<Props, State> {

    constructor(props: Props) {
        super(props);
    }

    render() {
        return <div className="space railroad">
            <div className="container">
                <div className="name">{this.props.casella.nome}</div>
                    <i className="drawing fa fa-subway"/>
                    <div className="price">{this.props.casella.costoBase}</div>
            </div>
        </div>
    }
}