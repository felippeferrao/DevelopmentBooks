import axios from 'axios';
import config from '../config/config_api';

export class LoginService {
    apiURL = config.apiLoginUrl;

    constructor() {
        this.config = {
            headers: {
                'Content-Type': 'application/json'
            }
        };
    }

    async login(credentials) {
        return axios
            .post(`${this.apiURL}/auth`, JSON.stringify(credentials), this.config)
            .then((res) => res.data)
            .catch((err) => {
                if (err.response && err.response.status === 401) {
                    throw err.response.data;
                } else {
                    throw err;
                }
            });
    }

    async register(register) {
        return axios
            .post(`${this.apiURL}/register`, JSON.stringify(register), this.config)
            .then((res) => res.data)
            .catch((err) => {
                if (err.response && err.response.status === 400) {
                    throw err.response.data;
                } else {
                    throw err;
                }
            });
    }

    async changePassword(data) {
        return axios
            .post(`${this.apiURL}/changePassword`, JSON.stringify(data), this.config)
            .then((res) => res.data)
            .catch((err) => {
                if (err.response && err.response.data) {
                    throw err.response.data;
                } else {
                    throw err;
                }
            });
    }

    async forgotPass(data) {
        return axios
            .post(`${this.apiURL}/forgotpassword`, JSON.stringify(data), this.config)
            .then((res) => res.data)
            .catch((err) => {
                if (err.response && err.response.data) {
                    throw err.response.data;
                } else {
                    throw err;
                }
            });
    }

    async confirmEmail(code) {
        return axios
            .get(`${this.apiURL}/confirm-email/${code}`, this.config)
            .then((res) => res.data)
            .catch((err) => {
                if (err.response && err.response.data) {
                    throw err.response.data;
                } else {
                    throw err;
                }
            });
    }

    setToken(token) {
        this.config.headers.Authorization = `Bearer ${token}`;
    }
}
