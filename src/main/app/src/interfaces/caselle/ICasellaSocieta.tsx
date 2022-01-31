import ICasellaProprieta from "./ICasellaProprieta";

export type ICasellaSocietaState = "SocietaAcquistata" | "SocietaIpotecata" | "SocietaNonAcquistata"

export interface ICasellaSocieta extends ICasellaProprieta{
    stato: {
        type: ICasellaSocietaState
    }
}

export default ICasellaSocieta;