import React from 'react';
import './caselle.scss'
import {ICasellaTassa} from "../../interfaces/caselle/ICasellaTassa";

interface State {

}

interface Props {
    casella: ICasellaTassa
}

export class Tassa extends React.Component<Props, State> {

    constructor(props: Props) {
        super(props);
    }

    render() {
        return <div className="space fee luxury-tax">
            <div className="container">
                <div className="name-tassa">{this.props.casella.nome}</div>
                <div className="drawing fa fa-diamond"/>
                <div className="price">{this.props.casella.costo}</div>
                {this.props.children}
            </div>
        </div>

    }
}