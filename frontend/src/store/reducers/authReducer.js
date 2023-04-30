const userString = localStorage.getItem('user');
const user = JSON.parse(userString);
const initialState = {
    isLoggedIn: Boolean(userString) ? user.isLoggedIn : false,
    name: Boolean(userString) ? user.name : '',
    initials: Boolean(userString) ? user.initials : '',
    avatar: Boolean(userString) ? user.avatar : '',
    token: Boolean(userString) ? user.token : ''
};

const authReducer = (state = initialState, action) => {
    switch (action.type) {
        case 'REGISTER_USER':
            let payload = {
                isLoggedIn: false,
                name: action.payload.name,
                initials: '',
                token: ''
            };

            localStorage.setItem('user', JSON.stringify(payload));
            return payload;

        case 'LOGOUT_USER':
            localStorage.removeItem('user');
            return initialState;
        case 'LOGIN_USER':
            //localStorage.setItem('token', action.payload.access_token);
            localStorage.setItem('user', JSON.stringify(action.payload));
            return {
                isLoggedIn: true,
                name: action.payload.name,
                initials: '',
                token: action.payload.token
            };
        default:
            return state;
    }
};

export default authReducer;
