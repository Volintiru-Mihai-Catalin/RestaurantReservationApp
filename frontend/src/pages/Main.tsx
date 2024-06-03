import React from "react";
import { Box, Typography, Button, Container, Paper } from '@mui/material';

const Main = () => (
    <Box
        sx={{
            backgroundImage: `url(https://images.unsplash.com/photo-1620878439129-177b2d18864c?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cmVzdGF1cmFudCUyMGJhY2tncm91bmR8ZW58MHx8MHx8fDA%3D)`,
            backgroundSize: 'cover',
            backgroundPosition: 'center',
            minHeight: '100vh',
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            padding: 2,
        }}
    >
        <Container maxWidth="sm">
            <Paper
                sx={{
                    padding: 4,
                    backgroundColor: 'rgba(255, 255, 255, 0.8)',
                    borderRadius: 2,
                    boxShadow: 3,
                }}
            >
                <Typography variant="h5" gutterBottom>
                    You are not logged in, please log into your account or create one.
                </Typography>
                <Box sx={{ display: 'flex', justifyContent: 'space-between', marginTop: 2 }}>
                    <Button
                        sx={{
                            background: 'black'
                        }}
                        variant="contained"
                        color="primary"
                        onClick={(e) => {
                            e.preventDefault();
                            window.location.assign("http://localhost:3000/login");
                        }}
                    >
                        Login
                    </Button>
                    <Button
                        sx={{
                            background: 'black'
                        }}
                        variant="contained"
                        color="primary"
                        onClick={(e) => {
                            e.preventDefault();
                            window.location.assign("http://localhost:3000/register");
                        }}
                    >
                        Register
                    </Button>
                </Box>
            </Paper>
        </Container>
    </Box>
);

export default Main;