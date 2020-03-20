import React from 'react';
import avatar from './avatar.png';
import './UserInfo.scss';

function UserInfo() {
    return (
        <div className="user-info-block">
		<div className="container">
    			<div className="rightPart">
        			<img src={avatar} className="avatar" alt="Avatar"/>
            				<div className="form-group">
                				<p className="person-name">Nazar Koval</p>
            				</div>
           			 <div className="form-group">
                			<p className="person-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit,sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quisnostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor inreprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecatcupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
            			</div>
    			</div>
		</div>
	</div>
    );
}

export default UserInfo;
