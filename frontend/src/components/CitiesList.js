import React, {Component} from 'react';
import {Button, Container, Table} from 'reactstrap';
import {Link} from 'react-router-dom';
import '../css/CitiesList.css'
import 'bootstrap/dist/css/bootstrap.min.css';

class CitiesList extends Component {

    constructor(props) {
        super(props);
        this.state = {cities: []};
        this.deleteCity = this.deleteCity.bind(this);
    }

    componentDidMount() {
        fetch('/cities')
            .then(response => response.json())
            .then(data => this.setState({cities: data}));
    }

    async deleteCity(id) {
        await fetch('/cities/' + id, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        }).then(() => {
                let cities = [...this.state.cities].filter(city => city.id !== id);
                this.setState({cities: cities});
            })
        this.props.history.push('/cities');
    }

    render() {
        const {cities} = this.state;

        const citiesList = cities.map(city => {
            return <tr key={city.id}>
                <td>{city.name}</td>
                <td>{city.info}</td>
                <td>
                    <Button color='outline-primary' tag={Link} to={'/cities/' + city.id}>
                        Edit
                    </Button>
                    <Button color='outline-danger' type='submit' onClick={() => this.deleteCity(city.id)}>
                        Delete
                    </Button>
                </td>
            </tr>
        });

        return (
            <Container id="cities_list">
                <div className="cities-table">
                    <h3 align={"center"}>Cities</h3>
                    <Table className="mt-4" bordered>
                        <thead>
                        <tr key={"header"}>
                            <th width="33%">Name</th>
                            <th width="33%">Info</th>
                            <th width="33%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {citiesList}
                        </tbody>
                    </Table>
                    <div class="add-city">
                    <Button color='outline-primary' tag={Link} to={'/cities/-1'}>
                        Add
                    </Button>
                    </div>
                </div>
            </Container>
        );
    }
}

export default CitiesList;