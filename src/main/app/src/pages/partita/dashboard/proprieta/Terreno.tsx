import ICasellaProprieta from "../../../../interfaces/caselle/ICasellaProprieta";
import React from "react";
import ICasellaTerreno, { ICasellaTerrenoState } from "../../../../interfaces/caselle/ICasellaTererno";
import {IDescrizioneProprieta} from "../Proprieta";
import IpotecaBtn from "../tastiFunzione/IpotecaBtn";
import VendiBtn from "../tastiFunzione/VendiBtn";
import DowngradeBtn from "../tastiFunzione/DowngradeBtn";
import UpgradeBtn from "../tastiFunzione/UpgradeBtn";

const translateState: {[key: ICasellaTerrenoState | string]: (terreno: ICasellaTerreno) => string} = {
    "TerrenoNonAcquistato": (terreno) => "",
    "TerrenoAcquistato": (terreno) => "Case possedute: " + terreno.numeroCase,
    "TerrenoIpotecato": (terreno) => "Terreno ipotecato, costo ipoteca: " + terreno.ipoteca
}

const translateButtonState: {[key: ICasellaTerrenoState | string]: (terreno: ICasellaTerreno) => JSX.Element} = {
    "TerrenoNonAcquistato": (terreno) => <></>,
    "TerrenoAcquistato": (terreno) => <>
        <VendiBtn casella={terreno}/>
        <IpotecaBtn casella={terreno}/>
        <DowngradeBtn casella={terreno}/>
        <UpgradeBtn casella={terreno}/>
    </>,
    "TerrenoIpotecato": (terreno) => <>
        <IpotecaBtn casella={terreno}/>
    </>
}

export class Terreno extends React.Component<ICasellaProprieta, {}> implements IDescrizioneProprieta{

    getDescrizione(): string {
        const terreno = this.props as ICasellaTerreno

        if(translateState[terreno.stato.type] !== undefined)
            return translateState[terreno.stato.type](terreno);
        return "";
    }

    render() {

        const terreno = this.props as ICasellaTerreno

        return translateButtonState[terreno.stato.type](terreno)
    }
}
