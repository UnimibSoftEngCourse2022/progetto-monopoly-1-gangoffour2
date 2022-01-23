import {ICasella} from "./ICasella";

export interface ICasellaProprieta extends ICasella{
    proprietario: string | null,
    costoBase: number,
    ipoteca: number,
}

export default ICasellaProprieta;