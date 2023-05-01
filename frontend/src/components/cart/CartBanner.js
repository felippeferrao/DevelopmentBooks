import React from 'react';
import { Box, Typography, IconButton, Link } from '@mui/material';
import { ShoppingCartOutlined } from '@ant-design/icons';

const CartBanner = ({ cart }) => {
    const totalPrice = cart.discountSet?.totalPrice ? cart.discountSet.totalPrice.toFixed(2) : '0.00';
    const totalDiscount = cart.discountSet?.totalDiscount ? cart.discountSet.totalDiscount.toFixed(2) : '0.00';

    const bookCount = cart?.items?.reduce((acc, item) => {
        return acc + item.quantity;
    }, 0);

    return (
        <Box sx={{ bgcolor: 'primary.main', color: 'white', p: 2 }}>
            <Typography variant="body1">
                <Typography variant="subtitle1" gutterBottom>
                    Total price: {totalPrice} €
                </Typography>
                <Typography variant="subtitle1" gutterBottom>
                    Number of Books: {bookCount}
                </Typography>
                <Typography variant="subtitle1" gutterBottom>
                    Total discount: {totalDiscount} €
                </Typography>
                <Typography variant="subtitle1" gutterBottom>
                    Total to pay: {(totalPrice - totalDiscount).toFixed(2)} €
                </Typography>
            </Typography>
            <IconButton component={Link} to="/cart" color="inherit" aria-label="Checkout">
                <ShoppingCartOutlined fontSize="large" />
                <Typography variant="subtitle1" component="span" sx={{ pl: 1 }}>
                    Checkout
                </Typography>
            </IconButton>
        </Box>
    );
};

export default CartBanner;
