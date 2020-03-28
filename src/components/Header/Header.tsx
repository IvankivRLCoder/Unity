import React from 'react';
import './Header.scss';

function Header() {
    return (
        <nav className="navbar main-navbar">
            <a className="navbar-brand" href="/"><i className="fas fa-hands-helping"/> Unity</a>
            <div>
                <ul className="navbar-nav navbar-expand ml-auto">
                    <li className="nav-item">
                        <a className="nav-link p-0" href="/user">
                            <i className="fas fa-user"/>
                        </a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link p-0" href="/login">
                            <i className="fas fa-sign-out-alt"/>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    );
}

export default Header;