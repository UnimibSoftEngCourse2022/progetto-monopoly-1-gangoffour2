import React from 'react';
import ICasellaPrigione from "../../interfaces/caselle/CasellaPrigione";

interface Props {
    casella: ICasellaPrigione
}

interface State {
}

export default class Via extends React.Component<Props, State>{
    constructor(props: Props) {
        super(props);
    }

    render(){
        return <div className="space corner go">
            <div className="container">
                <div className="instructions">Collect $200.00 salary as you pass</div>
                <div className="go-word">go</div>
            </div>
            <div className="arrow fa fa-long-arrow-left"/>
        </div>
    }
}
