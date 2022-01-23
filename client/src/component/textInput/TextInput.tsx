import React, {ChangeEvent, FunctionComponent, useState} from "react";
import "./TextInput.css";

type Props = Common_props & (Text_props | Number_props )

type Pattern = "number" | "text"

const pattern_key: Map<Pattern, string> = new Map<Pattern, string>();

pattern_key.set("number", "[0-9]*")
pattern_key.set("text", ".*")

interface Common_props {
    label: string,
    pattern?: Pattern,
    className?: string,
}

interface Text_props {
    type: "text",
    on_change: (str: string) => void,
    value?: string,
}

interface Number_props {
    type: "number",
    on_change: (str: number) => void,
    value?: number
}

const TextInput: FunctionComponent<Props> = (props) => {

    const default_value = props.value ? props.value : ( props.type === "number" ? 0 : "" );

    const [value, set_value] = useState(default_value);

    let pattern = pattern_key.get(props.type) ?? ".*";
    if(props.pattern !== undefined) pattern = pattern_key.get(props.pattern) ?? pattern;


    const handle_change = (evt: ChangeEvent<HTMLInputElement>) => {
        let val: string = "";
        if(evt.target.validity.valid){
            val = evt.target.value

            const regex = new RegExp(pattern as string);
            console.log(regex.test(val), pattern)
            if(regex.test(val)) {
                if (props.type === "number" && !isNaN(parseFloat(val.replace(",", "."))))
                    props.on_change(parseFloat(val));
                else if(props.type === "text")
                    props.on_change(val);
                set_value(val)
            }
        }
    }
    let type = props.type ? props.type : "text"


    return <div className={"text_input_container " + props.className}>
        <label>{props.label}</label>
        <input
        type={type}
        value={value}
        pattern={pattern}
        onChange={handle_change}
        className={"input"}/>
    </div>
}

export default TextInput