import React from "react";
import CasellaSingleton from "../../../component/caselle/CasellaSingleton";
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

        let jsxTurno = <div/>;
        if (partita.turnoCorrente){
            jsxTurno = <h3>Turno di {partita.turnoCorrente.giocatore.nick}</h3>;
        }

        return <div className={"container_menu float_classifica"}>
            <h1>Classifica</h1>

            <hr className="normale"/>
            {partita.giocatori.map(giocatore =>
                <div key={giocatore.nick} className="giocatore-classifica">
               <span className="palla" style={{backgroundColor: CasellaSingleton.giocatoreColore[giocatore.nick]}}/>
                    <div className={"testo-classifica"}>
                        {giocatore.nick + ": " + giocatore.conto + "€"}
                    </div>
            </div>)}

            <hr className={"rounded"}/>
            {jsxTurno}
            <h3>Stato: {partita.stato.type}</h3>
        </div>;
    }
}