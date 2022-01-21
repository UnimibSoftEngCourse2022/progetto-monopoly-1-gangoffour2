import ICasella from "./ICasella";

export default interface IGiocatore {
    nick: string,
    conto: number,
    casellaCorrente: ICasella
}