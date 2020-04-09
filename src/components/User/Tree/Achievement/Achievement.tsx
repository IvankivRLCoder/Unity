import React from 'react';
import './Achievement.scss';
import achievementImage from './achievement.png';

export interface IProps {
    name: string,
    id: number
}

class Achievement extends React.Component<IProps> {

    render() {
        return (
            <div className="achievement-wrapper">
                <a href="/task">
                    <div className="achievement">
                        <div className="achievement-img">
                            <img src={achievementImage} alt=""/>
                        </div>
                        <div className="achievement-description">
                            <p>{this.props.name}</p>
                        </div>
                    </div>
                </a>
            </div>
        );
    }
}

export default Achievement;