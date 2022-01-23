import ICasella from "../../interfaces/caselle/ICasella";
import React from "react";
import {Imprevisto} from "./Imprevisto";
import {Terreno} from "./Terreno";

const translate = {
    "Imprevisto": (props: any) => <Imprevisto casella={props}/>,
    "Terreno": (props: any) => <Terreno casella={props}/>
}

export class Casella extends React.Component<ICasella, {}>{

    render() {
        //@ts-ignore
        return translate[this.props.type](this.props)
    }
}
