import React from 'react';
import image from './task.svg';
import './Task.scss';
import ITask from './ITask'
import {Link} from "react-router-dom";

export default (props: ITask) => (
    <Link to={"/task/" + props.id} className="task-link">
        <div className='main-task-block' onClick={() => window.location.href = "/task/" + props.id}>
            <div className={"priority " + props.priority} />
            <div className='container'>
                <div className='row'>
                    <div className='col-md-4  '>
                        <div className='task-img'>
                            <figure><img src={image} alt="task-img"/></figure>
                        </div>
                    </div>
                    <div className='col-md-8 task-main-data'>
                        <div className='task-name'><h1>{props.title}</h1></div>
                        <div className="submain_data">
                            <div className='task-participants'>
                                <h4>Participants: <i>{props.approvedParticipants} / {props.numberOfParticipants} </i>
                                </h4></div>
                            <div className='task-owner'><h4>Owner: {props.creator}</h4></div>
                        </div>
                    </div>
                    <div className='col-md-12 task-description'> {props.description}
                    </div>
                </div>
            </div>

        </div>
    </Link>
)