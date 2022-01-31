import ICasellaProprieta from "../../../../interfaces/caselle/ICasellaProprieta";
import React from "react";
import {IDescrizioneProprieta} from "../Proprieta";
import ICasellaSocieta, {ICasellaSocietaState} from "../../../../interfaces/caselle/ICasellaSocieta";
import VendiBtn from "../tastiFunzione/VendiBtn";
import IpotecaBtn from "../tastiFunzione/IpotecaBtn";

const translateState: {[key: ICasellaSocietaState | string]: (societa: ICasellaSocieta) => string} = {
    "SocietaAcquistata": (societa) => "Valore ipoteca: " + societa.ipoteca,
    "SocietaIpotecata": (societa) => "Societa ipotecata, costo ipoteca: " + societa.ipoteca,
    "SocietaNonAcquistata": (societa) => ""
}

const translateButtonState: {[key: ICasellaSocietaState | string]: (societa: ICasellaSocieta) => JSX.Element} = {
    "SocietaAcquistata": (societa) => <>
        <VendiBtn casella={societa}/>
        <IpotecaBtn casella={societa}/>
    </>,
    "SocietaNonAcquistata": (societa) => <></>,
    "SocietaIpotecata": (societa) => <>
        <IpotecaBtn casella={societa}/>
    </>
}


export class Societa extends React.Component<ICasellaProprieta, {}> implements IDescrizioneProprieta{

    getDescrizione(): string {
        const societa = this.props as ICasellaSocieta
        if(translateState[societa.stato.type] !== undefined)
            return translateState[societa.stato.type](societa);
        return "";
    }

    render() {

        const societa = this.props as ICasellaSocieta

        return translateButtonState[societa.stato.type](societa)
    }


}
