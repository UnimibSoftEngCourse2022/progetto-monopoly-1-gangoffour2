import IPartita from "../interfaces/IPartita";

export interface ObserverPartita {
    update(partita: IPartita): void
}

export class ObserverSingleton {
    private static listListener: ObserverPartita[] = [];

    static addListener(listener: ObserverPartita){
        this.listListener.push(listener);
    }

    static removeListener(listener: ObserverPartita) {
        this.listListener = this.listListener.filter(el => el !== listener)
    }

    static notify(partita: IPartita){
        this.listListener.forEach(el => el.update(partita));
    }
}
