export default class Auth {
    static isLoggedIn:boolean = (localStorage.getItem('apiKey') !== null && localStorage.getItem('userId') !== null);

    static signIn = (apiKey: string, userId: string, firstName: string) => {
        localStorage.setItem('apiKey', apiKey);
        localStorage.setItem('userFirstName', firstName);
        localStorage.setItem('userId', userId);
        window.location.href = "/user";
    };

    static logOut = () => {
        localStorage.removeItem('apiKey');
        localStorage.removeItem('userFirstName');
        localStorage.removeItem('userId');
        window.location.href = "/login";
    }
}