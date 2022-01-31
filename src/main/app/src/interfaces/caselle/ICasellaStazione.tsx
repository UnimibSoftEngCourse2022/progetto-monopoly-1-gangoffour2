import {ICasella} from "./ICasella";
import ICasellaProprieta from "./ICasellaProprieta";

export type ICasellaStazioneState = "StazioneAcquistata" | "StazioneIpotecata" | "StazioneNonAcquistata"

export interface ICasellaStazione extends ICasellaProprieta{
    stato: {
        type: ICasellaStazioneState
    }
}

export default ICasellaStazione;