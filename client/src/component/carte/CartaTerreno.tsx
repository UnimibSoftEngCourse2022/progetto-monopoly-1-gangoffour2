import React from "react";
import './carta.css';
import ICasellaTerreno from "../../interfaces/caselle/ICasellaTererno";

interface Props{
    casella: ICasellaTerreno;
}

export default class CartaTerreno extends React.Component<Props, {}>{


    render() : JSX.Element {
        let {casella} = this.props;
        let jsxPrezzi: JSX.Element[] = [];
        for(let i = 1; i < casella.affitti.length - 1; ++i){
            jsxPrezzi.push(<>
                <div className="qty">Con {i} {i > 1 ? "case" : "casa"}:</div>
                <div className="price">{casella.affitti[i]}€</div>
            </>)
        }

        return <div className="table-carta">
            <div className="carta-d">
                <summary className={"terreno-carta " + casella.colore}>
                    <span className="eyebrow"/>
                    {casella.nome}
                </summary>
                <p className="rent">Rendita solo terreno {casella.affitti[0]}€.</p>
                <div className="priceTable">
                    {jsxPrezzi}
                </div>
                <p className="hotel">Con albergo {casella.affitti[casella.affitti.length - 1]}€</p>
                <p className="mortgage">Valore ipotecario: {casella.ipoteca}€</p>
                <p className="houses">Prezzo di ogni casa: {casella.costoCasa}€</p>
                <p className="hotelCost">Prezzo dell'albergo: {casella.costoAlbergo}€</p>
                <p className="disclaimer">If a player owns ALL the Lots of any Color-Group, the<br/>rent is Doubled on
                    Unimproved Lots in that group.<br/>&copy;1935 Hasbro, Inc.</p>
            </div>
        </div>
    }
}