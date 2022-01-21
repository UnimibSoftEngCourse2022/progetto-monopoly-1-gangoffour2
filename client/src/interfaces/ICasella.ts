import {IAlbergo, ICasa} from "./IEdificio";

export enum CasellaTypo {
    Terreno,
    Via,
    Prigione
}

export enum Colore {
    ROSSO,
    BLU,
    AZZURRO,
    GIALLO,
    ARANCIONE,
    MARRONE,
    VIOLA,
    VERDE
}

type ICasella = ( ICasellaVia | ICasellaPrigione | ICasellaTerreno) & {
    name: string
}

interface ICasellaVia {
    type: "Via"
}

interface ICasellaPrigione {
    type: "Prigione"
}

interface ICasellaTerreno {
    type: "Terreno",
    affitti: number[],
    colore: Colore,
    costoAlbergo: number,
    costoBase: number,
    costoCasa: number,
    ipoteca: number,
    albergo: IAlbergo | null,
    listaCase: ICasa[] | null,
    proprietario: string | null
}


export default ICasella