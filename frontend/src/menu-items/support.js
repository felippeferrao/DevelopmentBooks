// assets
import { GithubOutlined } from '@ant-design/icons';

// icons
const icons = {
    GithubOutlined
};

// ==============================|| MENU ITEMS - SAMPLE PAGE & DOCUMENTATION ||============================== //

const support = {
    id: 'support',
    title: 'Support',
    type: 'group',
    children: [
        {
            id: 'documentation',
            title: 'Documentation',
            type: 'item',
            url: 'https://github.com/felippeferrao/DevelopmentBooks',
            icon: icons.GithubOutlined,
            external: true,
            target: true
        }
    ]
};

export default support;
