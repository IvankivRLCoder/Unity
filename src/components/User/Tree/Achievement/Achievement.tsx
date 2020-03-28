import React from 'react';
import './Achievement.scss';
import achievementImage from './achievement.png';

function Achievement() {
    return (
        <div className="achievement-wrapper">
            <div className="achievement">
                <div className="achievement-img">
                    <img src={achievementImage} alt=""/>
                </div>
                <div className="achievement-description">
                    <p>Lorem ipsum dolor</p>
                </div>
            </div>
        </div>
    );
}

export default Achievement;