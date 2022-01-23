import React from 'react';
import ICasella from "../interfaces/caselle/ICasella";
import 'caselle/caselle.scss';

interface Props{
    caselle: ICasella[]
}

interface State {

}

export default class Tabellone extends React.Component<Props, State> {
    constructor(props: Props){
        super(props);
    }

    render() : JSX.Element {
        let jsxCaselle : JSX.Element[] = []
        this.props.caselle.forEach(casella => {

        });

        return <div className="board">
            {jsxCaselle}
        </div>
    }
}