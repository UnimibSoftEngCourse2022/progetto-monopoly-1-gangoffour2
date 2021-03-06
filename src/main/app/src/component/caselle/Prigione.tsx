import React from 'react';
import ICasellaPrigione from "../../interfaces/caselle/CasellaPrigione";

interface Props {
    casella: ICasellaPrigione
}

interface State {
}

export default class Prigione extends React.Component<Props, State>{
    render(){
        return <div className="space corner jail">
            <div className="just">Transito</div>
            <div className="drawing">
                <div className="container">
                    {this.props.children}
                    <div className="name-prigione">In</div>
                    <div className="window">
                        <div className="bar"/>
                        <div className="bar"/>
                        <div className="bar"/>
                        <i className="person fa fa-poo"/>
                    </div>
                    <div className="name-prigione">Prigione</div>
                </div>
            </div>
            <div className="visiting">Transito</div>
        </div>
    }
}