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
                quisnostrud exercitation ullamco laboris nisi ut aliquip ex ea
            </p>
        </div>
    );
}

export default UserInfo;