import ICasellaProprieta from "../../../../interfaces/caselle/ICasellaProprieta";
import React from "react";
import {IDescrizioneProprieta} from "../Proprieta";
import ICasellaSocieta, {ICasellaSocietaState} from "../../../../interfaces/caselle/ICasellaSocieta";

const translateState: {[key: ICasellaSocietaState | string]: (societa: ICasellaSocieta) => string} = {
    /*
    "TerrenoNonAcquistato": (societa) => "",
    "TerrenoAcquistato": (societa) => "Case possedute: " + terreno.listaCase?.length ?? 0,
    "TerrenoIpotecato": (terreno) => "Terreno ipotecato, costo ipoteca: " + terreno.ipoteca
     */
}

const translateButtonState: {[key: ICasellaSocietaState | string]: (societa: ICasellaSocieta) => JSX.Element} = {
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


export class Societa extends React.Component<ICasellaProprieta, {}> implements IDescrizioneProprieta{

    getDescrizione(): string {
        const societa = this.props as ICasellaSocieta

        return translateState[societa.stato.type](societa);
    }

    render() {

        const societa = this.props as ICasellaSocieta

        return translateButtonState[societa.stato.type](societa)
    }


}
