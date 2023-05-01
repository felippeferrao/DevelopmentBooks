import { Box, Typography, Divider, Grid, Button } from '@mui/material';
import { makeStyles } from '@mui/styles';
import { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import { CartService } from 'services/CartService';

import MainCard from 'components/MainCard';
import AnimateButton from 'components/@extended/AnimateButton';

const useStyles = makeStyles({
    root: {
        flexGrow: 1
    },
    card: {
        maxWidth: 345
    },
    media: {
        height: 0,
        paddingTop: '56.25%', // 16:9
        backgroundSize: 'contain',
        backgroundPosition: 'center'
    }
});

const CartPage = () => {
    const [error, setError] = useState(false);
    const cartService = new CartService();
    let authObj = useSelector((state) => state.auth);
    cartService.setToken(authObj.token);
    const [cart, setCart] = useState({});

    useEffect(() => {
        cartService
            .getMyCart()
            .then((reg) => {
                if (!reg) {
                    setError({ submit: 'API Error' });
                }
                setCart(reg);
            })
            .catch((err) => {
                setError({ submit: err.message });
            });
    }, []);

    const onClearCart = () => {
        cartService
            .clearCart()
            .then((reg) => {
                if (!reg) {
                    setError({ submit: 'API Error' });
                }

                setCart(reg);
            })
            .catch((err) => {
                setError({ submit: err.message });
            });
    };

    const totalPrice = cart.discountSet?.totalPrice ? cart.discountSet.totalPrice.toFixed(2) : '0.00';
    const totalDiscount = cart.discountSet?.totalDiscount ? cart.discountSet.totalDiscount.toFixed(2) : '0.00';
    const bookSets = cart.discountSet?.bookSets ? cart.discountSet.bookSets : [];
    const bookCount = cart?.items?.reduce((acc, item) => {
        return acc + item.quantity;
    }, 0);

    return (
        <MainCard title="BookStore - IT Gratest Books! - Cart">
            <Box sx={{ bgcolor: 'primary.main', color: 'white', padding: 2 }}>
                <Typography variant="h6" align="center">
                    Your Shopping Cart
                </Typography>
            </Box>
            <br />
            {bookSets.map((bookSet, index) => (
                <Box key={index} sx={{ padding: 2 }}>
                    <Box sx={{ bgcolor: 'primary.main', color: 'white', padding: 2 }}>
                        <Typography variant="body1" sx={{ fontWeight: 'bold' }}>
                            Books Set {index + 1}
                        </Typography>
                    </Box>
                    <Typography variant="body1" component="div">
                        {bookSet.map((cartItem, index) => (
                            <Box key={index}>
                                <Grid container>
                                    <Grid item xs={12} sm={9} md={10}>
                                        <Typography variant="body1">
                                            {cartItem.book.title} ({cartItem.quantity}x)
                                        </Typography>
                                        <Typography variant="subtitle2" color="text.secondary">
                                            {cartItem.book.author}, {cartItem.book.year}
                                        </Typography>
                                        <Typography variant="subtitle2" color="text.secondary">
                                            Original Price: {cartItem.book.price} €
                                        </Typography>
                                        <Typography variant="subtitle2" color="text.secondary">
                                            Discount: {cartItem.discount * 100}% ({cartItem.book.price - cartItem.discount} €)
                                        </Typography>
                                    </Grid>
                                    <Grid item xs={12} sm={3} md={2}>
                                        <Typography variant="body1" sx={{ fontWeight: 'bold' }}>
                                            {cartItem.book.price - cartItem.discount} €
                                        </Typography>
                                    </Grid>
                                </Grid>
                                {index < bookSet.length - 1 && <Divider sx={{ marginTop: 1, marginBottom: 1 }} />}
                            </Box>
                        ))}
                    </Typography>
                </Box>
            ))}
            <Divider sx={{ marginTop: 2, marginBottom: 2 }} />
            <Typography variant="body1" sx={{ fontWeight: 'bold' }}>
                Total Price: {totalPrice} €
            </Typography>
            <Typography variant="body1" sx={{ fontWeight: 'bold' }}>
                Total Discount: {totalDiscount} €
            </Typography>
            <br></br>
            <AnimateButton>
                <Button variant="contained" color="success" size="small" onClick={onClearCart}>
                    Clear Cart
                </Button>
            </AnimateButton>
        </MainCard>
    );
};

export default CartPage;
