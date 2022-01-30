import React, {ChangeEvent} from "react";
import StompController from "../../../application/stompController";
import TextInput from "../../../component/textInput/TextInput";
import "./lobby.css"
import IPartita from "../../../interfaces/IPartita";
import {Difficolta} from "../../../interfaces/IConfigurazione";

interface Props {
    setGiocatore: (nick: string, isImprenditore: boolean) => void,
    idPartita: string
}

interface State {
    nickname: string,
    isImprenditore: boolean,
    partita?: IPartita
}

export default class Lobby extends React.Component<Props, State> {


    constructor(props: Props) {
        super(props);


        this.state = {
            nickname: "",
            isImprenditore: false
        }
    }

    componentDidMount() {
        StompController.getPartita(this.props.idPartita).then(partita => this.setState({partita}));
    }

    handleSend = () => this.props.setGiocatore(this.state.nickname, this.state.isImprenditore)

    handleSetNickname = (text: string) => this.setState({nickname: text})
    handleSetImprenditore = (evt: ChangeEvent<HTMLInputElement>) => this.setState({isImprenditore: evt.target.checked})

    render() {
        return <div className={"container_menu container_partita"}>
            <h1>Accesso alla partita</h1>
            <TextInput className={"text_input_partita"} label={"Nickname"} type={"text"}
                       on_change={this.handleSetNickname}/>
            {this.state.partita?.config.difficolta !== Difficolta.EASY &&
                <div className="imprenditore_checkbox">
                    <label>
                        <input
                            type="checkbox"
                            value="Imprenditore"
                            checked={this.state.isImprenditore}
                            onChange={this.handleSetImprenditore}
                        />

                        Imprenditore
                    </label>
                </div>}
            <button onClick={this.handleSend}>Accedi</button>
        </div>
    }
}