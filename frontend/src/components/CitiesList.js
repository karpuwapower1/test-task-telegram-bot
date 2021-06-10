import React, {Component} from 'react';
import {Button, Container, Table} from 'reactstrap';
import {Link} from 'react-router-dom';
import '../css/CitiesList.css'
import 'bootstrap/dist/css/bootstrap.min.css';

class CitiesList extends Component {

    constructor(props) {
        super(props);
        this.state = {cities: []};
    }

    componentDidMount() {
        fetch('/cities')
            .then(response => response.json())
            .then(data => this.setState({cities: data}));
    }

    render() {
        const {cities} = this.state;

        const citiesList = cities.map(city => {
            return <tr key={city.uuid}>
                <td>{city.name}</td>
                <td>{city.info}</td>
                <td>
                    <Button color='outline-primary' tag={Link} to={'/cities/' + city.id}>
                        Edit
                    </Button>
                </td>
            </tr>
        });

        return (
            <Container>
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
                </div>
            </Container>
        );
    }
}

export default CitiesList;