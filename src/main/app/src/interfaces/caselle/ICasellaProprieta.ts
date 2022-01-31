import {ICasella} from "./ICasella";

export interface ICasellaProprieta extends ICasella{
    costoBase: number,
    ipoteca: number,
    rendita: number,
    proprietario: string
}

export default ICasellaProprieta;