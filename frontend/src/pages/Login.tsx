import React, {useState} from "react";
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import { LoginInterface } from "../interfaces/LoginInterface";
import {setEmail, setToken} from "../configuration/configurations";

const handleLogin = async (credentials: LoginInterface) => {
    const response = await fetch("http://localhost:8080/api/v1/signin", {
        method: "POST",
        body: JSON.stringify({
            email: credentials.email,
            password: credentials.password
        }),
        headers: {"Content-Type": "application/json"}
    });

    if (!response.ok) {
        console.log("LOGIN FAILED");
        return;
    }

    const data = await response.json();
    const token = data.token;

    if (token) {
        setToken(token);
        setEmail(credentials.email);
        window.location.assign("http://localhost:3000/home");
    }

    return;
}

const Login = () => {
    const [credentials, setCredentials] = useState<LoginInterface>({
        email: "",
        password: ""
    });

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
            <form
                onSubmit={(e) => {
                e.preventDefault();
                handleLogin(credentials);
            }}>
                <Box
                    sx={{
                        display: 'flex',
                        flexDirection: 'column',
                        gap: 2,
                        maxWidth: 300,
                        margin: '0 auto'
                    }}>
                    <TextField
                        sx={{
                            background: 'white'
                        }}
                        label="Email"
                        variant="outlined"
                        value={credentials.email}
                        onChange={(e) => setCredentials((previous) => ({...previous, email: e.target.value}))}
                    />
                    <TextField
                        sx={{
                            background: 'white'
                        }}
                        label="Password"
                        type="password"
                        variant="outlined"
                        value={credentials.password}
                        onChange={(e) => setCredentials((previous) => ({...previous, password: e.target.value}))}
                    />
                    <Button type="submit" variant="contained" color="primary"
                            sx={{
                                backgroundColor: 'black',
                            }}
                    >
                        Login
                    </Button>
                </Box>
            </form>
        </Box>
    );
};

export default Login;