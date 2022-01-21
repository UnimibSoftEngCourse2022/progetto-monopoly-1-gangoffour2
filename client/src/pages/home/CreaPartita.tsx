import StompController from "../../application/stompController";
import React from "react";

interface Props {}

interface State {}

export default class CreaPartita extends React.Component<Props, State> {
    render() {
       return <div>
           <button onClick={() => StompController.creaPartita()}>
               crea partita
           </button>
       </div>
    }
}