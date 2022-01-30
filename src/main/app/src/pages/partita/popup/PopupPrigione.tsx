import React from "react";
import Popup from "../../../component/popup/Popup";
import StompController from "../../../application/stompController";
import PopupButtonContainer from "../../../component/popup/PopupButtonContainer";
import {PopupProps} from "./PopupRouterAzioni";;


interface State {

}

export default class PopupPrigione extends React.Component<PopupProps, State> {

    render() {

        const nome = this.props.giocatore.casellaCorrente.nome;
        let testo = "";
        if(!this.props.isMioTurno)
            testo = "Il giocatore " + this.props.giocatore.nick + " è in prigione! Press F to pay respects"
        else {
            testo = "Sei finito in prigione! vuoi tentare la fortuna?"
        }
        return (
            <div>
                <Popup trigger={true} onClose={() => {}} title={"WASTED"}>
                    <div className={"container-popup-acquisto"}>
                        <p>{testo}</p>
                        {this.props.isMioTurno &&
                            <PopupButtonContainer>
                                <button onClick={() => StompController.lanciaDadi()}>Tenta un doppio</button>
                                <button onClick = {() => StompController.paga()}>Paga la cauzione</button>
                            </PopupButtonContainer>
                        }
                    </div>
                </Popup>
            </div>
        )
    }
}
