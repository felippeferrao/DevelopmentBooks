import PropTypes from 'prop-types';

// material-ui
import { useTheme } from '@mui/material/styles';
import { Stack, Chip } from '@mui/material';

// project import
import DrawerHeaderStyled from './DrawerHeaderStyled';
import Logo from 'components/Logo';

// ==============================|| DRAWER HEADER ||============================== //

const DrawerHeader = ({ open }) => {
    const theme = useTheme();

    return (
        <>
            <DrawerHeaderStyled theme={theme} open={open}>
                <Stack direction="row" spacing={1} alignItems="center">
                    <Logo />
                </Stack>
            </DrawerHeaderStyled>

            <Stack alignItems="center" spacing={2.5}>
                <Chip
                    label={process.env.REACT_APP_VERSION}
                    size="small"
                    sx={{ height: 16, width: 90, '& .MuiChip-label': { fontSize: '0.625rem', py: 0.25 } }}
                    component="a"
                    href="https://github.com/felippeferrao"
                    target="_blank"
                    clickable
                />
            </Stack>
        </>
    );
};

DrawerHeader.propTypes = {
    open: PropTypes.bool
};

export default DrawerHeader;
