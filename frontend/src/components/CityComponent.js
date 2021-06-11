import React, {Component} from 'react';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import {Link} from "react-router-dom";
import {withRouter} from 'react-router';

class CityComponent extends Component {

    emptyItem = {
        name: '',
        info: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            city: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const city = await (await fetch(`/cities/${this.props.match.params.id}`)).json();
            this.setState({city: city});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let city = {...this.state.city};
        city[name] = value;
        this.setState({city});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {city} = this.state;

        await fetch('/cities' + (city.id ? '/' + city.id : ''), {
            method: (city.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(city),
        });
        this.props.history.push('/cities');
    }

    render() {
        const {city} = this.state;
        const title = <h2>{city.id ? 'Edit City' : 'Add City'}</h2>;

        return (
            <div>
                <Container>
                    {title}
                    <Form onSubmit={this.handleSubmit}>
                        <FormGroup>
                            <Label for="name">Name</Label>
                            <Input type="text" name="name" id="name" value={city.name || ''}
                                   onChange={this.handleChange} autoComplete="name"/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="info">Info</Label>
                            <Input type="text" name="info" id="info" value={city.info || ''}
                                   onChange={this.handleChange} autoComplete="info"/>
                        </FormGroup>
                        <FormGroup>
                            <Button color="primary" type="submit">Save</Button>{' '}
                            <Button color="secondary" tag={Link} to="/cities">Cancel</Button>
                        </FormGroup>
                    </Form>
                </Container>
            </div>
        )
    }
}

export default withRouter(CityComponent);