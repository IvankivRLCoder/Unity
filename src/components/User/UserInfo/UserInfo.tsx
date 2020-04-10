import React, {FormEvent} from 'react';
import './UserInfo.scss';
import axios from "axios";
import Input from "../../../utils/UI/Input/Input";
import { Create } from '@material-ui/icons';
import Validation from "../../../utils/Validation/Validation";


class UserInfo extends React.Component {
    state: { [id: string]: any; } = {
        formControls: {
            firstName: {
                type: 'text',
                placeholder: 'Name',
                value: '',
                valid: false,
                errorMessage: 'Enter valid name',
                showValidate: false,
                validation: {
                    required: true,
                    minLength: 1
                }
            },
            lastName: {
                type: 'text',
                placeholder: 'Surname',
                value: '',
                valid: false,
                errorMessage: 'Enter valid surname',
                showValidate: false,
                validation: {
                    required: true,
                    minLength: 1
                }
            },
            description: {
                type: 'text',
                placeholder: 'Description',
                value: '',
                valid: false,
                errorMessage: '',
                showValidate: false,
                validation: false
            },
            photo: {
                url: '',
                file: '',
                error: ''
            }

        },
        editMode: false,
        currentInfo: {
            firstName: '',
            lastName: '',
            description: '',
            photo: {
                url: '',
                file: ''
            }
        }
    };

    componentDidMount () {
        axios.get('http://localhost:3000/mockups/user.json').then(res => {
            const user = res.data;
            const formControls = {...this.state.formControls};
            formControls.firstName.value = user.firstName;
            formControls.lastName.value = user.lastName;
            formControls.description.value = user.description;
            formControls.photo.url = user.photo;
            const currentInfo = {...this.state.currentInfo};
            currentInfo.firstName = user.firstName;
            currentInfo.lastName = user.lastName;
            currentInfo.description = user.description;
            currentInfo.photo = user.photo;
            this.setState({formControls:formControls});
            this.setState({currentInfo:currentInfo});

        });
    }

    handleUserDataSaveButton(event: React.MouseEvent<HTMLButtonElement>) {
        event.preventDefault();

        let isValid = true;
        let formControls = {...this.state.formControls};

        Object.keys(formControls).forEach((controlName) => {

            let control = {...formControls[controlName]};

            control.showValidate = true;
            formControls[controlName] = control;

            if (!control.valid && isValid) {
                isValid = false;
            }
        });

        this.setState({
            formControls
        });

        if (isValid) {
            console.log({
                name: formControls.name.value,
                email: formControls.email.value,
                password: formControls.password.value,
            });
        }
        axios.put('http://localhost:3000/mockups/user.json', {
            firstName: this.state.formControls.firstName.value,
            lastName: this.state.formControls.lastName.value,
            description: this.state.formControls.description.value,
            photo: this.state.formControls.photo.url,
        }).then(res => {
            this.setState({editMode: false});
            this.setState({currentInfo: {firstName: {value: this.state.formControls.firstName.value}}});
            this.setState({currentInfo: {lastName: {value: this.state.formControls.lastName.value}}});
            this.setState({currentInfo: {description: {value: this.state.formControls.description.value}}});
            this.setState({currentInfo: {photo: {url: this.state.formControls.photo.url, file: this.state.formControls.photo.file}}});
        }).catch(error => {
            if (error.response.status === 400) {
                // TODO: some validations
            }
        });
    }

    handleCancelButton = () => {
        this.setState({editMode: false});
        const formControls = {...this.state.formControls};
        formControls.firstName.value = this.state.currentInfo.firstName;
        formControls.lastName.value = this.state.currentInfo.lastName;
        formControls.description.value = this.state.currentInfo.description;
        formControls.photo.url = this.state.currentInfo.photo;
        this.setState({formControls:formControls});
    };

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

    validateControl = (value: string, validation: any) => {
        if (!validation) {
            return {isValid: true, errorMessage: ''};
        }

        let validator = new Validation();

        let isValid = true;
        let errorMessage = '';

        if (validation.required && isValid) {
            isValid = !validator.isEmpty(value);
            if (!isValid) errorMessage = 'Field is required.';
        }

        if (validation.minLength && isValid) {
            isValid = validator.checkMinLength(value, validation.minLength);
            if (!isValid) errorMessage = 'You should put minimum ' + validation.minLength + ' chars.';
        }


        return {isValid: isValid, errorMessage: errorMessage};
    };

