import ICasellaProprieta from "../../../../interfaces/caselle/ICasellaProprieta";
import React from "react";
import {IDescrizioneProprieta} from "../Proprieta";
import ICasellaStazione, {ICasellaStazioneState} from "../../../../interfaces/caselle/ICasellaStazione";
import VendiBtn from "../tastiFunzione/VendiBtn";
import IpotecaBtn from "../tastiFunzione/IpotecaBtn";

const translateState: {[key: ICasellaStazioneState | string]: (stazione: ICasellaStazione) => string} = {
    "StazioneAcquistata": (stazione) => "Valore ipoteca: " + stazione.ipoteca,
    "StazioneNonAcquistata": (stazione) => "",
    "StazioneIpotecata": (stazione) => "Stazione ipotecata, valore ipoteca: " + stazione.ipoteca
}

const translateButtonState: {[key: ICasellaStazioneState | string]: (stazione: ICasellaStazione) => JSX.Element} = {
    "StazioneAcquistata": (stazione) => <>
        <VendiBtn casella={stazione}/>
        <IpotecaBtn casella={stazione}/>
    </>,
    "StazioneNonAcquistata": (stazione) => <></>,
    "StazioneIpotecata": (stazione) => <>
        <IpotecaBtn casella={stazione}/>
    </>
}


export class Stazione extends React.Component<ICasellaProprieta, {}> implements IDescrizioneProprieta{

    getDescrizione(): string {
        const stazione = this.props as ICasellaStazione

        if(translateState[stazione.stato.type] !== undefined)
            return translateState[stazione.stato.type](stazione);
        return "";
    }

    render() {

        const stazione = this.props as ICasellaStazione

        return translateButtonState[stazione.stato.type](stazione)
    }


}
