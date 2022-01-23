import ICasellaTerreno from "./ICasellaTererno";
import ICasellaStazione from "./ICasellaStazione";
import {ICasellaImprevisto} from "./ICasellaImprevisto";

export enum CasellaTypo {
    Terreno,
    Via,
    Prigione
}

export interface ICasella {
    type: string;
    name: string,
}

export type AllCaselle = ICasellaImprevisto | ICasellaTerreno

export default ICasella;
