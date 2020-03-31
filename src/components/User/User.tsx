import React from 'react';
import Tree from './Tree/Tree';
import UserInfo from './UserInfo/UserInfo';

class User extends React.Component {
    userInfo: any;
    user: any = {
        name: 'Nazar',
        surname: 'Koval',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit,sed do eiusmod tempor incididunt ut ' +
            'labore et dolore magna aliqua. Ut enim ad minim veniam, quisnostrud exercitation ullamco laboris nisi ut aliquip ex ea',
        achievements: []
    };
    constructor(options: any) {
        super(options);
        // this.userInfo = new UserInfo(this.user);
    }
    render() {return (<div id="user_page" style={{width: "100%"}}>
                <div className="container-fluid">
                    <div className="row">
                        <div className="col-lg-8 col-12 order-2 order-lg-1">
                            <Tree/>
                        </div>
                        <div className="col-lg-4 col-12 order-1 order-lg-2">
                            <UserInfo/>
                        </div>
                    </div>
                </div>
            </div>);
    }
}

export default User;