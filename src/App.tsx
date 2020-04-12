import React from 'react';
import {Route, Redirect} from 'react-router-dom';
import User from './components/User/User';
import Login from './components/Login_Registration/Login';
import Registration from './components/Login_Registration/Registration';
import Main from './components/StartPage/Main';
import Task from './components/Task/Task';
import Layout from './hoc/Layout/Layout';

import './App.scss';
import Auth from "./utils/Auth/Auth";

function App() {
  return (
    <Layout>
        <Route path="/user" exact component={User} />
        <Route path="/" exact component={Main} />
        <Route path="/task" exact component={Task}/>
        <Route path="/login" render={() => (!Auth.isLoggedIn ? <Login/> : <Redirect to="/" />)} />
        <Route path="/registration" render={() => (!Auth.isLoggedIn ? <Registration/> : <Redirect to="/" />)} />
    </Layout>
  );
}

export default App;
