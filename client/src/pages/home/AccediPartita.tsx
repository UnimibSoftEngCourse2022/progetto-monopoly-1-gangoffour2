import StompController from "../../application/stompController";
import React from "react";
import IPartita from "../../interfaces/IPartita";
import {Link} from "react-router-dom";

interface Props {}

type State = StateLoaded | StateLoading

interface StateLoading {
    loading: true
}

interface StateLoaded {
    loading: false,
    partite: IPartita[]
}

export default class AccediPartita extends React.Component<Props, State> {

    constructor(props: Props) {
        super(props);

        this.state = {
            loading: true
        }
    }

    componentDidMount() {
        StompController.getPartite().then(partite => {
            this.setState({
                loading: false,
                partite: partite
            })
        })
    }


    render() {
        if(this.state.loading)
            return <div>Scaricando i dati</div>

        const { partite } = this.state

        return <div className={"container_accedi"}>
            {
                partite.map(partita =>
                    <>
                    <Link to={"/partita/" + partita.id}>
                        <div>
                            {partita.id}
                        </div>
                    </Link>
                    <hr/>
                    </>

                )
            }
        </div>
    }
}