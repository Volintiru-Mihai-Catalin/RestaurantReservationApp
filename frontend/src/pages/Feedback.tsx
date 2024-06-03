import React, { useState, ChangeEvent, FormEvent } from "react";
import {
    Container,
    TextField,
    Box,
    Button,
    FormControl,
    InputLabel,
    Select,
    MenuItem,
    FormControlLabel,
    RadioGroup,
    Radio,
    Checkbox,
    Typography,
    SelectChangeEvent,
} from '@mui/material';

const Feedback = () => {
    const [completed, setCompleted] = useState(false);
    const [feedback, setFeedback] = useState('');
    const [rating, setRating] = useState(0);
    const [subscribed, setSubscribed] = useState(false);
    const [category, setCategory] = useState('');

    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        setCompleted(true);
        const response = await fetch("http://localhost:8080/api/v1/feedback", {
            method: "POST",
            body: JSON.stringify({
                feedbackText: feedback,
                favouriteCategory: category,
                subscribed: subscribed,
                rating: rating
            }),
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem("token")}`
            }
        })

        if (!response.ok) {
            console.log("Posting Feedback Failed!");
        }

    };

    return completed?
        (
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
                <Container
                    maxWidth="md"
                    sx={{
                        backgroundColor: 'rgba(255, 255, 255, 0.8)',
                        borderRadius: 2,
                        padding: 4,
                        boxShadow: 3,
                        textAlign: 'center',
                    }}
                >
                    <Typography variant="h2" component="h1" gutterBottom>
                        Thank you for your feedback!
                    </Typography>
                </Container>
            </Box>
        )
        : (
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
            <Box
                component="form"
                sx={{
                    backgroundColor: 'rgba(255, 255, 255, 0.8)',
                    borderRadius: 2,
                    boxShadow: 3,
                    padding: 4,
                    width: '90%',
                    maxWidth: 600,
                }}
                onSubmit={handleSubmit}
            >
                <Typography variant="h5" gutterBottom>
                    Feedback Form
                </Typography>
                <TextField
                    label="Feedback (max 500 characters)"
                    multiline
                    maxRows={10}
                    name="feedback"
                    value={feedback}
                    onChange={(e) => setFeedback(e.target.value)}
                    variant="outlined"
                    fullWidth
                    inputProps={{ maxLength: 500 }}
                    margin="normal"
                />
                <Typography variant="h5" gutterBottom>
                    What did you enjoy the most?
                </Typography>
                <FormControl fullWidth margin="normal">
                    <InputLabel id="category-label">Category</InputLabel>
                    <Select
                        labelId="category-label"
                        name="category"
                        value={category}
                        onChange={(e: SelectChangeEvent<string>) => setCategory(e.target.value)}
                    >
                        <MenuItem value="service">Service</MenuItem>
                        <MenuItem value="food">Food</MenuItem>
                        <MenuItem value="atmosphere">Atmosphere</MenuItem>
                        <MenuItem value="nothing">Nothing</MenuItem>
                    </Select>
                </FormControl>
                <Typography variant="h5" gutterBottom>
                    Give us a rating
                </Typography>
                <FormControl component="fieldset" margin="normal">
                    <RadioGroup
                        name="rating"
                        value={rating.toString()}
                        onChange={(e) => setRating(Number(e.target.value))}
                    >
                        <FormControlLabel value="1" control={<Radio />} label="1" />
                        <FormControlLabel value="2" control={<Radio />} label="2" />
                        <FormControlLabel value="3" control={<Radio />} label="3" />
                        <FormControlLabel value="4" control={<Radio />} label="4" />
                        <FormControlLabel value="5" control={<Radio />} label="5" />
                    </RadioGroup>
                </FormControl>
                <Box sx={{ marginY: 2, display: 'flex', justifyContent: 'center' }}>
                    <FormControlLabel
                        control={
                            <Checkbox
                                name="subscribe"
                                checked={subscribed}
                                onChange={() => setSubscribed(!subscribed)}
                            />
                        }
                        label="Subscribe to our newsletter"
                    />
                </Box>
                <Button
                    sx={{
                        background: 'black',
                        mt: 2
                    }}
                    type="submit"
                    variant="contained"
                    color="primary"
                    fullWidth
                >
                    Send Feedback
                </Button>
            </Box>
        </Box>
    );
};

export default Feedback;
