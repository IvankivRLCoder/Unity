import React from 'react';
import photo from './signin-image.jpg';
import './LoginRegister.scss';

function Login() {
    return (
    <div className="main-sign-wrapper">
     <div className="container main">
        <div className="signin-content row">
            <div className="signin-form col-md-6 col-xs-12">
                <h2 className="form-title">Login</h2>
                <form method="POST" className="register-form" id="login-form">
                    <div className="form-group form-div">
                        <label className="label-icon"><i className="fas fa-user material-icons-name"></i></label>
                        <input type="text" className="form" id="your_name" placeholder="Your Name"/>
                    </div>
                    <div className="form-group form-div">
                        <label className="label-icon"><i className="fas fa-lock"></i></label>
                        <input type="password" className="form" id="your_pass" placeholder="Password"/>
                    </div>
                    <div className="form-group form-div remmeber">
                        <input type="checkbox" id="remember-me" className="agree-term" />
                        <label className="label-icon label-agree-term">Remember me</label>
                    </div>
                    <div className="form-group form-div form-button">
                        <input type="submit" id="signin" className="form-submit" value="Log in"/>
                    </div>
                </form>
                <div className="social-login">
                    <span className="social-label">Or login with</span>
                    <ul className="socials">
                        <li><a href="https://uk-ua.facebook.com"><i className="fab fa-facebook-f"></i></a></li>
                        <li><a href="https://twitter.com/?lang=uk"><i className="fab fa-twitter"></i></a></li>
                        <li><a href="https://www.google.com.ua/?hl=ru"><i className="fab fa-google"></i></a></li>
                    </ul>
                </div>
                <span className="login-label">Not a member?</span><a href="/registration" className="signin-image-link">Sign Up</a>
            </div>
            <div className="signin-image col-md-6 col-xs-12">
                <figure><img src={photo} className="image" alt="sing in"/></figure>
            </div>
        </div>
    </div>
    </div>);
}

export default Login;
