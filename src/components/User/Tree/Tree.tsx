import React from 'react';
import tree from './tree.svg';
import './Tree.scss';
import Achievement from './Achievement/Achievement';
import axios from "axios";

class Tree extends React.Component{

    state = {
        achievements: [],
        totalAmount: 0
    };

    componentDidMount () {
        axios.get('http://localhost:3000/mockups/user.json').then(res => {
            const user = res.data;
            this.setState({
                achievements: user.achievements,
                totalAmount: user.totalAmount
            });
        });
    }

    renderAchievements() {
        return this.state.achievements.map((achievement : any) => {
            return <Achievement name={achievement.name} id={achievement.id}/>
        });
    }

    render() {
        console.log(this.state.achievements.length, this.state.totalAmount)
        return (
            <div className="tree-block">
                <div className="tree-img">
                    <img className="tree-img-colorful" src={tree} alt="Tree" style={{height: this.state.achievements.length / this.state.totalAmount * 100 + "%"}}/>
                    <img className="tree-img-grayscale" src={tree} alt="Tree"/>
                </div>
                <div className="achievements">
                    {this.renderAchievements()}
                </div>
            </div>

        );
    }
}


export default Tree;