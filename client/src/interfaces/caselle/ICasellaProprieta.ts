import {ICasella} from "./ICasella";

export interface ICasellaProprieta extends ICasella{
    costoBase: number,
    ipoteca: number,
}

export default ICasellaProprieta;