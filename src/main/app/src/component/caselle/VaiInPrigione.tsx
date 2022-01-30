import React from 'react';
import './caselle.scss'
import {ICasellaVaiInPrigione} from "../../interfaces/caselle/ICasellaVaiInPrigione";

interface State {

}

interface Props {
    casella: ICasellaVaiInPrigione
}

export class VaiInPrigione extends React.Component<Props, State> {
    render() {
        return <div className="space corner go-to-jail">
            <div className="container">
                <div className="name">Vai in</div>
                <i className="drawing fa fa-gavel"/>
                <div className="name">Prigione</div>
                {this.props.children}
            </div>
        </div>

    }
}