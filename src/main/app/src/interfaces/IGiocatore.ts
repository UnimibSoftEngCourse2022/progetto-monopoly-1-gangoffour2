import ICasella from "./caselle/ICasella";
import ICasellaProprieta from "./caselle/ICasellaProprieta";

export default interface IGiocatore {
    nick: string,
    conto: number,
    casellaCorrente: ICasella,
    proprietaPossedute: ICasellaProprieta[]
}