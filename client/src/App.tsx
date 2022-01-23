import React from 'react';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import './App.css';
import Home from "./pages/home/Home";
import PartitaRouter from "./pages/partita/PartitaRouter";

function App() {

  return (
    <div className="App">
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path={"/partita/:id"} element={<PartitaRouter/>} />
            </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
