import ICasellaTerreno from "../../../interfaces/caselle/ICasellaTererno";
import CartaTerreno from "../../../component/carte/CartaTerreno";
import ICasellaSocieta from "../../../interfaces/caselle/ICasellaSocieta";
import CartaSocieta from "../../../component/carte/CartaSocieta";
import CartaStazione from "../../../component/carte/CartaStazione";
import ICasellaStazione from "../../../interfaces/caselle/ICasellaStazione";

const translateCarteProprieta : any = {
    "Terreno": (terreno: ICasellaTerreno) => <CartaTerreno casella={terreno}/>,
    "Societa": (societa: ICasellaSocieta) => <CartaSocieta casella={societa}/>,
    "Stazione": (stazione: ICasellaStazione) => <CartaStazione casella={stazione}/>,
}


export default translateCarteProprieta;