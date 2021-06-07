import React, {Component} from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import CitiesList from './components/CitiesList';
import CityComponent from "./components/CityComponent";

class App extends Component {

    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={CitiesList}/>
                    <Route path='/cities' exact={true} component={CitiesList}/>
                    <Route path='/cities/:id' component={CityComponent}/>
                </Switch>
            </Router>
        )
    }
}

export default App;
