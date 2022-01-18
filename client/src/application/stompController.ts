import { Stomp } from "@stomp/stompjs";
import websocket from "websocket"

Object.assign(global, {WebSocket: websocket.w3cwebsocket})

const URL = "http://localhost:8080";
const WS_URL = "ws://localhost:8080";

const defaultConfiguration = {
    difficolta: "EASY",
    randomCaselle: true,
    randomEconomia: true,
    soldiIniziali: 1000,
    numeroGiocatori: 4,
    facceDadi: 6,
    numeroDadi: 2,
    triggerDadiUguali: 3
}

export default class StompController {

    static creaPartita() {
        fetch(URL + "/partite", {
            method: "POST",
            body: JSON.stringify(defaultConfiguration),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                if(res.status === 200){
                    return res.text()
                }
                throw new Error("Impossibile creare una partita")
            })
    }

    static accediPartita(idPartita: string) {
        const client = Stomp.client( WS_URL + "/stomp");
        client.connect({}, () => {
            console.log("connesso")
            client.subscribe("/topic/partite/" + idPartita, (res) => console.log(res.body))
        })
    }


}
