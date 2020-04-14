import React from 'react';
import image from './task.svg';
import './Task.scss';


export default (props: {
    owner: any;
    description: any;
    partisipants: any;
    priority: any;
    name: React.ReactNode; }) =>(
        <div className='main-task-block'>
            <div className="priority" style={{background: props.priority}}/>
            <div className='container'>
                <div className='row'>
                    <div className='col-md-4  '>
                        <div className='task-img'>
                            <figure><img src={image} alt="task-img"/></figure>
                        </div>
                    </div>
                    <div className='col-md-8   task-main-data'>
                        <div className='task-name'><h1>{props.name}</h1></div>
                        <div className="submain_data">
                            <div className='task-participants'><h4>Participants: <i>{props.partisipants} </i></h4></div>
                            <div className='task-owner'><h4>Owner: {props.owner}</h4></div>
                        </div>
                    </div>
                    <div className='col-md-12 task-description'> {props.description}
                    </div>
                </div>
            </div>

        </div>
    )