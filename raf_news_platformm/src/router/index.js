import Vue from 'vue';
import VueRouter from 'vue-router';

import Home from "@/views/Home";
import HomePage from "@/views/HomePage";
import VestiKategorija from "@/views/VestiKategorija";
import Search from "@/views/Search";
import Vest from "@/views/Vest";

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/HomePage',
        name: 'HomePage',
        component: HomePage
    },
    {
        path: '/:kategorija',
        name: 'VestiKategorija',
        component: VestiKategorija
    },
    {
        path: '/search',
        name: 'Search',
        component: Search
    }
    ,
    {
        path: '/vest/:id',
        name: 'Vest',
        component: Vest
    }

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
});

export default router;