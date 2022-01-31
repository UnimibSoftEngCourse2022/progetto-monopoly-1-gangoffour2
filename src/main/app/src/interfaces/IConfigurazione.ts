
export enum Difficolta {
    EASY = "EASY",
    MEDIUM = "MEDIUM",
    HARD = "HARD"
}

export default interface IConfigurazione {
    difficolta: Difficolta,
    randomCaselle: boolean,
    randomEconomia: boolean,
    soldiIniziali: number,
    numeroGiocatori: number,
    facceDadi: number,
    numeroDadi: number,
    triggerDadiUguali: number,
    maxCasePerTerreno: number
}