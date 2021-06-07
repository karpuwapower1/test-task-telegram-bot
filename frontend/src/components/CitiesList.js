import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import { NavLink, Link  } from 'react-router-dom';

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
                <td style={{whiteSpace: 'nowrap'}}>{city.name}</td>
                <td>
                    <Link to={'/cities/' + city.uuid}>
                        <Button>
                            Edit
                        </Button>
                    </Link>
                </td>
            </tr>
        });

        return (
            <Container>
            <div className="cities-table">
                <h3 align={"center"}>Cities</h3>
                <Table className="mt-4" bordered>
                    <thead>
                    <tr>
                        <th width="33%">Name</th>
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