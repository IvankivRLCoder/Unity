import React from 'react';
import avatar from './avatar.png';
import './UserInfo.scss';

function UserInfo() {
    return (
        <div className="user-info-block">
            <img src={avatar} className="user-info-img" alt="Avatar"/>
            <h3 className="user-name">Nazar Koval</h3>
            <p className="user-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit,sed do
                eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                quisnostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute
                irure dolor inreprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                Excepteur sint occaecatcupidatat non proident, sunt in culpa qui officia deserunt mollit
                anim id est laborum.
            </p>
        </div>
    );
}

export default UserInfo;