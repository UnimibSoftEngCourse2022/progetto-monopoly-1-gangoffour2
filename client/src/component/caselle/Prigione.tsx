import React from 'react';
import ICasellaPrigione from "../../interfaces/caselle/CasellaPrigione";

interface Props {
    casella: ICasellaPrigione
}

interface State {
}

export default class Prigione extends React.Component<Props, State>{
    constructor(props: Props) {
        super(props);
    }

    render(){
        return <div className="space corner jail">
            <div className="just">Transito</div>
            <div className="drawing">
                <div className="container">
                    <div className="name">In</div>
                    <div className="window">
                        <div className="bar"/>
                        <div className="bar"/>
                        <div className="bar"/>
                        <i className="person fa fa-frown-o"/>
                    </div>
                    <div className="name">Prigione</div>
                </div>
            </div>
            <div className="visiting">Transito</div>
        </div>
    }
}