    onChangeHandler = (event: React.FormEvent<HTMLInputElement>, controlName: string) => {
        const formControls = {...this.state.formControls};
        const control = {...formControls[controlName]};

        control.value = (event.target as HTMLInputElement).value;
        let validateControlInfo = this.validateControl(control.value, control.validation);

        control.valid = validateControlInfo.isValid;
        if (validateControlInfo.errorMessage !== '') {
            control.errorMessage = validateControlInfo.errorMessage;
        }

        formControls[controlName] = control;

        this.setState({
            formControls
        });
    };

    render() {
        return (
            <div>
                <div className={"user-info-block " + (this.state.editMode ? 'hide' : 'show')}>
                    <div className="avatar-upload">
                        <div className="avatar-edit">
                            <input type="file" id="imageUpload"
                                   onChange={(event: FormEvent<HTMLInputElement>) => this.onFileChangeHandler((event.target as HTMLInputElement).files)}/>
                        </div>
                        <div className="avatar-preview">
                            <img src={this.state.formControls.photo.url} className="avatar-preview" alt="Avatar"/>
                        </div>
                    </div>
                    <h3 className="user-name">{this.state.formControls.firstName.value} {this.state.formControls.lastName.value}</h3>
                    <p className="user-description">
                        {this.state.formControls.description.value}
                    </p>
                    <div className={'action-panel'}>
                        <button type={"button"} className={"btn btn-danger"} onClick={e => this.setState({editMode: true})}>Edit</button>
                    </div>
                </div>
                <div className={"user-info-block " + (this.state.editMode ? 'show' : 'hide')}>
                    <div className="avatar-upload">
                        <div className="avatar-edit">
                            <input type="file" id="imageUpload"
                                   onChange={(event: FormEvent<HTMLInputElement>) => this.onFileChangeHandler((event.target as HTMLInputElement).files)}/>
                            <label htmlFor="imageUpload">
                                <Create/>
                            </label>
                        </div>
                        <div className="avatar-preview">
                            <img src={this.state.formControls.photo.url} className="avatar-preview" alt="Avatar"/>
                        </div>
                    </div>
                    <div className={"row"} style={{paddingTop: "15px"}}>
                        <div className={"col-md-6"}>
                            <Input
                                type={this.state.formControls.firstName.type}
                                placeholder={this.state.formControls.firstName.placeholder}
                                valid={this.state.formControls.firstName.valid}
                                iconClassName={this.state.formControls.firstName.iconClassName}
                                showValidate={this.state.formControls.firstName.showValidate}
                                errorMessage={this.state.formControls.firstName.errorMessage}
                                onChange={(e: FormEvent<HTMLInputElement>) => this.onChangeHandler(e, "firstName")}
                                value={this.state.formControls.firstName.value}/>
                        </div>
                        <div className={"col-md-6"}>
                            <Input
                                type={this.state.formControls.lastName.type}
                                placeholder={this.state.formControls.lastName.placeholder}
                                valid={this.state.formControls.lastName.valid}
                                iconClassName={this.state.formControls.lastName.iconClassName}
                                showValidate={this.state.formControls.lastName.showValidate}
                                errorMessage={this.state.formControls.lastName.errorMessage}
                                onChange={(e: FormEvent<HTMLInputElement>) => this.onChangeHandler(e, "lastName")}
                                value={this.state.formControls.lastName.value}/>
                        </div>
                    </div>
                    <Input
                        type={this.state.formControls.description.type}
                        placeholder={this.state.formControls.description.placeholder}
                        valid={this.state.formControls.description.valid}
                        iconClassName={this.state.formControls.description.iconClassName}
                        showValidate={this.state.formControls.description.showValidate}
                        errorMessage={this.state.formControls.description.errorMessage}
                        onChange={(e: FormEvent<HTMLInputElement>) => this.onChangeHandler(e, "description")}
                        value={this.state.formControls.description.value}/>
                    <div className={'action-panel'} style={{paddingTop: "15px"}}>
                        <button className={"btn btn-primary"} style={{marginRight: "15px"}}
                                onClick={this.handleCancelButton}>Cancel
                        </button>
                        <button className={"btn btn-success"}
                                onClick={e => this.handleUserDataSaveButton(e)}>Save
                        </button>
                    </div>
                </div>

            </div>
        );
    }
}

export default UserInfo;