import React from 'react';
import './caselle.scss'
import {ICasellaProbabilita} from "../../interfaces/caselle/ICasellaProbabilita";

interface State {

}

interface Props {
    casella: ICasellaProbabilita
}

export class Probabilita extends React.Component<Props, State> {

    constructor(props: Props) {
        super(props);
    }

    render() {
        return <div className="space community-chest">
            <div className="container">
                <div className="name">{this.props.casella.nome}</div>
                <i className="drawing fa fa-cube"/>
                <div className="instructions">Follow instructions on top card</div>
            </div>
        </div>

    }
}