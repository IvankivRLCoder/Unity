import React from 'react';
import Task from './Task/Task';
import Sidebar from './Sidebar/Sidebar';

function Main() {
    return (
        <div id="user_page">
            <div className="container-fluid" >
                <div className="row">
                    <div className="col-md-4 col-sm-12">
                        <Sidebar/>
                    </div>
                    <div className="col-md-8 col-sm-12">
                        <Task priority={"red"}/>
                        <Task priority={"red"}/>
                        <Task priority={"yellow"}/>
                        <Task priority={"yellow"}/>
                        <Task priority={"green"}/>

                    </div>
                </div>
            </div>
        </div>
    );
}

export default Main;