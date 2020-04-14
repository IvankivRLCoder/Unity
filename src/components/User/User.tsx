import React from 'react';
import Tree from './Tree/Tree';
import UserInfo from './UserInfo/UserInfo';
import axios from "axios";
import {CONFIG} from "../../config";

class User extends React.Component<any, any> {

    state = {
        user: null
    };

    getUserInfo = async () => {
        let res = await axios(CONFIG.apiServer + 'users/' + this.props.match.params.id);
        return await res;
    };

    componentDidMount(): void {
        this.getUserInfo().then(data => {
            this.setState({user: data.data})
        });
    }

    render() {
        if (!this.state.user) {
            return (<em>Loading...</em>)
        }

        return (<div id="user_page" style={{width: "100%"}}>
            <div className="container-fluid">
                <div className="row">
                    <div className="col-lg-8 col-12 order-2 order-lg-1">
                        <Tree/>
                    </div>
                    <div className="col-lg-4 col-12 order-1 order-lg-2">
                        <UserInfo user={this.state.user}/>
                    </div>
                </div>
            </div>
        </div>)
    };
}

export default User;