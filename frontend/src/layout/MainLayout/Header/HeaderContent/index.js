// material-ui
import { Box, IconButton, Link, useMediaQuery } from '@mui/material';
import { GithubOutlined } from '@ant-design/icons';

// project import
import MobileSection from './MobileSection';

// ==============================|| HEADER - CONTENT ||============================== //

const HeaderContent = () => {
    const matchesXs = useMediaQuery((theme) => theme.breakpoints.down('md'));

    return (
        <>
            {matchesXs && <Box sx={{ width: '100%', ml: 1 }} />}

            <IconButton
                component={Link}
                href="https://github.com/felippeferrao/DevelopmentBooks"
                target="_blank"
                disableRipple
                color="secondary"
                title="Documentation Page"
                sx={{ color: 'text.primary', bgcolor: 'grey.100' }}
            >
                <GithubOutlined />
            </IconButton>
            {matchesXs && <MobileSection />}
        </>
    );
};

export default HeaderContent;
