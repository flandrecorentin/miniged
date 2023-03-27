import { createRouter, createWebHistory} from 'vue-router'
import About from '../views/About'
import Miniged from '../views/Miniged'
import Document from '../views/Document'
import Type from '../views/Type'
import Schema from '../views/Schema'

const routes = [
    {
        path: '/about',
        name: 'About',
        component: About,
    },
    {
        path: '/',
        name: 'Miniged',
        component: Miniged,
    },
    {
        path: '/document',
        name: 'Document',
        component: Document,
    },
    {
        path: '/type',
        name: 'Type',
        component: Type,
    },
    {
        path: '/schema',
        name: 'Schema',
        component: Schema,
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
})

export default router