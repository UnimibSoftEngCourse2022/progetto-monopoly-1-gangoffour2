import React from "react";
import IPartita from "../../../interfaces/IPartita";
import "./dashboard.css"
import ICasellaProprieta from "../../../interfaces/caselle/ICasellaProprieta";
import ICasellaTererno from "../../../interfaces/caselle/ICasellaTererno";
import ICasellaTerreno from "../../../interfaces/caselle/ICasellaTererno";

interface Props {
    partita: IPartita,
    nickname: string
}

interface State {

}

export class Dashboard extends React.Component<Props, State> {
    constructor(props: Props) {
        super(props);
    }

    computeDescription(casella: ICasellaProprieta){
        switch (casella.type) {
            case "Terreno":
                const terreno = casella as ICasellaTererno;
                if(terreno.albergo)
                    return "Albero costruito"
                return "Case possedute: " + (casella as ICasellaTerreno).listaCase?.length ?? 0;
            default:
                return "Valore ipoteca: " + casella.ipoteca
        }
    }

    render() {
        const giocatore = this.props.partita.giocatori.find(el => el.nick === this.props.nickname)
        console.log(giocatore)
        if (giocatore === undefined)
            return null;

        //const properietaPossedute = giocatore.proprietaPossedute
        const properietaPossedute: ICasellaProprieta[] = this.props.partita.tabellone.caselle.filter(el => el.type === "Terreno") as ICasellaTererno[]
        return (
            <div className={"container_menu dashboard_container"}>
                <div className={"dashboard_container_data"}>
                   <h3>Azioni</h3>
                    <div className={"property_list"}>
                        {properietaPossedute.map(el =>
                            <React.Fragment>
                                <div className={"property_list_element"}>
                                    <div>
                                        {el.nome}
                                    </div>
                                    {this.computeDescription(el)}
                                </div>
                            </React.Fragment>
                        )}
                    </div>
                </div>
                <div className={"bottom_button_dashboard"}>
                    <button>Compra</button>
                    <button>Tira Dadi</button>
                </div>
            </div>
        );
    }
}