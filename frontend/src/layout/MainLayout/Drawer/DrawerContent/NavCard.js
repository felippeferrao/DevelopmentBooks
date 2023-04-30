import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';

// material-ui
import { Button, CardMedia, Stack, Typography } from '@mui/material';

// assets
import AnimateButton from 'components/@extended/AnimateButton';

// project import
import MainCard from 'components/MainCard';

const NavCard = () => {
    const auth = useSelector((state) => state.auth);
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const handleLogout = () => {
        // Faz o dispatch da ação para fazer logout
        dispatch({ type: 'LOGOUT_USER' });

        // Redireciona o usuário para a página de login
        navigate('/');
    };

    return (
        <MainCard sx={{ bgcolor: 'grey.50', m: 3 }}>
            <Stack alignItems="center" spacing={2.5}>
                <CardMedia component="img" image={auth.avatar} sx={{ width: 112 }} />
                <Stack alignItems="center">
                    <Typography variant="h5">{auth.name}</Typography>
                    <Typography variant="h6" color="secondary">
                        {auth.iniciais}
                    </Typography>
                </Stack>
                <AnimateButton>
                    <Button variant="contained" color="success" size="small" onClick={handleLogout}>
                        Logout
                    </Button>
                </AnimateButton>
            </Stack>
        </MainCard>
    );
};

export default NavCard;
