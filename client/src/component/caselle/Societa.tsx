import React from 'react';
import './caselle.scss'
import ICasellaSocieta from "../../interfaces/caselle/ICasellaSocieta";

interface State {

}

interface Props {
    casella: ICasellaSocieta
}

export class Societa extends React.Component<Props, State> {

    constructor(props: Props) {
        super(props);
    }

    render() {
        return <div className="space utility electric-company">
            <div className="container">
                <div className="name">{this.props.casella.nome}</div>
                <i className="drawing fa fa-lightbulb-o"></i>
                <div className="price">{this.props.casella.costoBase}</div>
            </div>
        </div>

    }
}