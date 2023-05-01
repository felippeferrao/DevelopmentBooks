import { lazy } from 'react';

// project import
import Loadable from 'components/Loadable';
import MainLayout from 'layout/MainLayout';

// render - dashboard
const DashboardDefault = Loadable(lazy(() => import('pages/dashboard')));
const Home = Loadable(lazy(() => import('pages/home')));
const Cart = Loadable(lazy(() => import('pages/cart')));

// ==============================|| MAIN ROUTING ||============================== //

const MainRoutes = {
    path: '/',
    element: <MainLayout />,
    children: [
        {
            path: '/',
            element: <Home />
        },
        {
            path: 'home',
            element: <Home />
        },
        {
            path: 'cart',
            element: <Cart />
        }
    ]
};

export default MainRoutes;
