import StompController from "../../application/stompController";
import React, {ChangeEvent} from "react";
import IConfigurazione, {Difficolta} from "../../interfaces/IConfigurazione";
import TextInput from "../../component/textInput/TextInput";

interface Props {
}

interface State {
    configurazione: IConfigurazione
}

const defaultConfiguration: IConfigurazione = {
    difficolta: Difficolta.EASY,
    randomCaselle: true,
    randomEconomia: true,
    soldiIniziali: 1000,
    numeroGiocatori: 4,
    facceDadi: 6,
    numeroDadi: 2,
    triggerDadiUguali: 3,
    maxCasePerTerreno: 4
}

export default class CreaPartita extends React.Component<Props, State> {

    constructor(props: Props) {
        super(props);
        this.state = {
            configurazione: defaultConfiguration
        }
    }

    handleSet = (ca: (config: IConfigurazione) => IConfigurazione) => {
        const config = this.state.configurazione;
        this.setState({configurazione: ca(config)})
    }

    handleSetSoldiIniziali = (soldi: number) => {
        this.handleSet((config) => {
            config.soldiIniziali = soldi;
            return config;
        })
    }

    handleSetNumeroDadi = (dadi: number) => {
        this.handleSet((config) => {
            config.numeroDadi = dadi;
            return config;
        })
    }

    handleSetFacceDadi = (facce: number) => {
        this.handleSet((config) => {
            config.facceDadi = facce;
            return config;
        })
    }

    handleSetTriggerDadiUguali = (triggerUguali: number) => {
        this.handleSet((config) => {
            config.triggerDadiUguali = triggerUguali;
            return config;
        })
    }

    handleSetNumeroGiocatori = (numeroGiocatori: number) => {
        this.handleSet((config) => {
            config.numeroGiocatori = numeroGiocatori;
            return config;
        })
    }

    handleSetDifficolta = (event: ChangeEvent<HTMLSelectElement>) => {
        this.handleSet((config) => {
            config.difficolta = event.target.value as Difficolta;
            return config;
        })
    }

    render() {

        const config = this.state.configurazione;

        return <div className={"form_accedi"}>
            <TextInput label={"Numero Giocatori"} type={"number"} on_change={this.handleSetNumeroGiocatori}
                       value={config.numeroGiocatori}/>
            <TextInput label={"Numero Dadi"} type={"number"} on_change={this.handleSetNumeroDadi}
                       value={config.numeroDadi}/>
            <TextInput label={"Facce Dadi"} type={"number"} on_change={this.handleSetFacceDadi}
                       value={config.facceDadi}/>
            <TextInput label={"Trigger Dadi Uguali"} type={"number"} on_change={this.handleSetTriggerDadiUguali}
                       value={config.triggerDadiUguali}/>
            <TextInput label={"Soldi Iniziali"} type={"number"} on_change={this.handleSetSoldiIniziali}
                       value={config.soldiIniziali}/>

                <label htmlFor={"difficolta"}>Seleziona la difficolta</label>
                <select id={"difficolta"} onChange={this.handleSetDifficolta} value={config.difficolta}>
                    <option value={Difficolta.EASY}>Facile</option>
                    <option value={Difficolta.MEDIUM}>Normale</option>
                    <option value={Difficolta.HARD}>Difficile</option>
                </select>
            <div>
                <p>Facile: Gioco vanilla</p>
                <p>Normale: I giocatori possono essere imprenditori</p>
                <p>Difficile: Random Economia + Random Tabellone</p>
            </div>

            <button onClick={() => StompController.creaPartita(this.state.configurazione)}>
                crea partita
            </button>
        </div>
    }
}