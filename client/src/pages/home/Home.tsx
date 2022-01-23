import React from "react";
import "../../App.css";
import StompController from "../../application/stompController";
import CreaPartita from "./CreaPartita";
import "./Home.css";
import AccediPartita from "./AccediPartita";

interface State {
    selected: number
}

interface Props {
}

export default class Home extends React.Component<Props, State> {

    constructor(props: Props) {
        super(props);
        this.state = {
            selected: 0
        }
    }

    handle_change_select = (i: number) => {
        this.setState({selected: i})
    }

    render(){

        const lista = [
            {text: "Crea partita", component: <CreaPartita/>},
            {text: "Accedi a una partita", component: <AccediPartita/>}
        ]

        return (
            <div className={"container"}>
               <h1>Monopoly</h1>
                <div className={"menu_selector"}>
                    {lista.map(( el, i ) =>
                        <div key={el.text} aria-selected = {this.state.selected === i ? true : false} onClick={() => this.handle_change_select(i)}>
                            <p>{ el.text }</p>
                        </div>
                    )}
                </div>
                <div className={"container_data"}>
                    { lista[this.state.selected].component }
                </div>
            </div>
        )
    }
}

