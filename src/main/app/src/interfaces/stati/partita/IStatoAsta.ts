import IStatoPartita from "./IStatoPartita";
import ICasella from "../../caselle/ICasella";
import IGiocatore from "../../IGiocatore";

interface IAsta{
    offertaAttuale: number,
    miglioreOfferente: IGiocatore,
    prop: ICasella
}

export default interface IStatoAsta extends IStatoPartita{
    astaCorrente: IAsta
}