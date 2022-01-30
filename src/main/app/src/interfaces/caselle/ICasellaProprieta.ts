import {ICasella} from "./ICasella";

export interface ICasellaProprieta extends ICasella{
    costoBase: number,
    ipoteca: number,
    rendita: number,
}

export default ICasellaProprieta;