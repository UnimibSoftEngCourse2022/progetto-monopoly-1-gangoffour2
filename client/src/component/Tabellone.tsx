import React from 'react';
import ICasella, {AllCaselle} from "../interfaces/caselle/ICasella";
import './caselle/caselle.scss';
import ICasellaTererno from "../interfaces/caselle/ICasellaTererno";
import {Casella} from "./caselle/Casella";

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
        this.props.caselle.map(el => el as AllCaselle).filter(c => c.type === "Imprevisto").forEach(casella => {
            jsxCaselle.push(<Casella {...casella}/>)
        });

        return <div className="board">
            <div className={"center"}/>
            {jsxCaselle}
        </div>
    }
}