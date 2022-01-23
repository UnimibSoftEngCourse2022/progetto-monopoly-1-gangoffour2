import {ObserverPartita, ObserverSingleton} from "../../application/ObserverSingleton";
import React from "react";
import IPartita from "../../interfaces/IPartita";
import Tabellone from "../../component/Tabellone";
import StompController from "../../application/stompController";

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
        console.log(partita)
        this.setState({
            partita: partita
        })
    }

    render() {
        console.log(this.state.partita)
        if(this.state.partita)
            return <Tabellone caselle={this.state.partita.tabellone.caselle}/>
        else
            return null;
    }
}