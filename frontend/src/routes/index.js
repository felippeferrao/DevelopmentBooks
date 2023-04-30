import { useRoutes } from 'react-router-dom';
import { useSelector } from 'react-redux';

// project import
import LoginRoutes from './LoginRoutes';
import MainRoutes from './MainRoutes';

// ==============================|| ROUTING RENDER ||============================== //

export default function ThemeRoutes() {
    const isAuthenticated = useSelector((state) => state.auth.isLoggedIn);

    let routes = [LoginRoutes];
    if (isAuthenticated) {
        routes = [MainRoutes];
    }

    const routing = useRoutes(routes);
    return routing;
}
