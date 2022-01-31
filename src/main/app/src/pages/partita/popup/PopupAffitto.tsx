import {FunctionComponent} from "react";
import Popup from "../../../component/popup/Popup";
import PopupButtonContainer from "../../../component/popup/PopupButtonContainer";
import {PopupProps} from "./PopupRouterAzioni";
import IGiocatore from "../../../interfaces/IGiocatore";
import StompController from "../../../application/stompController";

const PopupAffitto: FunctionComponent<PopupProps> = (props) => {

    let text = "";

    let proprietario: IGiocatore | undefined;

    props.partita.giocatori.forEach(giocatore =>
        giocatore.proprietaPossedute.forEach(proprieta => {
                if (proprieta.id === props.giocatore.casellaCorrente.id)
                    proprietario = giocatore
            }
        )
    )

    if (proprietario === undefined)
        return null;

    if (props.isMioTurno) {
        text = "Devi pagare l'affitto a " + proprietario.nick
    } else {
        text = "Il giocatore " + props.giocatore.nick + " deve pagare l'affitto a " + proprietario.nick
    }


    return <Popup trigger={true} onClose={() => {
    }} title={"Attessa Pagamento Affitto"}>
        {text}
        {props.isMioTurno && <PopupButtonContainer>
            <button onClick={() => StompController.paga()}>Paga</button>
        </PopupButtonContainer>}
    </Popup>
}

export default PopupAffitto