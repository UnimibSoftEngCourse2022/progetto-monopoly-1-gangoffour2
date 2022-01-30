import React from "react";
import Popup from "../../../component/popup/Popup";
import StompController from "../../../application/stompController";
import PopupButtonContainer from "../../../component/popup/PopupButtonContainer";
import {PopupProps} from "./PopupRouterAzioni";
import ICasella from "../../../interfaces/caselle/ICasella";
import translateCarteProprieta from "./TranslateCarteProprieta";




interface State {

}

export default class PopupAcquisto extends React.Component<PopupProps, State> {

    render() {

        const nome = this.props.giocatore.casellaCorrente.nome;
        let casellaCorrente: ICasella = this.props.giocatore.casellaCorrente;
        let testo = "";
        if(!this.props.isMioTurno)
            testo = "Il giocatore " + this.props.giocatore.nick + " è finito sulla proprietà: " + nome + " attendi che la compri o la metta all'asta"

        return (
            <div>
                <Popup trigger={true} onClose={() => {}} title={"Acquista la proprietà"}>
                    <div className={"container-popup-acquisto"}>
                        <p>{testo}</p>
                        {this.props.isMioTurno &&
                            <PopupButtonContainer>
                                <button onClick={() => StompController.acquista()}>Compra</button>
                                <button onClick = {() => StompController.avviaAsta(this.props.giocatore.casellaCorrente)}>Metti all'asta</button>
                            </PopupButtonContainer>
                        }
                        {translateCarteProprieta[casellaCorrente.type](casellaCorrente)}
                    </div>
                </Popup>
            </div>
        )
    }
}