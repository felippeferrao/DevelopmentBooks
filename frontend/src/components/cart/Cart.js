import { Box, Typography } from '@mui/material';

const Cart = ({ cart }) => {
  const { discountSet } = cart;
  const { totalPrice, totalDiscount, bookSets } = discountSet;

  return (
    <Box>
      <Typography variant="h5" gutterBottom>
        Cart
      </Typography>
      {bookSets.map((bookSet, index) => (
        <Box key={index} sx={{ mt: 4 }}>
          <Typography variant="h6" gutterBottom>
            Subset {index + 1}
          </Typography>
          <BookSet books={bookSet} discount={bookSet[0].discount} />
        </Box>
      ))}
      <Box sx={{ mt: 4 }}>
        <Typography variant="subtitle1" gutterBottom>
          Total price: {totalPrice} €
        </Typography>
        <Typography variant="subtitle1" gutterBottom>
          Total discount: {totalDiscount} €
        </Typography>
        <Typography variant="h6" gutterBottom>
          Total to pay: {(totalPrice - totalDiscount).toFixed(2)} €
        </Typography>
      </Box>
    </Box>
  );
};
