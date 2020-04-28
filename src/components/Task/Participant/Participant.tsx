import React, {Component} from "react";
import './Participant.scss';
import IParticipant from "./IParticipant";
import {Link} from "react-router-dom";

interface IProps {
    participant: IParticipant
}

class Participant extends Component<IProps> {
    render() {
        return (
            <div className="card mb-3 default-task-block task-participant-block">
                <div className="row no-gutters">
                    <div className="col-md-1 justify-content-center align-items-center d-flex">
                        <div className="task-participant-img-wrapper">
                            <Link className="task-participant-link" to={"/user/" + this.props.participant.user.id}>
                                <img src="/img/avatar.png" alt=""/>
                            </Link>
                        </div>
                    </div>
                    <div className="col-md-11">
                        <div className="card-body">
                            <h4 className="card-title">
                                <Link className="task-participant-link" to={"/user/" + this.props.participant.user.id}>
                                    {this.props.participant.user.firstName} {this.props.participant.user.lastName}
                                </Link>
                                {this.props.participant.approved ? (<span className="ml-1 badge badge-pill badge-success">Approved</span>) : ''}
                            </h4>
                            <p className="card-text"><small className="text-muted">{this.props.participant.participationDate}</small></p>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Participant;
