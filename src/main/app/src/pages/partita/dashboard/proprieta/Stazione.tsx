import ICasellaProprieta from "../../../../interfaces/caselle/ICasellaProprieta";
import React from "react";
import {IDescrizioneProprieta} from "../Proprieta";
import ICasellaStazione, {ICasellaStazioneState} from "../../../../interfaces/caselle/ICasellaStazione";

const translateState: {[key: ICasellaStazioneState | string]: (stazione: ICasellaStazione) => string} = {
    /*
    "TerrenoNonAcquistato": (societa) => "",
    "TerrenoAcquistato": (societa) => "Case possedute: " + terreno.listaCase?.length ?? 0,
    "TerrenoIpotecato": (terreno) => "Terreno ipotecato, costo ipoteca: " + terreno.ipoteca
     */
}

const translateButtonState: {[key: ICasellaStazioneState | string]: (stazione: ICasellaStazione) => JSX.Element} = {
    /*
    "TerrenoNonAcquistato": (terreno) => <></>,
    "TerrenoAcquistato": (terreno) => <>
        <button><i className="fas fa-angle-up"></i></button>
        <button><i className="fas fa-angle-down"></i></button>
    </>,
    "TerrenoIpotecato": (terreno) => <>

    </>
     */
}


export class Stazione extends React.Component<ICasellaProprieta, {}> implements IDescrizioneProprieta{

    getDescrizione(): string {
        const stazione = this.props as ICasellaStazione

        return translateState[stazione.stato.type](stazione);
    }

    render() {

        const stazione = this.props as ICasellaStazione

        return translateButtonState[stazione.stato.type](stazione)
    }


}
