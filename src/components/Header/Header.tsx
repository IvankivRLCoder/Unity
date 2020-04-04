import React from 'react';
import './Header.scss';
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/esm/Button";
import avatar from "../User/UserInfo/avatar.png";

class Header extends React.Component{

    state = {
        openedModal: false
    };


    render() {
        return (
            <nav className="navbar main-navbar">
                <a className="navbar-brand" href="/"><i className="fas fa-hands-helping"/> Unity</a>
                <div>
                    <ul className="navbar-nav navbar-expand ml-auto">
                        <li className="nav-item">
                            <a className="nav-link p-0" style={{"cursor": "pointer"}} onClick={() => this.setState({openedModal: true})}>
                                <i className="fas fa-plus"/>
                            </a>
                        </li>
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
                <Modal size="lg" show={this.state.openedModal} onHide={() => this.setState({openedModal: false})}>
                    <Modal.Header closeButton>
                        <Modal.Title>Add new task</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <div className={"row"}>
                            <div className={"col-lg-4"}>
                                <img src={avatar} style={{"width": "70%"}}/>
                                <input style={{"paddingTop": "10px"}} type={"file"}/>
                                <div className={"row"} style={{"paddingTop": "10px"}}>
                                    <div className={"col-lg-4"}>
                                        <img src={avatar} style={{"width": "100%"}}/>
                                    </div>
                                    <div className={"col-lg-4"}>
                                        <img src={avatar} style={{"width": "100%"}}/>
                                    </div>
                                    <div className={"col-lg-4"}>
                                        <img src={avatar} style={{"width": "100%"}}/>
                                    </div>
                                </div>
                            </div>
                            <div className={"col-lg-8"}>
                                <div className="form-group">
                                    <label>Task name</label>
                                    <input className="form-control"/>
                                </div>
                                <div className="form-group">
                                    <label>Task description</label>
                                    <textarea style={{"height" : "150px"}} className="form-control"/>
                                </div>
                            </div>
                        </div>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="danger" onClick={() => this.setState({openedModal: false})}>
                            Close
                        </Button>
                        <Button variant="success" onClick={() => this.setState({openedModal: false})}>
                            Create
                        </Button>
                    </Modal.Footer>
                </Modal>
            </nav>
        );
    }
}

export default Header;