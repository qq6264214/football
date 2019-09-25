import axios from 'axios'

let base = '/api'

export const posrs = params => {
    return axios.post(`${base}/login`, params).then(res => res.data)
}

export const getPredictions = params => {
    return axios.get(`${base}/forecast/list`, { params: params })
}

export const getNeedAnaNum = () => {
    return axios.get(`${base}/forecast/needAnaNum`, {  })
}


export const upload1DataApi = `${base}/file/upload1Data`

export const upload3DataApi = `${base}/file/upload3Data`