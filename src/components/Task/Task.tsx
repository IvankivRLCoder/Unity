import React from 'react';
import task from './task.svg';
import './Task.scss';
import Carousel from "react-bootstrap/Carousel";

function Task() {
    return (
        <div className="task-page">
            <div className="container-fluid">
                <div className="row">
                    <div className="col-12 col-md-12 col-lg-5">
                        <div className="task-photos default-task-block">
                        	<Carousel nextIcon={(<i className="fas fa-chevron-right  task-carousel-arrow"></i>)} prevIcon={(<i className="fas fa-chevron-left  task-carousel-arrow"></i>)}>
                                <Carousel.Item>
                                    <img className="d-block w-100" src={task} alt="First slide"/>
                                </Carousel.Item>
                                <Carousel.Item>
                                    <img className="d-block w-100" src={task} alt="Second slide"/>
                                </Carousel.Item>
                                <Carousel.Item>
                                    <img className="d-block w-100" src={task} alt="Third slide"/>
                                </Carousel.Item>
                                <Carousel.Item>
                                    <img className="d-block w-100" src={task} alt="Fourth slide"/>
                                </Carousel.Item>
                                <Carousel.Item>
                                    <img className="d-block w-100" src={task} alt="Fifth slide"/>
                                </Carousel.Item>
                                <Carousel.Item>
                                    <img className="d-block w-100" src={task} alt="Sixth slide"/>
                                </Carousel.Item>
                            </Carousel>
                        </div>
                    </div>
                    <div className="col-12 col-md-12 col-lg-7">
                        <div className="task-additional-info default-task-block">
                            <h1 className="task-name">
                                Task name
                            </h1>
                            <h3 className="task-participants">
                                3/10 participants
                            </h3>
                            <h3 className="task-organizer">
                                Organized by: <a href="/user">Nazar Koval</a>
                            </h3>
                            <p className="task-description">Lorem ipsum dolor sit amet, consectetur adipiscing
                                elit,sed
                                do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                                veniam,
                                quisnostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                                Duis
                                aute irure dolor inreprehenderit in voluptate velit esse cillum dolore eu fugiat
                                nulla
                                pariatur. Excepteur sint occaecatcupidatat non proident, sunt in culpa qui officia
                                deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur
                                adipiscing
                                elit,sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
                                minim
                                veniam, quisnostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                                consequat. Duis aute irure dolor inreprehenderit in voluptate velit esse cillum
                                dolore
                                eu fugiat nulla pariatur</p>
                            <button className="btn participate-btn">
                                Participate
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Task;