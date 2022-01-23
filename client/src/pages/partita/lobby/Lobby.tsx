import React from "react";
import StompController from "../../../application/stompController";
import TextInput from "../../../component/textInput/TextInput";
import "./lobby.css"
import {ObserverPartita} from "../../../application/ObserverSingleton";
import IPartita from "../../../interfaces/IPartita";

interface Props {
    setNickame: (nick: string) => void
}

interface State {
    nickname: string
}

export default class Lobby extends React.Component<Props, State> {


    constructor(props: Props) {
        super(props);


        this.state = {
            nickname: ""
        }
    }

    handleSend = () => this.props.setNickame(this.state.nickname)

    handleSetNickname = (text: string) => this.setState({nickname: text})

    render() {
        return <div className={"container container_partita"}>
            <h1>Accesso alla partita</h1>
            <TextInput className={"text_input_partita"} label={"Nickname"} type={"text"} on_change={this.handleSetNickname}/>
            <button onClick={this.handleSend}>Accedi</button>
        </div>
    }


}