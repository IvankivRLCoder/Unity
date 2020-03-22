import React from 'react';
import photo from './signup-image.jpg';
import './LoginRegister.scss';

function Login() {
    return (
    <div className="main-sign-wrapper">
     <div className="container main">
        <div className="signin-content row">
            <div className="signin-form col-md-6 col-xs-12">
                <h2 className="form-title">Sign Up</h2>
                <form method="POST" className="register-form" id="login-form">
                    <div className="form-group form-div">
                        <label className="label-icon"><i className="fas fa-user material-icons-name"></i></label>
                        <input type="text" className="form" id="your_name" placeholder="Your Name"/>
                    </div>
                    <div className="form-group form-div">
                        <label className="label-icon email"><i className="fas fa-envelope"></i></label>
                        <input type="email" className="form" placeholder="Email"/>
                    </div>
                    <div className="form-group form-div">
                        <label className="label-icon"><i className="fas fa-lock"></i></label>
                        <input type="password" className="form" placeholder="Password"/>
                    </div>
                    <div className="form-group form-div">
                        <label className="label-icon"><i className="fas fa-lock"></i></label>
                        <input type="password" className="form" placeholder="Confirm password"/>
                    </div>
                    <div className="form-group form-div form-button">
                        <input type="submit" id="signin" className="form-submit" value="Register"/>
                    </div>
                </form>
                <div className="social-login">
                    <span className="social-label">Or register with</span>
                    <ul className="socials">
                        <li><a href="https://uk-ua.facebook.com"><i className="fab fa-facebook-f"></i></a></li>
                        <li><a href="https://twitter.com/?lang=uk"><i className="fab fa-twitter"></i></a></li>
                        <li><a href="https://www.google.com.ua/?hl=ru"><i className="fab fa-google"></i></a></li>
                    </ul>
                </div>
                <span className="login-label">Have account?</span><a href="/login" className="signin-image-link">Sign In</a>
            </div>
            <div className="signin-image col-md-6 col-xs-12">
                <figure><img src={photo} className="image" alt="sing up"/></figure>
            </div>
        </div>
    </div>
</div>);
}

export default Login;
