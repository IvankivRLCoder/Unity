import React from 'react';
import tree from './tree.svg';
import './Tree.scss';
import Achievement from './Achievement/Achievement';
import axios from "axios";

export type achievementT = {
    id: number,
    name: String
}

interface IProps {}

interface IState {
    achievements: achievementT[]
}

class Tree extends React.Component<IProps, IState> {

    constructor(props: IProps) {
        super(props);

        this.state ={
            achievements: []
        };
    }

    componentDidMount () {
        axios.get('http://localhost:3000/mockups/doneTasksByUser.json').then(res => {
            let loadedAchievements: [achievementT] = res.data;
            let achievementsToShow: achievementT[] = [];
            for (let i = 0; i < ((loadedAchievements.length <= 10) ? loadedAchievements.length: 10);i++) {
                achievementsToShow.push(loadedAchievements[i]);
            }

            this.setState({
                achievements: achievementsToShow
            });
        });
    }

    renderAchievements() {
        return this.state.achievements.map((achievement : any) => {
            return <Achievement name={achievement.name} id={achievement.id} key={achievement.id}/>
        });
    }

    render() {
        return (
            <div className="tree-block">
                <div className="tree-img">
                    <img className="tree-img-colorful" src={tree} alt="Tree" style={{height: (this.state.achievements.length * 10) + "%"}}/>
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