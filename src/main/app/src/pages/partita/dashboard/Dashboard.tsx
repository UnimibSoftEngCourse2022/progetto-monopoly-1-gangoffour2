import React from "react";
import IPartita from "../../../interfaces/IPartita";
import "./dashboard.css"
import Proprieta from "./Proprieta";

interface Props {
    partita: IPartita,
    nickname: string
}

interface State {

}

export class Dashboard extends React.Component<Props, State> {
    render() {
        const giocatore = this.props.partita.giocatori.find(el => el.nick === this.props.nickname)
        if (giocatore === undefined)
            return null;

        const properietaPossedute = giocatore.proprietaPossedute

        return (
                <div className={"dashboard_container_data"}>
                   <h3 id={"titolo-dashboard"}>Proprietà</h3>
                    <div className={"property_list"}>
                        {properietaPossedute.map(el =>
                            <React.Fragment>
                                <Proprieta {...el}/>
                            </React.Fragment>
                        )}
                        {
                            properietaPossedute.length === 0 && <div className={"subtitle_dashboard"}>
                                Non possiedi nessuna proprietà
                            </div>
                        }
                    </div>
                </div>
        );
    }
}