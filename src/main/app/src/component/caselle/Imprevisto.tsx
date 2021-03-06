import React from 'react';
import './caselle.scss'
import {ICasellaImprevisto} from "../../interfaces/caselle/ICasellaImprevisto";

interface State {

}

interface Props {
    casella: ICasellaImprevisto
}

export class Imprevisto extends React.Component<Props, State> {
    render() {
        return <div className="space chance">
            <div className="container">
                <div className="name">{this.props.casella.nome}</div>
                <div className="name">&nbsp; </div>
                <i className="drawing fa fa-question"/>
                {this.props.children}
            </div>
        </div>


    }
}