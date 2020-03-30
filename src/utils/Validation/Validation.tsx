class Validation {
    checkEmail = (email: string) : boolean => {
        // eslint-disable-next-line
        let validEmailRegex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return validEmailRegex.test(email);
    };

    isEmpty = (str: string) : boolean => {
        return str.trim() === '';
    };

    checkMaxLength = (str: string, max: number) : boolean => {
        return str.length <= max;
    };

    checkMinLength = (str: string, min: number) : boolean => {
        return str.length >= min;
    }
}

export default Validation;