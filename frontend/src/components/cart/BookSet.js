import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography } from '@mui/material';

const BookSet = ({ books, discount }) => {
  return (
    <>
      <Typography variant="h6" gutterBottom>
        Books in this set
      </Typography>
      <TableContainer>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Title</TableCell>
              <TableCell>Author</TableCell>
              <TableCell>Year</TableCell>
              <TableCell>Price</TableCell>
              <TableCell>Quantity</TableCell>
              <TableCell>Discount</TableCell>
              <TableCell>Price with Discount</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {books.map((book) => (
              <TableRow key={book.book.id}>
                <TableCell>{book.book.title}</TableCell>
                <TableCell>{book.book.author}</TableCell>
                <TableCell>{book.book.year}</TableCell>
                <TableCell>{book.book.price} €</TableCell>
                <TableCell>{book.quantity}</TableCell>
                <TableCell>{book.discount * 100}%</TableCell>
                <TableCell>{(book.book.price * book.quantity * (1 - book.discount)).toFixed(2)} €</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <Typography variant="subtitle1" gutterBottom>
        Discount for this set: {discount * 100}%
      </Typography>
    </>
  );
};

