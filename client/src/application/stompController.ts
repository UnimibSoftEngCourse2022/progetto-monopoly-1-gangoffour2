import {CompatClient, Stomp} from "@stomp/stompjs";
import websocket from "websocket"
import IPartita from "../interfaces/IPartita";
import IConfigurazione, {Difficolta} from "../interfaces/IConfigurazione";
import {ObserverSingleton} from "./ObserverSingleton";

Object.assign(global, {WebSocket: websocket.w3cwebsocket})

const URL = "http://localhost:8080";
const WS_URL = "ws://localhost:8080";



export default class StompController {

    static client: CompatClient;
    static idPartita: string;

    static creaPartita(configuration: IConfigurazione): Promise<string> {
        return fetch(URL + "/partite", {
            method: "POST",
            body: JSON.stringify(configuration),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                console.log(res)
                if(res.status === 200){
                    return res.text()
                }
                throw new Error("Impossibile creare una partita")
            })
    }

    static getPartite(): Promise<IPartita[]> {
        return fetch(URL + "/partite")
            .then((res) => {
                console.log(res)
                if(res.status === 200){
                    return res.json()
                }
                throw new Error("Impossibile creare una partita")
            })
    }

    static accediPartita(idPartita: string, nickname: string) {
        const client = Stomp.client( WS_URL + "/stomp");
        this.client = client;
        this.idPartita = idPartita;
        client.connect({}, () => {
            client.subscribe("/topic/partite/" + idPartita, (res) => ObserverSingleton.notify(JSON.parse(res.body) as IPartita))
            client.send("/app/partite/" + idPartita + "/entra", {}, nickname)
        })
    }

    static lanciaDadi() {
        this.client.send("/app/partite/" + this.idPartita + "/lanciaDadi")
    }

    static acquista() {
        this.client.send("/app/partite/" + this.idPartita + "/acquista")
    }

    static paga() {
        this.client.send("/app/partite/" + this.idPartita + "/paga")
    }


}
