import React, {Component} from 'react';
import taskImg from './task.svg';
import './Task.scss';
import Carousel from "react-bootstrap/Carousel";
import axios from "axios";
import {CONFIG} from "../../config";
import ITask from "../StartPage/Task/ITask";
import Participant from "./Participant/Participant";
import IParticipant from "./Participant/IParticipant";

interface IState {
    task: ITask | null,
    participants: IParticipant[],
    isTask: boolean,
}

class Task extends Component<any, IState> {
    state = {
        task: {
            id: 0,
            title: '',
            creator: {
                firstName: '',
                id: 0,
                lastName: ''
            },
            description: '',
            approvedParticipants: 0,
            numberOfParticipants: 0,
            priority: ''
        },
        isTask: true,
        participants: []
    }

    componentDidMount = () => {
        axios(CONFIG.apiServer + 'tasks/' + this.props.match.params.id).then(res => {
            this.setState({task: res.data});
            axios(CONFIG.apiServer + 'participants/' + this.props.match.params.id + "/users").then(res1 => {
                this.setState({participants: res1.data});
            });
        }).catch(error => {
            this.setState({isTask: false});
        });
    }

    renderParticipants = () => {
        return this.state.participants.map((participant: IParticipant, index: number) => (
            <Participant participant={participant}/>
        ));
    }

    render () {
        if (this.state.task.id !== 0) {
            return (
                <div className="task-page">
                    <div className="container-fluid">
                        <div className="row">
                            <div className="col-12 col-lg-5">
                                <div className="task-photos default-task-block">
                                    <Carousel nextIcon={(<i className="fas fa-chevron-right  task-carousel-arrow"/>)}
                                              prevIcon={(<i className="fas fa-chevron-left  task-carousel-arrow"/>)}>
                                        <Carousel.Item>
                                            <img className="d-block w-100" src={taskImg} alt="First slide"/>
                                        </Carousel.Item>
                                        <Carousel.Item>
                                            <img className="d-block w-100" src={taskImg} alt="Second slide"/>
                                        </Carousel.Item>
                                        <Carousel.Item>
                                            <img className="d-block w-100" src={taskImg} alt="Third slide"/>
                                        </Carousel.Item>
                                        <Carousel.Item>
                                            <img className="d-block w-100" src={taskImg} alt="Fourth slide"/>
                                        </Carousel.Item>
                                        <Carousel.Item>
                                            <img className="d-block w-100" src={taskImg} alt="Fifth slide"/>
                                        </Carousel.Item>
                                        <Carousel.Item>
                                            <img className="d-block w-100" src={taskImg} alt="Sixth slide"/>
                                        </Carousel.Item>
                                    </Carousel>
                                </div>
                            </div>
                            <div className="col-12 col-lg-7">
                                <div className="task-additional-info default-task-block">
                                    <h1 className="task-name">{this.state.task.title}</h1>
                                    <h3 className="task-participants">
                                        {this.state.task.approvedParticipants}/{this.state.task.numberOfParticipants} participants
                                    </h3>
                                    <h3 className="task-organizer">
                                        Organized by: <a href={"/user/" + this.state.task.creator.id}>{this.state.task.creator.firstName + " " + this.state.task.creator.lastName}</a>
                                    </h3>
                                    <p className="task-description">{this.state.task.description}</p>
                                    <button className="btn participate-btn">Participate</button>
                                </div>
                                <div className="task-participants">
                                    {this.renderParticipants()}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            );
        } else if (this.state.isTask) {
            return (
                <div className="container-fluid">
                    <h2 className="text-center">Loading...</h2>
                </div>
            )
        } else {
            return (
                <div className="container-fluid">
                    <h2 className="text-center">Not Found</h2>
                </div>
            )
        }
    }
}

export default Task;
