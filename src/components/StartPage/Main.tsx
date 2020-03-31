import React, {Component, Suspense,lazy} from 'react';
import Sidebar from './Sidebar/Sidebar';
import './Main.scss';
import axios from 'axios';
const Task = lazy(()=> import('./Task/Task'));


export class Main extends Component {

    state = {
        tasksFromApi: [{
            "name": "",
            "owner": "",
            "partisipants": "",
            "description": ""
        }]
        };


    componentDidMount () {
        axios.get('http://localhost:3000/mockups/tasks.json').then(res => {
            const tasks = res.data;
            console.log(tasks);
            this.setState({tasksFromApi:tasks});
            console.log(this.state.tasksFromApi);
        });
    }

    renderTasks = () => {

        return this.state.tasksFromApi.map((task) => {
            return (
                <Task
                    name={task.name}
                    owner={task.owner}
                    description={task.description}
                    partisipants={task.partisipants}
                    priority={"red"}
                />
            )
        });

    };

    render() {
        return (
            <div className='main_page'>
                <div className="container-fluid">
                    <div className="row">
                        <div className="col-md-4 col-sm-12">
                            <Sidebar/>
                        </div>
                        <div className="col-md-8 col-sm-12">
                            <Suspense fallback={<div>Loading...</div>}>
                                {this.renderTasks()}
                            </Suspense>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Main;