import IPartita from "../interfaces/IPartita";
import {ICarta} from "../interfaces/ICarta";

export interface ObserverPartita{
    update(partita: IPartita): void
}

export interface ObserverCarta {
    udpateCarta(carta: ICarta): void
}

export class ObserverSingleton {
    private static listListener:ObserverPartita[] = [];
    private static listListenerCarta:ObserverCarta[] = [];

    static addListener(listener: ObserverPartita){
        this.listListener.push(listener);
    }

    static addListenerCarta(listener: ObserverCarta){
        this.listListenerCarta.push(listener)
    }

    static removeListener(listener: ObserverPartita | ObserverCarta) {
        this.listListener = this.listListener.filter(el => el !== listener);
        this.listListenerCarta = this.listListenerCarta.filter(el => el !== listener);
    }

    static notify(partita: IPartita){
        this.listListener.forEach(el => el.update(partita));
    }

    static notifyCarta(carta: ICarta){
        this.listListenerCarta.forEach(el => el.udpateCarta(carta))
    }
}
