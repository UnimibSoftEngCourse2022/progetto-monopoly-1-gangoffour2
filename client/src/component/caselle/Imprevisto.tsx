import React from 'react';
import './caselle.scss'
import {ICasellaImprevisto} from "../../interfaces/caselle/ICasellaImprevisto";

interface State {

}

interface Props {
    casella: ICasellaImprevisto
}

export class Imprevisto extends React.Component<Props, State> {

    constructor(props: Props) {
        super(props);
    }

    render() {
        return <div className="space chance">
            <div className="container">
                <div className="name">{this.props.casella.name}</div>
                <i className="drawing fa fa-question red"></i>
            </div>
        </div>

    }
}