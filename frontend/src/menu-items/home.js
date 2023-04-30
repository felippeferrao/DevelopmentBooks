// assets
import { DashboardOutlined } from '@ant-design/icons';

// icons
const icons = {
    DashboardOutlined
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
            url: '/dashboard/default',
            icon: icons.DashboardOutlined,
            breadcrumbs: false
        }
    ]
};

export default home;
