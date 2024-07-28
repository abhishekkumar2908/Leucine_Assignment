import axios from 'axios';

const apiBaseUrl = 'http://localhost:8080/api';

const getRequestConfig = async (config) => {
  const token  = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
};

const getRequestError = (error) => {
  console.log('Request Error', error);
  return Promise.reject(error);
};

export const getResponseError = (error) => {
  if (error.response) {
    // Request made and server responded
    switch (error.response.status) {
      case 400: {
        console.log('Bad Request');
        break;
      }
      case 401: {
        delete Axios.defaults.headers.common.Authorization;
        break;
      }
      case 403: {
        console.log('Forbidden');
        break;
      }
      case 404: {
        console.log('Not Found');
        break;
      }
      case 500: {
        console.log('Internal Server Error');
        break;
      }
      case 502: {
        console.log('Bad Gateway');
        break;
      }
      case 503: {
        console.log('Service Unavailable');
        break;
      }
      case 504: {
        console.log('Gateway Timeout');
        break;
      }
      default: {
        break;
      }
    }
  return Promise.reject(new Error(error?.response?.data?? 'Something went wrong!'));
};
};
const getResponseConfig = (response)  => {
  return response.data;
};
export const Axios = axios.create({
  baseURL: apiBaseUrl
});
Axios.interceptors.request.use(getRequestConfig, getRequestError);
Axios.interceptors.response.use(getResponseConfig, getResponseError);