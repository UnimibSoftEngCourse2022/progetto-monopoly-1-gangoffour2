import React from "react";
import Lobby from "./lobby/Lobby";
import Partita from "./Partita";


interface Props {

}

interface State {
    nickname?: string
}

export default class PartitaRouter extends React.Component<Props, State> {

    private idPartita: string;

    constructor(props: Props) {
        super(props);
        const paths = window.location.href.split("/");
        this.idPartita = paths[paths.findIndex(el => el === "partita") + 1];

        this.state = {
            nickname: undefined
        }
    }

    handle_set_nickname = (nick: string) => {
        this.setState({nickname: nick})
    }

    render() {
        if(this.state.nickname === undefined)
            return <Lobby setNickame={this.handle_set_nickname}/>
        else
            return <Partita nickname={this.state.nickname} idPartita={this.idPartita}/>
    }
}