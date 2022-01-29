import {FunctionComponent} from "react";
import style from "./popup.module.css";

const PopupButtonContainer: FunctionComponent<{}> = (props) => {

    return(
        <div className={style.popup_button_function}>
            {props.children}
        </div>
    )
}

export default PopupButtonContainer