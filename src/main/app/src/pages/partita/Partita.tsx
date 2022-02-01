import {ObserverPartita, ObserverSingleton} from "../../application/ObserverSingleton";
import React from "react";
import IPartita from "../../interfaces/IPartita";
import Tabellone from "../../component/Tabellone";
import StompController from "../../application/stompController";
import CasellaSingleton from "../../component/caselle/CasellaSingleton";
import BarraAzioni from "../../component/barraAzioni/barraAzioni";
import style from "./partita.module.css"

interface Props {
    idPartita: string,
    nickname: string,
    isImprenditore: boolean
}

interface State {
    partita?: IPartita
}

export default class Partita extends React.Component<Props, State> implements ObserverPartita {

    constructor(props: Props) {
        super(props);
        this.state = {
            partita: undefined
        }
    }

    componentDidMount() {
        ObserverSingleton.addListener(this);
        StompController.accediPartita(this.props.idPartita, this.props.nickname, this.props.isImprenditore);
    }

    componentWillUnmount() {
        ObserverSingleton.removeListener(this);
    }

    update(partita: IPartita) {
        this.setState({
            partita: partita
        })
    }

    render() {
        if(!this.state.partita)
            return null;

        CasellaSingleton.casellaGiocatore = {}
        this.state.partita.giocatori.forEach(el => {
            CasellaSingleton.addGiocatore(el.nick, el.casellaCorrente)
        })

        return <div className={style.container}>
            <BarraAzioni partita={this.state.partita} nickname={this.props.nickname}/>
            <Tabellone caselle={this.state.partita.tabellone.caselle} giocatori={this.state.partita.giocatori}/>
        </div>
    }
}