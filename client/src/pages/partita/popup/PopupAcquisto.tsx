import React from "react";
import Popup from "../../../component/popup/Popup";
import StompController from "../../../application/stompController";
import PopupButtonContainer from "../../../component/popup/PopupButtonContainer";
import {PopupProps} from "./PopupRouter";


interface State {

}

export default class PopupAcquisto extends React.Component<PopupProps, State> {

    render() {

        const nome = this.props.giocatore.casellaCorrente.nome;

        let testo = "";
        if(this.props.isMioTurno)
            testo = "Sei finito sulla proprietà " + nome + " comprala o mettila all'asta";
        else
            testo = "Il giocatore " + this.props.giocatore.nick + " è finito sulla proprietà: " + nome + " attendi che la compri o la metta all'asta"

        return (
            <div>
                <Popup trigger={true} onClose={() => {}} title={"Acquista la proprietà"}>
                    <p>{testo}</p>
                    {this.props.isMioTurno &&
                        <PopupButtonContainer>
                            <button onClick={() => StompController.acquista()}>Compra</button>
                            <button>Metti all'asta</button>
                        </PopupButtonContainer>
                    }
                </Popup>
            </div>
        )
    }
}