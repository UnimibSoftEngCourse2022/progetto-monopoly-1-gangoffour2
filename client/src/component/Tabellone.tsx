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


    costruisciRiga(inizio: number, fine: number, classe: string, reverse: boolean){
        let riga : JSX.Element[] = []
        this.props.caselle.slice(inizio, fine).forEach((casella: ICasella) => {
            console.log(casella.nome);
            riga.push(<Casella {...casella}/>);
        });
        if (reverse){
            riga = riga.reverse();
        }
        return <div className={classe}>
            {riga}
        </div>;
    }

    render() : JSX.Element {
        let jsxCaselle = []
        jsxCaselle.push(<Casella {...this.props.caselle[0]}/>);
        jsxCaselle.push(this.costruisciRiga(1, 10, "row horizontal-row bottom-row", true));
        jsxCaselle.push(<Casella {...this.props.caselle[10]}/>);
        jsxCaselle.push(this.costruisciRiga(11, 20, "row vertical-row left-row", true));
        jsxCaselle.push(<Casella {...this.props.caselle[20]}/>);
        jsxCaselle.push(this.costruisciRiga(21, 30, "row horizontal-row top-row", false));
        jsxCaselle.push(<Casella {...this.props.caselle[30]}/>);
        jsxCaselle.push(this.costruisciRiga(31, 40, "row vertical-row right-row", false));

        return <div className="board">
            <div className="center">
                <div className="community-chest-deck">
                    <h2 className="label">Probabilita</h2>
                    <div className="deck"/>
                </div>
                <h1 className="title">GANG OF FOUR 2</h1>
                <div className="chance-deck">
                    <h2 className="label">Imprevisti</h2>
                    <div className="deck"/>
                </div>
            </div>
            {jsxCaselle}
        </div>
    }
}