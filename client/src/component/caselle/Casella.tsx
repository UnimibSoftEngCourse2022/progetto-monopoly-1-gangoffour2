import ICasella from "../../interfaces/caselle/ICasella";
import React from "react";
import {Imprevisto} from "./Imprevisto";
import {Terreno} from "./Terreno";
import {Societa} from "./Societa";
import {Stazione} from "./Stazione";
import Prigione from "./Prigione";
import {Probabilita} from "./Probabilita";
import {Tassa} from "./Tassa";
import {VaiInPrigione} from "./VaiInPrigione";
import {Parcheggio} from "./Parcheggio";
import Via from "./Via";
import CasellaSingleton from "./CasellaSingleton";

const translate = {
    "Imprevisto": (props: any, giocatoriJsx: JSX.Element) => <Imprevisto casella={props}> {giocatoriJsx} </Imprevisto>,
    "Terreno": (props: any, giocatoriJsx: JSX.Element) => <Terreno casella={props}> {giocatoriJsx} </Terreno>,
    "Societa": (props: any, giocatoriJsx: JSX.Element) => <Societa casella={props}>{giocatoriJsx}</Societa>,
    "Stazione": (props: any, giocatoriJsx: JSX.Element) => <Stazione casella={props}>{giocatoriJsx}</Stazione>,
    "Prigione": (props: any, giocatoriJsx: JSX.Element) => <Prigione casella={props}>{giocatoriJsx}</Prigione>,
    "Probabilita": (props: any, giocatoriJsx: JSX.Element) => <Probabilita casella={props}>{giocatoriJsx}</Probabilita>,
    "Tassa": (props: any, giocatoriJsx: JSX.Element) => <Tassa casella={props}>{giocatoriJsx}</Tassa>,
    "VaiInPrigione": (props: any, giocatoriJsx: JSX.Element) => <VaiInPrigione casella={props}>{giocatoriJsx}</VaiInPrigione>,
    "Parcheggio": (props: any, giocatoriJsx: JSX.Element) => <Parcheggio casella={props}>{giocatoriJsx}</Parcheggio>,
    "Via": (props: any, giocatoriJsx: JSX.Element) => <Via casella={props}>{giocatoriJsx}</Via>
}

export class Casella extends React.Component<ICasella, {}>{

    render() {
        console.log(this.props);
        const giocatoriJsx = <div className={"overlap_player"}>
            {
                CasellaSingleton.casellaGiocatore[this.props.id]?.map(el => {
                        const colore = CasellaSingleton.giocatoreColore[el];
                        console.log(colore)
                        return <div style={{backgroundColor: colore}}></div>
                    }
                )
            }
        </div>
        //@ts-ignore
        const casellaJsx = translate[this.props.type](this.props, giocatoriJsx);
        return casellaJsx
    }

}
