import React from "react";
import Popup from "../../../component/popup/Popup";
import StompController from "../../../application/stompController";
import PopupButtonContainer from "../../../component/popup/PopupButtonContainer";
import {PopupProps} from "./PopupRouter";
import IStatoAsta from "../../../interfaces/stati/partita/IStatoAsta";
import TextInput from "../../../component/textInput/TextInput";
import translateCarteProprieta from "./TranslateCarteProprieta";
import {Simulate} from "react-dom/test-utils";

interface State {
    input:number
}

export default class PopupAsta extends React.Component<PopupProps, State> {
    constructor(props: PopupProps) {
        super(props);
        let prezzoBase: number = (this.props.partita.stato as IStatoAsta).astaCorrente.offertaAttuale;
        this.state = {
            input: prezzoBase
        };
    }
    render() {

        const stato = this.props.partita.stato as IStatoAsta

        return (
            <div>
                <Popup trigger={true} onClose={() => {}} title={"Asta in corso"}>
                    <div className={"container-popup-acquisto"}>
                        <p>Migliore offerta: {stato.astaCorrente.offertaAttuale}</p>
                        <p>Migliore offerente:
                            {stato.astaCorrente.miglioreOfferente !== null ? stato.astaCorrente.miglioreOfferente.nick : ""}</p>
                        <TextInput label="Offerta: " value={this.state.input} on_change={(val) => {
                            this.setState({
                                input: val
                            })}} type="number"/>
                        <PopupButtonContainer>
                            <button onClick={() => StompController.offri(this.state.input)}>Offri</button>
                        </PopupButtonContainer>
                        {translateCarteProprieta[stato.astaCorrente.prop.type](stato.astaCorrente.prop)}
                    </div>
                </Popup>
            </div>
        )
    }
}
