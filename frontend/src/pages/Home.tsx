import React from "react";
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import { Link } from "react-router-dom";
import { Box } from "@mui/material";

const Home = () => (
    <Box
        sx={{
            backgroundImage: `url(https://images.unsplash.com/photo-1620878439129-177b2d18864c?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cmVzdGF1cmFudCUyMGJhY2tncm91bmR8ZW58MHx8MHx8fDA%3D)`,
            backgroundSize: 'cover',
            backgroundPosition: 'center',
            minHeight: '100vh',
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            justifyContent: 'center',
            padding: 2,
        }}
    >
        <AppBar position="static" sx={{ backgroundColor: 'rgba(0, 0, 0, 0.7)' }}>
            <Toolbar>
                <Typography variant="h6" sx={{ flexGrow: 1 }}>
                    Restaurant24
                </Typography>
                <Box sx={{ display: 'flex' }}>
                    <Button color="inherit" component={Link} to="/feedback">
                        Give us some feedback
                    </Button>
                    <Button color="inherit" component={Link} to="/reservations">
                        Book a table
                    </Button>
                    <Button color="inherit" component={Link} to="/browse-restaurants">
                        Browse restaurants
                    </Button>
                    <Button color="inherit" component={Link} to="/contact">
                        Contact us
                    </Button>
                    <Button color="inherit" component={Link} to="/about">
                        About Us
                    </Button>

                </Box>
            </Toolbar>
        </AppBar>
    </Box>
);

export default Home;