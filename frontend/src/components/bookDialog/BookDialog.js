import React, { useState } from 'react';
import { makeStyles } from '@mui/styles';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';

const useStyles = makeStyles({
    dialogContent: {
        display: 'flex',
        alignItems: 'center',
        gap: '16px',
        padding: '16px'
    },
    bookImage: {
        width: '100px',
        height: '150px',
        objectFit: 'cover'
    }
});

const BookDialog = ({ book, open, onClose, onAddToCart }) => {
    const classes = useStyles();
    const [quantity, setQuantity] = useState(1);

    const handleQuantityChange = (event) => {
        setQuantity(event.target.value);
    };

    const handleAddToCart = () => {
        onAddToCart(book, quantity);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>{book.title}</DialogTitle>
            <DialogContent className={classes.dialogContent}>
                <img src={book.imageUrl} alt={book.title} className={classes.bookImage} />
                <div>
                    <Typography variant="subtitle1" gutterBottom>
                        {book.author}
                    </Typography>
                    <Typography variant="body1" gutterBottom>
                        {book.description}
                    </Typography>
                    <Typography variant="h6" gutterBottom>
                        {book.price} €
                    </Typography>
                    <TextField label="Quantity" type="number" value={quantity} onChange={handleQuantityChange} inputProps={{ min: 1 }} />
                </div>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleAddToCart} variant="contained" color="primary">
                    Add to Cart
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default BookDialog;
