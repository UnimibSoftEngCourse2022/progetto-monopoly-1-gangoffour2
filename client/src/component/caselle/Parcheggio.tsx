import React from 'react';
import './caselle.scss'
import ICasellaParcheggio from "../../interfaces/caselle/ICasellaParcheggio";

interface State {

}

interface Props {
    casella: ICasellaParcheggio
}

export class Parcheggio extends React.Component<Props, State> {

    constructor(props: Props) {
        super(props);
    }

    render() {
        return <div className="space corner free-parking">
            <div className="container">
                <div className="name">Free</div>
                <i className="drawing fa fa-car"/>
                <div className="name">Parking</div>
                {this.props.children}
            </div>
        </div>

    }
}