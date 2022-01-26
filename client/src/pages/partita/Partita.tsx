import {ObserverPartita, ObserverSingleton} from "../../application/ObserverSingleton";
import React from "react";
import IPartita from "../../interfaces/IPartita";
import Tabellone from "../../component/Tabellone";
import StompController from "../../application/stompController";
import {Classifica} from "./classifica/Classifica";
import {Dashboard} from "./dashboard/Dashboard";
import CasellaSingleton from "../../component/caselle/CasellaSingleton";

interface Props {
    idPartita: string,
    nickname: string
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
        StompController.accediPartita(this.props.idPartita, this.props.nickname);
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
        console.log(this.state.partita)
        if(!this.state.partita)
            return null;

        CasellaSingleton.casellaGiocatore = {}
        this.state.partita.giocatori.forEach(el => {
            CasellaSingleton.addGiocatore(el.nick, el.casellaCorrente)
        })

        return <div>
            <Tabellone caselle={this.state.partita.tabellone.caselle} giocatori={this.state.partita.giocatori}/>
            <Classifica partita={this.state.partita}/>
            <Dashboard nickname={this.props.nickname} partita={this.state.partita}/>
        </div>
    }
}