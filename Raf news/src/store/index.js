import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: '',
    nameU: '',
    admin: 0,
    status: '',
    korisnikData: [],
    kategorijaData:[],
    kategorije:[],
    vestData:[]
  },
  mutations: {
    setToken(state, token) {
      state.token = token;
      localStorage.token = token;
    },
    removeToken(state) {
      state.token = '';
      localStorage.token = '';
      localStorage.name=''
    },
    setName(state,name) {
      state.nameU=name
      localStorage.name=name
    },
    setStatus(state,status) {
      state.status=status
    },
    setAdmin(state,admin) {
      state.admin=admin
    },
    korisniciData(state,data) {
      state.korisnikData = data
    },
      kategorijaData(state,data) {
          state.kategorijaData = data
    },
      kategorije(state,data) {
          state.kategorije = data
      },
      vestData(state,data) {
          state.vestData = data
      }

  },
  actions: {
    register({ commit }, obj) {
      fetch('http://localhost:9000/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(obj)
      }).then( res =>res.json() )
        .then( tkn =>
          { 
            if(tkn.msg)
              alert("Check your email and password field.\nPassword should be at least 4 characters long.")  
            else
            {
            if(tkn.token)
              commit('setToken', tkn.token)
            else
              alert("Acces denied!")       
            }
          }
          );
    },
    login({ commit }, obj) {
      fetch('http://localhost:8081/cms/korisnici/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(obj)
    }).then( res => res.json() )
      .then( tkn => {
        if (tkn.msg) {
          alert(tkn.msg);
          window.location.href='/login'
        } else if(tkn.statk =='0')
        {
          alert("Korisnik nije aktivan!")
        }else{
          console.log(tkn)
          commit('setToken', tkn.jwt)
          commit('setName', tkn.ime)
          commit('setAdmin',tkn.tip)
          commit('setStatus',tkn.statk)
        }
      });
    },
      insertUser({ commit }, obj) {
      fetch('http://localhost:8081/cms/korisnici/insert', {
        method: 'POST',
        headers: {
          'Authorization': `Bearer ${localStorage.token}`,
          'Content-Type': 'application/json' },
        body: JSON.stringify(obj)
      }).then( res => res.json() )
          .then( tkn => {
            if(tkn.msg)
            {
              alert(tkn.msg)
            } else if(tkn.msg2)
            {
              alert(tkn.msg2)
            }
          });
    },
     aktivacija({ commit }, obj) {

      const obj2={ // pravljenje objekta koji ce se ocekivati kao request na beku
        id:obj
      }

      fetch('http://localhost:8081/cms/korisnici/aktivacija', {
        method: 'PUT',
        headers: {
          'Authorization': `Bearer ${localStorage.token}`,
          'Content-Type': 'application/json' },
        body: JSON.stringify(obj2)
      }).then( res => res.json() )
          .then( tkn => {
              if(tkn.msg)
              {
                alert(tkn.msg)
                window.location.reload()
                // ovde radim reload jer nzm kako da uradim refresh tabele / watchujem tabelu ... i tome slicno
              } else if(tkn.msg2) // ako dodje do greske
              {
                alert(tkn.msg2)
              }
          });
    },
    getKorisnikData({ commit }, obj) {

      const obj2={ // pravljenje objekta koji ce se ocekivati kao request na beku
        id:obj
      }

      fetch('http://localhost:8081/cms/korisnici/getKorisnik', {
        method: 'PUT',
        headers: {
          'Authorization': `Bearer ${localStorage.token}`,
          'Content-Type': 'application/json' },
        body: JSON.stringify(obj2)
      }).then( res => res.json() )
          .then( korisnikData => {
            var valuesOnly = Object.values(korisnikData.korisnik);
            commit('korisniciData',valuesOnly);
          });
    },
    updateUser({ commit }, obj) {
      fetch('http://localhost:8081/cms/korisnici/update', {
        method: 'PUT',
        headers: {
          'Authorization': `Bearer ${localStorage.token}`,
          'Content-Type': 'application/json' },
        body: JSON.stringify(obj)
      }).then( res => res.json() )
          .then( tkn => {
            if(tkn.msg)
            {
              alert(tkn.msg)
            } else if(tkn.msg2)
            {
              alert(tkn.msg2)
            }
          });
    },

      insertKategorija({ commit }, obj) {
          fetch('http://localhost:8081/cms/kategorije/insert', {
              method: 'POST',
              headers: {
                  'Authorization': `Bearer ${localStorage.token}`,
                  'Content-Type': 'application/json' },
              body: JSON.stringify(obj)
          }).then( res => res.json() )
              .then( tkn => {
                  if(tkn.msg)
                  {
                      alert(tkn.msg)
                  } else if(tkn.msg2)
                  {
                      alert(tkn.msg2)
                  }
              });
      },
      obrisiKategoriju({ commit }, obj) {
          const obj2={ // pravljenje objekta koji ce se ocekivati kao request na beku
              id:obj
          }
          fetch('http://localhost:8081/cms/kategorije/delete', {
              method: 'POST',
              headers: {
                  'Authorization': `Bearer ${localStorage.token}`,
                  'Content-Type': 'application/json' },
              body: JSON.stringify(obj2)
          }).then( res => res.json() )
              .then(poruka => {
                  alert(poruka)
              });
      },
      getKategorijaData({ commit }, obj) {
          const obj2={ // pravljenje objekta koji ce se ocekivati kao request na beku
              id:obj
          }
          fetch('http://localhost:8081/cms/kategorije/getKategorija', {
              method: 'PUT',
              headers: {
                  'Authorization': `Bearer ${localStorage.token}`,
                  'Content-Type': 'application/json' },
              body: JSON.stringify(obj2)
          }).then( res => res.json() )
              .then( kategorijaData => {
                  var valuesOnly = Object.values(kategorijaData.kategorija);
                  commit('kategorijaData',valuesOnly);
              });
      },
      updateKategorija({ commit }, obj) {
          fetch('http://localhost:8081/cms/kategorije/update', {
              method: 'PUT',
              headers: {
                  'Authorization': `Bearer ${localStorage.token}`,
                  'Content-Type': 'application/json' },
              body: JSON.stringify(obj)
          }).then( res => res.json() )
              .then( tkn => {
                  if(tkn.msg)
                  {
                      alert(tkn.msg)
                  } else if(tkn.msg2)
                  {
                      alert(tkn.msg2)
                  }
              });
      },
      getKategorije({ commit }) {
          fetch('http://localhost:8081/cms/kategorije', {
              method: 'GET',
              headers: {
                  'Authorization': `Bearer ${localStorage.token}`,
                  'Content-Type': 'application/json' }
          }).then( res => res.json() )
              .then( kategorije => {
                  commit('kategorije',kategorije.kategorije)
              });
      },

      insertVest({ commit }, obj) {
          fetch('http://localhost:8081/cms/vesti/insert', {
              method: 'POST',
              headers: {
                  'Authorization': `Bearer ${localStorage.token}`,
                  'Content-Type': 'application/json' },
              body: JSON.stringify(obj)
          }).then( res => res.json() )
              .then( tkn => {
                  if(tkn.msg)
                  {
                      alert(tkn.msg)
                  } else if(tkn.msg2)
                  {
                      alert(tkn.msg2)
                  }
              });
      },
      getVestData({ commit }, obj) {
          const obj2={ // pravljenje objekta koji ce se ocekivati kao request na beku
              id:obj
          }
          fetch('http://localhost:8081/cms/vesti/getVest', {
              method: 'PUT',
              headers: {
                  'Authorization': `Bearer ${localStorage.token}`,
                  'Content-Type': 'application/json' },
              body: JSON.stringify(obj2)
          }).then( res => res.json() )
              .then( vestData => {
                  var valuesOnly = Object.values(vestData.vest);
                  commit('vestData',valuesOnly);
              });
      },
      updateVest({ commit }, obj) {
          fetch('http://localhost:8081/cms/vesti/update', {
              method: 'PUT',
              headers: {
                  'Authorization': `Bearer ${localStorage.token}`,
                  'Content-Type': 'application/json' },
              body: JSON.stringify(obj)
          }).then( res => res.json() )
              .then( tkn => {
                  if(tkn.msg)
                  {
                      alert(tkn.msg)
                  } else if(tkn.msg2)
                  {
                      alert(tkn.msg2)
                  }
              });
      },
      obrisiVest({ commit }, obj) {
          const obj2={ // pravljenje objekta koji ce se ocekivati kao request na beku
              id:obj
          }

          console.log(obj2.id)

          fetch('http://localhost:8081/cms/vesti/delete/'+obj2.id, {
              method: 'DELETE',
              headers: {
                  'Authorization': `Bearer ${localStorage.token}`,
                  'Content-Type': 'application/json' }
          }).then( res => res.json() )
              .then(poruka => {
                  alert(poruka)
              });
      },







  }

})
