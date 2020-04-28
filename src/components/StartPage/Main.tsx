import React, {Component} from 'react';
import InfiniteScroll from 'react-infinite-scroll-component';
import Sidebar from './Sidebar/Sidebar';
import './Main.scss';
import axios from 'axios';
import Task from './Task/Task';
import ITask from './Task/ITask';
import {CONFIG} from "../../config";
import IFilterParam from "./Task/IFilterParam";

interface IState {
    tasksFromApi: ITask[],
    page: number,
    hasMoreTasks: boolean
    filterParams: IFilterParam
}

export class Main extends Component<any, IState> {

    state = {
        tasksFromApi: [],
        page: 1,
        hasMoreTasks: true,
        filterParams: {
            priority: "",
            order: "",
            category: ""
        }
    };

    componentDidMount() {
        this.getNewTasks();
    }

    setFilterParams = (params: IFilterParam) => {
        console.log(params);
        this.setState({
            page: 1,
            filterParams: params,
            tasksFromApi: []
        });
        console.log(this.state.page);
        this.getNewTasks();

    };

    getNewTasks = () => {
        let tasks: ITask[] = [...this.state.tasksFromApi];
        console.log(this.state.filterParams);
        let paramApiUrl = "&category="+this.state.filterParams.category+"&order="+this.state.filterParams.order+"&priority="+this.state.filterParams.priority;
        axios.get(CONFIG.apiServer + "tasks/?pageNumber=" + this.state.page + paramApiUrl).then(res => {
            const newTasks = res.data.entities as ITask[];

            newTasks.forEach((item: ITask) => {
                let creatorData = item.creator;
                item.creator = creatorData.firstName + " " + creatorData.lastName;
                tasks.push(item);
            });
            this.setState({
                tasksFromApi: tasks,
                page: this.state.page + 1
            });
        }).catch(error => {
            this.setState({hasMoreTasks: false});
        });
    }

    renderTasks = () => {
        const tasks = [...this.state.tasksFromApi];

        return tasks.map((task: ITask, index: number) => (
                <Task
                    id={task.id}
                    key={index}
                    title={task.title}
                    creator={task.creator}
                    description={task.description}
                    approvedParticipants={task.approvedParticipants}
                    numberOfParticipants={task.numberOfParticipants}
                    priority={task.priority}
                />
            )
        );
    };



    render() {
        return (
            <div className='main_page'>
                <div className="container-fluid">
                    <div className="row">
                        <div className="col-md-4 col-sm-12">
                            <Sidebar setFilterParams={this.setFilterParams}/>
                        </div>
                        <div className="col-md-8 col-sm-12">
                            <InfiniteScroll
                                dataLength={this.state.tasksFromApi.length}
                                next={this.getNewTasks}
                                hasMore={this.state.hasMoreTasks}
                                loader={<p style={{textAlign: 'center'}}>Loading...</p>}
                                endMessage={
                                    <p style={{textAlign: 'center', color: 'gray'}}>No more tasks!</p>
                                }>
                                {this.renderTasks()}
                            </InfiniteScroll>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Main;