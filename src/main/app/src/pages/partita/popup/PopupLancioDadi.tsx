import React from "react";
import Popup from "../../../component/popup/Popup";
import StompController from "../../../application/stompController";
import PopupButtonContainer from "../../../component/popup/PopupButtonContainer";
import {PopupProps} from "./PopupRouterAzioni";

interface State {

}

export default class PopupLancioDadi extends React.Component<PopupProps, State> {

    render() {

        const nome = this.props.giocatore.nick;
        let testo = "";
        if (!this.props.isMioTurno) {
            testo = "Il giocatore " + this.props.giocatore.nick + "sta lanciando i dadi";
        }

        return (
            <div>
                <Popup trigger={true} onClose={() => {
                }} title={"È il turno di " + nome}>
                    <div className={"container-popup-acquisto"}>
                        <p>{testo}</p>
                        {this.props.isMioTurno &&
                            <PopupButtonContainer>
                                <button onClick={() => StompController.lanciaDadi()}>Lancia i dadi</button>
                            </PopupButtonContainer>
                        }
                    </div>
                </Popup>
            </div>
        )
    }
}
