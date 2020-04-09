import React, {FormEvent} from 'react';
import './UserInfo.scss';
import axios from "axios";

class UserInfo extends React.Component {
    state: { [id: string]: any; } = {
        formControls: {
            firstName: {
                value: '',
                error: ''
            },
            lastName: {
                value: '',
                error: ''
            },
            description: {
                value: '',
                error: ''
            },
            photo: {
                url: '',
                file: '',
                error: ''
            }

        },
        editMode: false
    };

    componentDidMount () {
        axios.get('http://localhost:3000/mockups/user.json').then(res => {
            const user = res.data;
            const formControls = {...this.state.formControls};
            formControls.firstName.value = user.firstName;
            formControls.lastName.value = user.lastName;
            formControls.description.value = user.description;
            formControls.photo.url = user.photo;
            this.setState({formControls:formControls});
        });
    }

    onChangeHandler = (event: FormEvent<HTMLElement>, controlName: string) => {
        const formControls = {...this.state.formControls};
        const control = formControls[controlName];

        control.value = (event.target as HTMLInputElement).value;
        formControls[controlName] = control;

        this.setState({
            formControls
        });
    };

    handleUserDataSaveButton () {
        axios.put('http://localhost:3000/mockups/user.json', {
            firstName: this.state.formControls.firstName.value,
            lastName: this.state.formControls.lastName.value,
            description: this.state.formControls.description.value,
            photo: this.state.formControls.photo.url,
        }).then(res => {
            this.setState({editMode: false});
        }).catch(error => {
            if (error.response.status === 400) {
                // TODO: some validations
            }
        });
    }

    onFileChangeHandler = (files: any) => {
        let reader = new FileReader();
        let file = files[0];

        reader.onloadend = () => {
            const formControls = {...this.state.formControls};
            const control = {...formControls.photo};
            control.file = file;
            control.url = reader.result;
            formControls.photo = control;
            this.setState({
                formControls: formControls
            });
        };

        reader.readAsDataURL(file)
    };

    render() {
        return (
            <div>
                <div className={"user-info-block " + (this.state.editMode ? 'hide' : 'show')}>
                    <img src={this.state.formControls.photo.url} className="user-info-img" alt="Avatar"/>
                    <h3 className="user-name">{this.state.formControls.firstName.value} {this.state.formControls.lastName.value}</h3>
                    <p className="user-description">
                        {this.state.formControls.description.value}
                    </p>
                    <div className={'action-panel'}>
                        <button type={"button"} className={"btn btn-danger"} onClick={e => this.setState({editMode: true})}>Edit</button>
                    </div>
                </div>
                <div className={"user-info-block " + (this.state.editMode ? 'show' : 'hide')}>
                    <input type="file" onChange={(event: FormEvent<HTMLInputElement>) => this.onFileChangeHandler((event.target as HTMLInputElement).files)}/>
                    <div className={"row"} style={{paddingTop: "15px"}}>
                        <div className={"col-md-6"}>
                            <input className={"form-control "} placeholder={"First name"} value={this.state.formControls.firstName.value} onChange={e => { this.onChangeHandler(e, 'firstName')}}/>
                        </div>
                        <div className={"col-md-6"}>
                            <input className={"form-control"} placeholder={"Last name"} value={this.state.formControls.lastName.value} onChange={e => { this.onChangeHandler(e, 'lastName')}}/>
                        </div>
                    </div>
                    <textarea className={"form-control user-description"} placeholder={"Enter your description"} value={this.state.formControls.description.value} onChange={e => { this.onChangeHandler(e, 'description')}}>
                    </textarea>
                    <div className={'action-panel'} style={{paddingTop: "15px"}}>
                        <button className={"btn btn-primary"} style={{marginRight: "15px"}}
                                onClick={e => this.setState({editMode: false})}>Cancel
                        </button>
                        <button className={"btn btn-success"}
                                onClick={e => this.handleUserDataSaveButton()}>Save
                        </button>
                    </div>
                </div>

            </div>
        );
    }
}

export default UserInfo;