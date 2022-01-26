import {Colore} from "../../interfaces/caselle/ICasellaTererno";
import ICasella from "../../interfaces/caselle/ICasella";

export default class CasellaSingleton {
    static casellaGiocatore: {[key: number]: string[]} = {}
    static giocatoreColore: {[key: string]: string} = {}

    static addGiocatore(nick: string, casella: ICasella){
        if(this.giocatoreColore[nick] === undefined)
            this.giocatoreColore[nick] = '#'+(0x1000000+Math.random()*0xffffff).toString(16).substring(1,7)

        if(this.casellaGiocatore[casella.id])
            this.casellaGiocatore[casella.id] = [...this.casellaGiocatore[casella.id], nick]
        else
            this.casellaGiocatore[casella.id] = [nick]
    }
}