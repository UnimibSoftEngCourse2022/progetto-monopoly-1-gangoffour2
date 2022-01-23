import React from 'react';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import './App.css';
import Home from "./pages/home/Home";
import Tabellone from "./component/Tabellone";
import Partita from "./pages/partita/Partita";

function App() {

  return (
    <div className="App">
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path={"/partita/:id"} element={<Partita/>} />
            </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
