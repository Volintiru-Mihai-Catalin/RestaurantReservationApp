import React, { useState, useEffect } from "react";
import {
    Container,
    Typography,
    List,
    ListItem,
    ListItemText,
    CircularProgress,
    Box,
    Paper,
    styled
} from "@mui/material";

const handleBrowseRestaurants = async () => {
    const response = await fetch("http://localhost:8080/api/v1/restaurants", {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${localStorage.getItem("token")}`
        }
    });

    if (!response.ok) {
        console.log("Browsing Restaurants failed!");
        return [];
    }

    const data = await response.json();
    return data.map((item: any) => ({
        restaurantId: item.restaurantId,
        name: item.name,
        address: item.address,
        phoneNumber: item.phoneNumber,
    }));
}

const BrowseRestaurants = () => {
    const [restaurants, setRestaurants] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchRestaurants = async () => {
            const data = await handleBrowseRestaurants();
            setRestaurants(data);
            setLoading(false);
        };

        fetchRestaurants();
    }, []);

    if (loading) {
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
                <Container>
                    <CircularProgress />
                </Container>
            </Box>
        );
    }
    console.log(restaurants);
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
            <Container>
                <Typography variant="h4" component="h1" gutterBottom textAlign={"center"}>
                    Restaurants
                </Typography>
                <Paper
                    sx={{
                        backgroundColor: 'rgba(255, 255, 255, 0.8)',
                        borderRadius: 2,
                        padding: 4,
                        boxShadow: 3,
                        textAlign: 'center',
                    }}
                >
                    <List>
                        {restaurants.map((item: any) => (
                            <ListItem key={item.restaurantId}>
                                <Box
                                    sx={{
                                        display: 'flex',
                                        flexDirection: 'column',
                                        alignItems: 'center',
                                        width: '100%',
                                    }}
                                >
                                    <ListItemText
                                        primary={item.name}
                                        secondary={
                                            <>
                                                {item.address}
                                                <br />
                                                {item.phoneNumber}
                                            </>
                                        }
                                    />
                                </Box>
                            </ListItem>
                        ))}
                    </List>
                </Paper>
            </Container>
        </Box>
    );
}

export default BrowseRestaurants;
