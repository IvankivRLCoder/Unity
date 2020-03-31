import React from 'react';
import Tree from './Tree/Tree';
import UserInfo from './UserInfo/UserInfo';

class User extends React.Component {

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
                    <div className="col-lg-4 col-12 order-1 order-lg-2">
                        <UserInfo/>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default User;