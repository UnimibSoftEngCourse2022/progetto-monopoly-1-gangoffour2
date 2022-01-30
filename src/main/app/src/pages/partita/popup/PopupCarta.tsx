import {FunctionComponent} from "react";
import Popup from "../../../component/popup/Popup";
import {ICarta} from "../../../interfaces/ICarta";

interface Props {
    carta: ICarta | undefined
}

const PopupCarta: FunctionComponent<Props> = (props) => {

    if(props.carta === undefined)
        return null;

    return <div>
        <Popup trigger={true} onClose={() => {}} title={"Hai estratto una carta"}>
            {props.carta.testo}
        </Popup>
    </div>
}
export default PopupCarta