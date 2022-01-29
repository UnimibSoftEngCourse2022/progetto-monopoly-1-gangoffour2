import IGiocatore from "./IGiocatore";

export default interface ITurno {
    giocatore: IGiocatore,
    lanciConsecutivi: number,
    casellaDaVisitare: number,
    valoreDadi: number[],
}