import React, {FunctionComponent} from "react";
import style from "./barraAzioni.module.css"
import {Dashboard} from "../../pages/partita/dashboard/Dashboard";
import IPartita from "../../interfaces/IPartita";
import {Classifica} from "../../pages/partita/classifica/Classifica";
import PopupRouter from "../../pages/partita/popup/PopupRouter";

interface Props {
    partita: IPartita,
    nickname: string
}

const BarraAzioni: FunctionComponent<Props> = (props) => {

    const { partita, nickname } = props

    return <div className={style.container}>
        <Classifica partita={partita}/>
        <Dashboard partita={partita} nickname={nickname}/>
        <PopupRouter partita = {partita} nickname={nickname}/>
    </div>
}

export default BarraAzioni