import React from 'react';
import Task from './Task/Task';
import Sidebar from './Sidebar/Sidebar';

function Main() {
    return (
        <div id="user_page">
            <div className="container-fluid">
                <div className="row">
                    <div className="col-md-3 ">
                        <Sidebar/>
                    </div>
                    <div className="col-md-9 col-sm-12">
                        <Task/>
                        <Task/>
                        <Task/>
                        <Task/>
                        <Task/>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Main;