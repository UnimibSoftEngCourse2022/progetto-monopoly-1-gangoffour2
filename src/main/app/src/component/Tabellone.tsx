import React from 'react';
import ICasella from "../interfaces/caselle/ICasella";
import './caselle/caselle.scss';
import {Casella} from "./caselle/Casella";
import IGiocatore from "../interfaces/IGiocatore";
import "./tabellone.css"

interface Props{
    caselle: ICasella[],
    giocatori: IGiocatore[]
}

interface State {

}

export default class Tabellone extends React.Component<Props, State> {
    costruisciRiga(inizio: number, fine: number, classe: string, reverse: boolean){
        let riga : JSX.Element[] = []
        this.props.caselle.slice(inizio, fine).forEach((casella: ICasella) => {
            riga.push(<Casella key={casella.id} {...casella}/>);
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
        jsxCaselle.push(<Casella key={this.props.caselle[0].id} {...this.props.caselle[0]}/>);
        jsxCaselle.push(this.costruisciRiga(1, 10, "row horizontal-row bottom-row", true));
        jsxCaselle.push(<Casella key={this.props.caselle[10].id} {...this.props.caselle[10]}/>);
        jsxCaselle.push(this.costruisciRiga(11, 20, "row vertical-row left-row", true));
        jsxCaselle.push(<Casella key={this.props.caselle[20].id} {...this.props.caselle[20]}/>);
        jsxCaselle.push(this.costruisciRiga(21, 30, "row horizontal-row top-row", false));
        jsxCaselle.push(<Casella key={this.props.caselle[30].id} {...this.props.caselle[30]}/>);
        jsxCaselle.push(this.costruisciRiga(31, 40, "row vertical-row right-row", false));

        return <div className={"container_tabellone"}>
            <div className="board">
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
        </div>
    }
}