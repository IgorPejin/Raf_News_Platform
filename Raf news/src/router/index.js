import Vue from 'vue';
import VueRouter from 'vue-router';

import Home from '@/views/Home.vue';
import Register from '@/views/Register.vue';
import Kategorije from "@/views/Kategorije";
import Login from "@/views/Login";
import Vesti from "@/views/Vesti";
import Korisnici from "@/views/Korisnici";

import NoviKorisnik from "@/views/NoviKorisnik";
import IzmenaKorisnika from "@/views/IzmenaKorisnika";

import NovaKategorija from "@/views/NovaKategorija";
import IzmenaKategorije from "@/views/IzmenaKategorije";

import NovaVest from "@/views/NovaVest";
import IzmenaVesti from "@/views/IzmenaVesti";
import VestiKategorija from "@/views/VestiKategorija";

import Search from "@/views/Search"

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/Kategorije',
    name: 'Kategorije',
    component: Kategorije
  },
  {
    path: '/Korisnici',
    name: 'Korisnici',
    component: Korisnici
  },
  {
    path: '/Vesti',
    name: 'Vesti',
    component: Vesti
  },
  {
    path: '/NoviKorisnik',
    name: 'Novi Korisnik',
    component: NoviKorisnik
  },
  {
    path: '/IzmenaKorisnika/:id',
    name: 'Izmena Korisnika',
    component: IzmenaKorisnika
  },
  {
    path: '/NovaKategorija',
    name: 'Nova Kategorija',
    component: NovaKategorija
  },
  {
    path: '/IzmenaKategorije/:id',
    name: 'Izmena Kategorije',
    component: IzmenaKategorije
  },
  {
    path: '/NovaVest',
    name: 'NovaVest',
    component: NovaVest
  },
  {
    path: '/IzmenaVesti/:id',
    name: 'IzmenaVesti',
    component: IzmenaVesti
  },
  {
    path: '/Vesti/:kategorija',
    name: 'VestiKategorija',
    component: VestiKategorija
  },
  {
    path: '/search',
    name: 'Search',
    component: Search
  }

];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

export default router;
