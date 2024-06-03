import React from "react";
import { Box, Container, Paper, Typography, Grid } from '@mui/material';

const ContactUs = () => {

    return (
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
            <Container maxWidth="md">
                <Paper
                    sx={{
                        padding: 4,
                        backgroundColor: 'rgba(255, 255, 255, 0.8)',
                        borderRadius: 2,
                        boxShadow: 3,
                    }}
                >
                    <Typography variant="h4" gutterBottom>
                        Contact Us
                    </Typography>
                    <Grid container spacing={4}>
                        <Grid item xs={12} md={6}>
                            <Typography variant="h6">Restaurant24 SRL</Typography>
                            <Typography>Splaiul Independentei 290</Typography>
                            <Typography>Bucharest, Romania, 060029</Typography>
                            <Typography>Phone: (123) 456-7890</Typography>
                            <Typography>Email: restaurant24@gmail.com</Typography>
                        </Grid>
                    </Grid>
                </Paper>
            </Container>
        </Box>
    );
};

export default ContactUs;
