import React from 'react';
import image from './task.svg';
import './Task.scss';

function Task(props: { priority: any; }) {
    return (
        <div className='main-task-block'>
            <div className="priority" style={{background:props.priority}}/>
            <div className='container' >
                <div className='row'>
                    <div className='col-md-4  '>
                        <div className='task-img'>
                            <figure><img src={image} alt="task-img"/></figure>
                        </div>
                    </div>
                    <div className='col-md-8   task-main-data'>
                            <div className='task-name'><h1>Some name</h1></div>
                            <div className='task-participants'><h4>Participants: <i>3/10 </i></h4></div>
                            <div className='task-owner'><h4>Owner: Robert De Niro</h4></div>
                    </div>
                    <div className='col-md-12 task-description'> Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                        sed
                        do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                        quis
                        nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute
                        irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
                        pariatur.
                        Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt
                        mollit
                        anim id est laborum.
                    </div>
                </div>
            </div>

        </div>
    );
}

export default Task;