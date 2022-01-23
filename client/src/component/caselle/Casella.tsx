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

const translate = {
    "Imprevisto": (props: any) => <Imprevisto casella={props}/>,
    "Terreno": (props: any) => <Terreno casella={props}/>,
    "Societa": (props: any) => <Societa casella={props}/>,
    "Stazione": (props: any) => <Stazione casella={props}/>,
    "Prigione": (props: any) => <Prigione casella={props}/>,
    "Probabilita": (props: any) => <Probabilita casella={props}/>,
    "Tassa": (props: any) => <Tassa casella={props}/>,
    "VaiInPrigione": (props: any) => <VaiInPrigione casella={props}/>,
    "Parcheggio": (props: any) => <Parcheggio casella={props}/>,
    "Via": (props: any) => <Via casella={props}/>
}

export class Casella extends React.Component<ICasella, {}>{

    render() {
        //@ts-ignore
        return translate[this.props.type](this.props);
    }
}
