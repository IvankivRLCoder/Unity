export default class Auth {

    static isLoggedIn:boolean = (localStorage.getItem('apiKey') !== null && localStorage.getItem('userId') !== null);
    static loggedUserId:any = localStorage.getItem('userId');
    static loggedApiKey:any = localStorage.getItem('apiKey');
    static remember:boolean = localStorage.getItem('remember') === 'true';

    static signIn = (apiKey: string, userId: string, firstName: string, remember: string) => {
        localStorage.setItem('apiKey', apiKey);
        localStorage.setItem('userFirstName', firstName);
        localStorage.setItem('userId', userId);
        localStorage.setItem('remember', remember);
        window.location.href = "/user/" + userId;
    };

    static logOut = () => {
        localStorage.removeItem('apiKey');
        localStorage.removeItem('userFirstName');
        localStorage.removeItem('userId');
        localStorage.removeItem('remember');
        window.location.href = "/login";
    }
}