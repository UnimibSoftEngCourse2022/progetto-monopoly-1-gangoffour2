import React from "react";
import Popup from "../../../component/popup/Popup";
import StompController from "../../../application/stompController";
import PopupButtonContainer from "../../../component/popup/PopupButtonContainer";
import {PopupProps} from "./PopupRouterAzioni";

interface State {

}

export default class PopupFineTurno extends React.Component<PopupProps, State> {

    render() {

        let testo = "";
        if(!this.props.isMioTurno)
            testo = "Il giocatore " + this.props.giocatore.nick + " sta terminando il suo turno"
        else {
            testo = "Scegli un'azione da eseguire"
        }
        return (
            <div>
                <Popup trigger={true} onClose={() => {}} title={"Fine del turno corrente"}>
                    <div className={"container-popup-acquisto"}>
                        <p>{testo}</p>
                        {this.props.isMioTurno &&
                            <PopupButtonContainer>
                                <button onClick={() => StompController.fineTurno()}>Finisci il turno</button>
                            </PopupButtonContainer>
                        }
                    </div>
                </Popup>
            </div>
        )
    }
}
