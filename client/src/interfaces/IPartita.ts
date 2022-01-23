import ICasella from "./caselle/ICasella";
import {IAlbergo, ICasa} from "./IEdificio";
import IConfigurazione from "./IConfigurazione";
import IGiocatore from "./IGiocatore";
import ITurno from "./ITurno";

export default interface IPartita{
    azioneAttesaRicevuta: boolean,
    id: string,
    tabellone: ICasella[],
    alberghi: IAlbergo[],
    config: IConfigurazione,
    giocatori: IGiocatore[],
    listaCase: ICasa[],
    turnoCorrente: ITurno | null
}