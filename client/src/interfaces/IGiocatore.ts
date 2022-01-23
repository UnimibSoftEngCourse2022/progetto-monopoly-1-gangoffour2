import ICasella from "./caselle/ICasella";

export default interface IGiocatore {
    nick: string,
    conto: number,
    casellaCorrente: ICasella
}