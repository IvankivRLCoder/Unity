import React, {Component, FormEvent} from 'react';
import photo from './signup-image.jpg';
import './LoginRegister.scss';

class Registration extends Component {

    state = {
        name: "",
        email: "",
        password: "",
        repassword: "",

        errors: {
            name: "",
            email: "",
            password: "",
            repassword: ""
        }
    };

    handleSubmit = (event: FormEvent<HTMLFormElement>): void => {
        event.preventDefault();
        let errors: { [id: string]: any; } = {};

        // eslint-disable-next-line
        const validEmailRegex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if (this.state.email.length === 0)
            errors['email'] = 'Email cannot be empty';
        else if (!validEmailRegex.test(this.state.email))
            errors['email'] = 'Email is not valid';

        if (this.state.name.length === 0)
            errors['name'] = 'Name cannot be empty';
        else if (this.state.name.length < 3 || this.state.name.length > 60)
            errors['name'] = 'Name should have more than 3 characters and less than 60 characters';

        if (this.state.password.length === 0)
            errors['password'] = 'Password cannot be empty';
        else if (this.state.password.length < 8 || this.state.password.length > 30)
            errors['password'] = 'Password should have more than 8 characters and less than 30 characters';

        if (this.state.repassword.length === 0)
            errors['repassword'] = 'Re-password cannot be empty';
        else if (this.state.repassword !== this.state.password)
            errors['repassword'] = 'Re-password is not equal to password';

        if (Object.keys(errors).length === 0) {
            console.log({
                email: this.state.email,
                name: this.state.name,
                password: this.state.password,
            });
            //some logic after request
        }

        if (Object.keys(errors).length !== 0) {
            this.setState({
                errors: {
                    name: (errors.name) ? errors.name : '',
                    email: (errors.email) ? errors.email : '',
                    password: (errors.password) ? errors.password : '',
                    repassword: (errors.repassword) ? errors.repassword : ''
                }
            });
        }

    };

    render() {
        return (
            <div className="main-sign-wrapper">
                <div className="container sign-main-block">
                    <div className="row">
                        <div className="col-md-6 col-xs-12">
                            <div className="text-center sign-form">
                                <h2 className="form-title">Sign Up</h2>
                                <form method="POST" className="register-form" id="login-form"
                                      onSubmit={this.handleSubmit}>
                                    <div className="form-group">
                                        <div className="form-div">
                                            <label className="label-icon" htmlFor="name"><i className="fas fa-user material-icons-name"/></label>
                                            <input type="text"
                                                   className={"sign-form-input form-control " + (this.state.errors.name ? 'error' : '')}
                                                   id="name" placeholder="Your Name"
                                                   onChange={e => this.setState({name: e.target.value})}/>
                                        </div>
                                        <p className="sign-form-error-text">{this.state.errors.name}</p>
                                    </div>
                                    <div className="form-group">
                                        <div className="form-div">
                                            <label className="label-icon email" htmlFor="email"><i className="fas fa-envelope"/></label>
                                            <input type="text"
                                                   id="email"
                                                   className={"sign-form-input form-control " + (this.state.errors.email ? 'error' : '')}
                                                   placeholder="Email"
                                                   onChange={e => this.setState({email: e.target.value})}/>
                                        </div>
                                        <p className="sign-form-error-text">{this.state.errors.email}</p>
                                    </div>
                                    <div className="form-group">
                                        <div className="form-div">
                                            <label className="label-icon" htmlFor="password"><i className="fas fa-lock"/></label>
                                            <input type="password"
                                                   id="password"
                                                   className={"sign-form-input form-control " + (this.state.errors.password ? 'error' : '')}
                                                   placeholder="Password"
                                                   onChange={e => this.setState({password: e.target.value})}/>
                                        </div>
                                        <p className="sign-form-error-text">{this.state.errors.password}</p>
                                    </div>
                                    <div className="form-group">
                                        <div className="form-div">
                                            <label className="label-icon" htmlFor="repassword"><i className="fas fa-lock"/></label>
                                            <input type="password"
                                                   id="repassword"
                                                   className={"sign-form-input form-control " + (this.state.errors.repassword ? 'error' : '')}
                                                   placeholder="Confirm password"
                                                   onChange={e => this.setState({repassword: e.target.value})}/>
                                        </div>
                                        <p className="sign-form-error-text">{this.state.errors.repassword}</p>
                                    </div>
                                    <div className="form-group form-div form-button">
                                        <input type="submit" id="signin" className="form-submit" value="Register"/>
                                    </div>
                                </form>
                                <div className="social-login">
                                    <span className="social-label">Or register with</span>
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
                                <span className="login-label">Have account?</span><a href="/login" className="signin-image-link">Sign In</a>
                            </div>
                        </div>
                        <div className="signin-image col-md-6 col-xs-12">
                            <figure><img src={photo} className="image" alt="sing up"/></figure>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Registration;
