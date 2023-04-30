import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { BookService } from 'services/BookService';
import { Grid, Typography, Button, Card, CardMedia, CardContent, CardActions, Box } from '@mui/material';
import { makeStyles } from '@mui/styles';
import BookDialog from 'components/bookDialog/BookDialog';

const useStyles = makeStyles({
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

// project import
import MainCard from 'components/MainCard';

// ==============================|| SAMPLE PAGE ||============================== //

const Home = () => {
    const classes = useStyles();
    const bookService = new BookService();
    let authObj = useSelector((state) => state.auth);
    bookService.setToken(authObj.token);
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [books, setBooks] = useState([]);
    const [error, setError] = useState(false);
    const [showPopup, setShowPopup] = useState(false);
    const [selectedBook, setSelectedBook] = useState(null);

    const handleAddToCartClick = (book) => {
        setSelectedBook(book);
        setShowPopup(true);
    };

    useEffect(() => {
        bookService
            .getAllBooks()
            .then((reg) => {
                if (!reg) {
                    setError({ submit: 'API Error' });
                }
                setBooks(reg);
            })
            .catch((err) => {
                setError({ submit: err.message });
            });
    }, []);

    const addToCart = (bookId, quantity) => {
        // Implementar aqui a lógica para adicionar o livro ao carrinho
        console.log(`Adicionando ${quantity} cópias do livro ${bookId} ao carrinho.`);
    };

    return (
        <MainCard title="BookStore - IT Gratest Books!">
            <Box sx={{ bgcolor: 'primary.main', color: 'white', padding: 2 }}>
                <Typography variant="h6" align="center">
                    Buy more, save more!
                </Typography>
                <Typography variant="body1" align="left">
                    <ul>
                        <li>If you buy two different books from the series, you get a 5% discount on those two books.</li>
                        <li>If you buy 3 different books, you get a 10% discount.</li>
                        <li>With 4 different books, you get a 20% discount.</li>
                        <li>If you buy all 5, you get a huge 25% discount!</li>
                    </ul>
                </Typography>
            </Box>
            <br></br>
            <Grid container spacing={2}>
                {books.map((book) => (
                    <Grid item xs={12} md={6} lg={4} key={book.id}>
                        <Card className={classes.card}>
                            <CardMedia className={classes.media} image={book.imageUrl} title={book.title} />
                            <CardContent>
                                <Typography gutterBottom variant="h5" component="div">
                                    {book.title}
                                </Typography>
                                <Typography variant="body2" color="text.secondary">
                                    {book.author}, {book.year}
                                </Typography>
                                <Typography variant="body2" color="text.secondary">
                                    {book.price} €
                                </Typography>
                            </CardContent>
                            <CardActions>
                                <Button size="small" onClick={() => handleAddToCartClick(book)}>
                                    Add to Cart
                                </Button>
                            </CardActions>
                        </Card>
                    </Grid>
                ))}
            </Grid>
            {showPopup && (
                <BookDialog
                    open={showPopup}
                    book={selectedBook}
                    onClose={() => setShowPopup(false)}
                    onAddToCart={(book, quantity) => addToCart(book, quantity)}
                />
            )}
        </MainCard>
    );
};

export default Home;
