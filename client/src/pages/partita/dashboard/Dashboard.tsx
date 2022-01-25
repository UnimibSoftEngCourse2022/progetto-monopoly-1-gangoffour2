import React from "react";
import IPartita from "../../../interfaces/IPartita";
import "./dashboard.css"
import ICasellaProprieta from "../../../interfaces/caselle/ICasellaProprieta";
import ICasellaTererno from "../../../interfaces/caselle/ICasellaTererno";
import ICasellaTerreno from "../../../interfaces/caselle/ICasellaTererno";
import StompController from "../../../application/stompController";

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

    handleTiraDadi = () => {
        StompController.lanciaDadi();
    }

    render() {
        const giocatore = this.props.partita.giocatori.find(el => el.nick === this.props.nickname)
        if (giocatore === undefined)
            return null;

        const properietaPossedute = giocatore.proprietaPossedute

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
                        {
                            properietaPossedute.length === 0 && <div className={"subtitle_dashboard"}>
                                Non possiedi nessuna proprietà
                            </div>
                        }
                    </div>
                </div>
                <div className={"bottom_button_dashboard"}>
                    <button onClick={this.handleTiraDadi}>Tira Dadi</button>
                </div>
            </div>
        );
    }
}