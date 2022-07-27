import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        kategorije:[],
        vestTags:'',
        comments:[],
        vestData:[]
    },
    mutations: {
        kategorije(state,data) {
            state.kategorije = data
        },
        tags(state,data)
        {
            state.vestTags=data;
        },
        comments(state,data)
        {
            state.comments=data;
        },
        vestData(state,data)
        {
            state.vestData=data
        }

    },
    actions: {
        getKategorije({ commit }) {
            fetch('http://localhost:8081/cms/kategorije', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(res => res.json())
                .then(kategorije => {
                    commit('kategorije', kategorije.kategorije)
                });
        },

        // eslint-disable-next-line no-unused-vars
        getVestData({commit},obj)
        {
            fetch('http://localhost:8081/cms/vesti/'+obj, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(res => res.json())
                .then(news => {
                    commit('vestData',Object.values(news))
                });
        },

        getVestTags({commit},obj)
        {
            fetch('http://localhost:8081/cms/vesti/tags/'+obj, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(res => res.json())
                .then(news => {
                    if(news.msg) {
                        commit('tags', '')
                    }else{
                        commit('tags', news.tag)
                    }if(news.msg2)
                    {
                        commit('comments',0)
                    }else{
                        commit('comments',news.komentari)
                    }
                });
        },
        // eslint-disable-next-line no-unused-vars
        insertComment({commit},form) {
            console.log(form)
            fetch('http://localhost:8081/cms/vesti/insertComment', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(form)
            }).then(res => res.json())
                .then(poruka => {
                    if(poruka.msg)
                    alert(poruka.msg)
                });
        },
    }

})