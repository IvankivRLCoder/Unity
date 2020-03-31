import React from 'react';
import avatar from './avatar.png';
import './UserInfo.scss';

class UserInfo extends React.Component {
    state = {
        name: "",
        surname: "",
        description: "",
        avatar: "",
        editMode: false
    };

    constructor(options:any) {
        super(options);
        console.log(options);
        // this.setState({
        //     name: options.name,
        //     surname: options.surname,
        //     decription: options.description
        // })
        this.state.name = options.name;
        this.state.surname = options.surname;
        this.state.description = options.description;
    }

    handleMode = (event: React.FormEvent<HTMLFormElement>):void => {

    };

    render() {
        return (
            <div>
                <div className={"user-info-block " + (this.state.editMode ? 'hide' : 'show')}>
                    <img src={avatar} className="user-info-img" alt="Avatar"/>
                    <h3 className="user-name">{this.state.name} {this.state.surname}</h3>
                    <p className="user-description">
                        {this.state.description}
                    </p>
                    <div className={'action-panel'}>
                        <button type={"button"} className={"btn btn-danger"} onClick={e => this.setState({editMode: true})}>Edit</button>
                    </div>
                </div>
                <div className={"user-info-block " + (this.state.editMode ? 'show' : 'hide')}>
                    <img src={avatar} className="user-info-img" alt="Avatar"/>
                    <div className={"row"} style={{paddingTop: "15px"}}>
                        <div className={"col-md-6"}>
                            <input className={"form-control "} placeholder={"Name"} defaultValue={this.state.name}/>
                        </div>
                        <div className={"col-md-6"}>
                            <input className={"form-control"} placeholder={"Surname"} defaultValue={this.state.surname}/>
                        </div>
                    </div>
                    <textarea className={"form-control user-description"} placeholder={"Enter your description"} defaultValue={this.state.description}>
                    </textarea>
                    <div className={'action-panel'} style={{paddingTop: "15px"}}>
                        <button className={"btn btn-primary"} style={{marginRight: "15px"}}
                                onClick={e => this.setState({editMode: false})}>Cancel
                        </button>
                        <button className={"btn btn-success"}
                                onClick={e => this.setState({editMode: false})}>Save
                        </button>
                    </div>
                </div>

            </div>
        );
    }
}

export default UserInfo;