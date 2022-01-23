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
                <div className="name">{this.props.casella.name}</div>
                <div className="drawing fa fa-diamond"></div>
                <div className="instructions">{this.props.casella.name}</div>
            </div>
        </div>

    }
}