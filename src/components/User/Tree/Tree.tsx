import React from 'react';
import tree from './tree.svg';
import './Tree.scss';
import Achievement from './Achievement/Achievement';

function Tree() {

    return (
        <div className="tree-block">
            <div className="tree-img">
                <img className="tree-img-colorful" src={tree} alt="Tree" style={{height: '100%'}}/>
                <img className="tree-img-grayscale" src={tree} alt="Tree"/>
            </div>
            <div className="container-fluid achievements">
                <div className="row achievement-row">
                    <div className="achievement achievement-left col-3">
                        <Achievement/>
                        <Achievement/>
                        <Achievement/>
                        <Achievement/>
                        <Achievement/>
                    </div>
                    <div className="col-6"></div>
                    <div className="achievement achievement-right col-3">
                        <Achievement/>
                        <Achievement/>
                        <Achievement/>
                        <Achievement/>
                        <Achievement/>
                    </div>
                </div>
            </div>
        </div>

    );
}


export default Tree;