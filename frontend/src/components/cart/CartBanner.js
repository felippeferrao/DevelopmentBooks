import React from 'react';
import { Box, Typography, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import AnimateButton from 'components/@extended/AnimateButton';

const CartBanner = ({ cart }) => {
    const totalPrice = cart.discountSet?.totalPrice ? cart.discountSet.totalPrice.toFixed(2) : '0.00';
    const totalDiscount = cart.discountSet?.totalDiscount ? cart.discountSet.totalDiscount.toFixed(2) : '0.00';
    const navigate = useNavigate();

    const bookCount = cart?.items?.reduce((acc, item) => {
        return acc + item.quantity;
    }, 0);

    const handleCartClick = () => {
        navigate('/cart');
    };

    return (
        <Box sx={{ bgcolor: 'primary.main', color: 'white', p: 2 }}>
            <Typography variant="body1">
                <Typography variant="subtitle1" gutterBottom>
                    Total price: {totalPrice} € | Number of Books: {bookCount} | Total discount: {totalDiscount} €
                </Typography>
                <Typography variant="subtitle1" gutterBottom>
                    Total to pay: {(totalPrice - totalDiscount).toFixed(2)} €
                </Typography>
            </Typography>
            <AnimateButton>
                <Button variant="contained" color="success" size="small" onClick={handleCartClick}>
                    Checkout
                </Button>
            </AnimateButton>
        </Box>
    );
};

export default CartBanner;
