// assets
import { HomeOutlined, ShoppingCartOutlined } from '@ant-design/icons';

// icons
const icons = {
    HomeOutlined,
    ShoppingCartOutlined
};

// ==============================|| MENU ITEMS - DASHBOARD ||============================== //

const home = {
    id: 'group-dashboard',
    title: 'Home',
    type: 'group',
    children: [
        {
            id: 'dashboard',
            title: 'Home',
            type: 'item',
            url: '/',
            icon: icons.HomeOutlined,
            breadcrumbs: false
        },
        {
            id: 'cart',
            title: 'My Cart',
            type: 'item',
            url: '/cart',
            icon: icons.ShoppingCartOutlined,
            breadcrumbs: false
        }
    ]
};

export default home;
