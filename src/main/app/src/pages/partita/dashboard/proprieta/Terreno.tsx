import ICasellaProprieta from "../../../../interfaces/caselle/ICasellaProprieta";
import React, {FunctionComponent} from "react";
import ICasellaTerreno, { ICasellaTerrenoState } from "../../../../interfaces/caselle/ICasellaTererno";
import {IDescrizioneProprieta} from "../Proprieta";
import ICasellaTererno from "../../../../interfaces/caselle/ICasellaTererno";

const translateState: {[key: ICasellaTerrenoState | string]: (terreno: ICasellaTerreno) => string} = {
    "TerrenoNonAcquistato": (terreno) => "",
    "TerrenoAcquistato": (terreno) => "Case possedute: " + terreno.listaCase?.length ?? 0,
    "TerrenoIpotecato": (terreno) => "Terreno ipotecato, costo ipoteca: " + terreno.ipoteca
}

const translateButtonState: {[key: ICasellaTerrenoState | string]: (terreno: ICasellaTerreno) => JSX.Element} = {
    "TerrenoNonAcquistato": (terreno) => <></>,
    "TerrenoAcquistato": (terreno) => <>
        <button><i className="fas fa-angle-up"></i></button>
        <button><i className="fas fa-angle-down"></i></button>
    </>,
    "TerrenoIpotecato": (terreno) => <>

    </>
}


export class Terreno extends React.Component<ICasellaProprieta, {}> implements IDescrizioneProprieta{

    getDescrizione(): string {
        const terreno = this.props as ICasellaTerreno

        return translateState[terreno.stato.type](terreno);
    }

    render() {

        const terreno = this.props as ICasellaTerreno

        return translateButtonState[terreno.stato.type](terreno)
    }


}
