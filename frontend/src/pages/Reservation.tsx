import React, {useState} from "react";
import { TextField, Button, Box, Typography, Container, Paper } from '@mui/material';
import {ReservationInterface} from "../interfaces/ReservationInterface";
import {ReservationDetailsInterface} from "../interfaces/ReservationDetailsInterface";

const handleReservation = async (reservationDetails: ReservationInterface, setMessage: React.Dispatch<React.SetStateAction<string>>) => {
    const response = await fetch("http://localhost:8080/api/v1/reservation", {
        mode: "cors",
        method: "POST",
        body: JSON.stringify({
            reservationDate: reservationDetails.reservationDate,
            restaurantName: reservationDetails.restaurantName,
            email: localStorage.getItem("email")
        }),
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${localStorage.getItem("token")}`
        }
    });

    if (!response.ok) {
        console.log("Reservation details not valid!");
        setMessage("Reservation details not valid!");
        return;
    }

    setMessage("Reservation confirmed!");

    return;
}

const handleSeeReservations = async (setReservations: React.Dispatch<React.SetStateAction<ReservationDetailsInterface[]>>) => {
    const response = await fetch("http://localhost:8080/api/v1/reservations", {
        mode: "cors",
        method: "GET",
        headers: {
            "Authorization": `Bearer ${localStorage.getItem("token")}`
        }
    });

    if (!response.ok) {
        console.log("GET Reservations failed!");
        return;
    }

    const data = await response.json();
    const reservations: ReservationDetailsInterface[] = data.map((item: any) => ({
        reservationDate: item.reservationDate,
        endTime: item.endTime,
        restaurantName: item.restaurantName,
        tableId: item.tableId,
    }));

    setReservations(reservations);
}

const Reservation = () => {

    const [message, setMessage] = useState("Please enter reservation detalis!");
    const [reservation, setReservation] = useState<ReservationInterface>({
        reservationDate: "",
        restaurantName: "",
        email: ""
    })
    const [reservations, setReservations] = useState<ReservationDetailsInterface[]>([]);

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
            <Container maxWidth="sm">
                <Paper
                    sx={{
                        padding: 4,
                        backgroundColor: 'rgba(255, 255, 255, 0.8)',
                        borderRadius: 2,
                        boxShadow: 3,
                    }}
                >
                    <Typography variant="h4" gutterBottom>
                        {message}
                    </Typography>
                    <form onSubmit={(e) => {
                        e.preventDefault();
                        handleReservation(reservation, setMessage);
                    }}>
                        <TextField
                            fullWidth
                            margin="normal"
                            variant="outlined"
                            label="Reservation Date"
                            onChange={(e) => setReservation((previous) => ({ ...previous, reservationDate: e.target.value }))}
                        />
                        <TextField
                            fullWidth
                            margin="normal"
                            variant="outlined"
                            label="Restaurant Name"
                            onChange={(e) => setReservation((previous) => ({ ...previous, restaurantName: e.target.value }))}
                        />
                        <Button sx={{ marginTop: 2, background: 'black'}} type="submit" variant="contained" color="primary" fullWidth>
                            Make reservation
                        </Button>
                    </form>
                    <Button
                        variant="contained"
                        color="primary"
                        fullWidth
                        sx={{ marginTop: 2, background: 'black'}}
                        onClick={(e) => {
                            e.preventDefault();
                            handleSeeReservations(setReservations);
                        }}
                    >
                        See busy tables here!
                    </Button>
                    <Box sx={{ marginTop: 4 }}>
                        {reservations.map((res, index) => (
                            <Paper key={index} sx={{ padding: 2, marginBottom: 2 }}>
                                <Typography>Reservation Date: {res.reservationDate}</Typography>
                                <Typography>End Time: {res.endTime}</Typography>
                                <Typography>Restaurant Name: {res.restaurantName}</Typography>
                                <Typography>Table ID: {res.tableId}</Typography>
                            </Paper>
                        ))}
                    </Box>
                </Paper>
            </Container>
        </Box>
    );
};

export default Reservation;