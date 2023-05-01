import axios from 'axios';
import config from '../config/config_api';

export class CartService {
    apiURL = config.apiLoginUrl;

    constructor() {
        this.config = {
            headers: {
                'Content-Type': 'application/json'
            }
        };
    }

    async getMyCart() {
        return axios
            .get(`${this.apiURL}/api/v1/cart/myCart`, this.config)
            .then((res) => res.data)
            .catch((err) => {
                if (err.response && err.response.data) {
                    throw err.response.data;
                } else {
                    throw err;
                }
            });
    }

    async addItemToCart(item) {
        return axios
            .post(`${this.apiURL}/api/v1/cart/addItem`, JSON.stringify(item), this.config)
            .then((res) => res.data)
            .catch((err) => {
                if (err.response && err.response.data) {
                    throw err.response.data;
                } else {
                    throw err;
                }
            });
    }

    async removeItemFromCart(item) {
        return axios
            .post(`${this.apiURL}/api/v1/cart/removeItem`, JSON.stringify(item), this.config)
            .then((res) => res.data)
            .catch((err) => {
                if (err.response && err.response.data) {
                    throw err.response.data;
                } else {
                    throw err;
                }
            });
    }

    async clearCart() {
        return axios
            .get(`${this.apiURL}/api/v1/cart/clearCart`, this.config)
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
