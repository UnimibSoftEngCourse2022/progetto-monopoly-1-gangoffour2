import React from "react";
import StompController from "../../application/stompController";
import Tabellone from "../../component/Tabellone";

interface Props {}

interface State {}

export default class Partita extends React.Component<Props, State> {

    private id_partita: string;

    constructor(props: Props) {
        super(props);
        const paths = window.location.href.split("/");
        this.id_partita = paths[paths.findIndex(el => el === "partita") + 1];
    }

    componentDidMount() {
        StompController.accediPartita(this.id_partita)
    }

    render() {
        return <Tabellone caselle={[]}/>;
    }
}