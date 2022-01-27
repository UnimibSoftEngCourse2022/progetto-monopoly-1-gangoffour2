import {FunctionComponent} from "react";
import IPartita from "../../../interfaces/IPartita";
import PopupAcquisto from "./PopupAcquisto";
import IGiocatore from "../../../interfaces/IGiocatore";

interface Props {
    partita: IPartita,
    nickname: string
}

const componentStato: {[key: string]: (props: Props, giocatore: IGiocatore, isMioTurno: boolean) => JSX.Element} = {
    'AttesaAcquisto': (props: Props, giocatore: IGiocatore, isMioTurno) =>
        <PopupAcquisto partita={props.partita} giocatore={giocatore} isMioTurno={isMioTurno}/>,

}

const PopupRouter: FunctionComponent<Props> = (props) => {

    const component = componentStato[props.partita.stato.type]
    const giocatore = props.partita.giocatori.find(el => el.nick === props.partita.turnoCorrente?.giocatore.nick)
    const isMioTurno = props.partita.turnoCorrente?.giocatore.nick === props.nickname

    if(component === undefined || giocatore === undefined)
        return null;
    return component(props, giocatore, isMioTurno);

}

export default PopupRouter;