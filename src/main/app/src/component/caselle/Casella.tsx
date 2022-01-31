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
import translateCarteProprieta from "../../pages/partita/popup/TranslateCarteProprieta";

const translate = {
    "Imprevisto": (props: any, giocatoriJsx: JSX.Element) => <Imprevisto casella={props}> {giocatoriJsx} </Imprevisto>,
    "Terreno": (props: any, giocatoriJsx: JSX.Element, ca: () => {}) => <Terreno casella={props} caHover={ca}> {giocatoriJsx} </Terreno>,
    "Societa": (props: any, giocatoriJsx: JSX.Element, ca: () => {}) => <Societa caHover={ca} casella={props}>{giocatoriJsx}</Societa>,
    "Stazione": (props: any, giocatoriJsx: JSX.Element, ca: () => {}) => <Stazione caHover={ca} casella={props}>{giocatoriJsx}</Stazione>,
    "Prigione": (props: any, giocatoriJsx: JSX.Element) => <Prigione casella={props}>{giocatoriJsx}</Prigione>,
    "Probabilita": (props: any, giocatoriJsx: JSX.Element) => <Probabilita casella={props}>{giocatoriJsx}</Probabilita>,
    "Tassa": (props: any, giocatoriJsx: JSX.Element) => <Tassa casella={props}>{giocatoriJsx}</Tassa>,
    "VaiInPrigione": (props: any, giocatoriJsx: JSX.Element) => <VaiInPrigione casella={props}>{giocatoriJsx}</VaiInPrigione>,
    "Parcheggio": (props: any, giocatoriJsx: JSX.Element) => <Parcheggio casella={props}>{giocatoriJsx}</Parcheggio>,
    "Via": (props: any, giocatoriJsx: JSX.Element) => <Via casella={props}>{giocatoriJsx}</Via>
}

type Props = ICasella & {rotate?: number}

export class Casella extends React.Component<Props, {
    hover: boolean
}>{

    constructor(props: Props) {
        super(props);
        this.state = {
            hover: false
        }
    }

    render() {
        const carta_attributo = translateCarteProprieta[this.props.type]

        const giocatoriJsx = <>
            <div className={"popup_carta_tabellone"} aria-level={this.props.rotate}>
                <div>
                {
                    carta_attributo !== undefined && this.state.hover ? carta_attributo(this.props) : null
                }
                </div>
            </div>
            <div className={"overlap_player"}>
            {
                CasellaSingleton.casellaGiocatore[this.props.id]?.map(el => {
                        const colore = CasellaSingleton.giocatoreColore[el];
                        return <div title={el} style={{backgroundColor: colore}}></div>
                    }
                )
            }
        </div>

        </>
        //@ts-ignore
        return translate[this.props.type](this.props, giocatoriJsx, () => this.setState({hover: !this.state.hover}))
    }

}
