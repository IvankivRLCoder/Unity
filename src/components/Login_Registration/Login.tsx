import React, {Component, FormEvent} from 'react';
import photo from './signin-image.jpg';
import './LoginRegister.scss';

class Login extends Component {
    render() {
        return (
            <div className="main-sign-wrapper">
                <div className="container sign-main-block">
                    <div className="row">
                        <div className=" col-md-6 col-xs-12">
                            <div className="text-center sign-form">
                                <h2 className="form-title">Login</h2>
                                <form method="POST" className="register-form" id="login-form" onSubmit={(event: FormEvent<HTMLFormElement>) => {event.preventDefault();window.location.href="/"}}>
                                    <div className="form-group">
                                        <div className="form-div">
                                            <label className="label-icon" htmlFor="email"><i
                                                className="fas fa-envelope material-icons-name"/></label>
                                            <input type="text" className="sign-form-input form-control" id="email"
                                                   placeholder="Email"/>
                                        </div>
                                        <p className="sign-form-error-text"/>
                                    </div>
                                    <div className="form-group">
                                        <div className="form-div">
                                            <label className="label-icon" htmlFor="pass"><i
                                                className="fas fa-lock"/></label>
                                            <input type="password" className="sign-form-input form-control" id="pass"
                                                   placeholder="Password"/>
                                        </div>
                                        <p className="sign-form-error-text"/>
                                    </div>
                                    <div className="form-check form-group text-left">
                                        <input type="checkbox" className="form-check-input"/>
                                        <label className="remember-label">Remember me</label>
                                    </div>
                                    <div className="form-group form-div form-button">
                                        <input type="submit" id="signin" className="form-submit" value="Log in"/>
                                    </div>
                                </form>
                                <div className="social-login">
                                    <span className="social-label">Or login with</span>
                                    <ul className="socials">
                                        <li><a href="https://uk-ua.facebook.com"><i className="fab fa-facebook-f"/></a>
                                        </li>
                                        <li><a href="https://twitter.com/?lang=uk"><i className="fab fa-twitter"/></a>
                                        </li>
                                        <li><a href="https://www.google.com.ua/?hl=ru"><i
                                            className="fab fa-google"/></a>
                                        </li>
                                    </ul>
                                </div>
                                <span className="login-label">Not a member?</span>
                                <a href="/registration" className="signin-image-link">Sign Up</a>
                            </div>
                        </div>
                        <div className="signin-image col-md-6 col-xs-12">
                            <figure><img src={photo} className="image" alt="sing in"/></figure>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Login;
