import React from "react";
import IPartita from "../../../interfaces/IPartita";
import "./classifica.css"

interface Props {
    partita: IPartita
}

interface State {

}

export class Classifica extends React.Component<Props, State> {

    render() {

        const {partita} = this.props;

        return <div className={"container_menu float_classifica"}>
            <h2>Classifica</h2>
            {partita.giocatori.map(giocatore => <div key={giocatore.nick}>
                {giocatore.nick + ": " + giocatore.conto + "€"}
            </div>)}
        </div>;
    }
}