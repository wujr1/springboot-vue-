import { createRouter, createWebHistory } from 'vue-router'
import Login from '../components/Login.vue'
import Student from '../components/Student.vue'
import Home from '../components/Home.vue'
const routes=[{
  path:'/login',
  component:Login
},
{
  path:'/home',
  name:'home',
  component:Home
},
{
  path:'/student',
  name:'student',
  component:Student
},{
  path:'/',
  redirect:'/login'
}]
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})


export default router
