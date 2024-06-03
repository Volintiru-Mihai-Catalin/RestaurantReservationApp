import React, {useState} from "react";
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import {RegisterInterface} from "../interfaces/RegisterInterface";
import {setEmail, setToken} from "../configuration/configurations";

const handleRegister = async (registerCredentials: RegisterInterface) => {

    const response = await fetch("http://localhost:8080/api/v1/signup", {
        method: "POST",
        body: JSON.stringify({
            firstName: registerCredentials.firstName,
            lastName: registerCredentials.lastName,
            email: registerCredentials.email,
            phoneNumber: registerCredentials.phoneNumber,
            password: registerCredentials.password
        }),
        headers: {"Content-Type": "application/json"}
    });

     if (!response.ok) {
         console.log("REGISTER FAILED!");
         return;
     }
    const data = await response.json();
    const token = data.token;

    if (token) {
        setToken(token);
        setEmail(registerCredentials.email);
        window.location.assign("http://localhost:3000/home");
    }
    return;
};

const RegisterPage = () => {

    const [registerCredentials, setRegisterCredentials] = useState<RegisterInterface>({
        firstName: "",
        lastName: "",
        email: "",
        phoneNumber: "",
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
            <form onSubmit={(e) => {
                e.preventDefault();
                handleRegister(registerCredentials);
            }}>
                <Box sx={{display: 'flex', flexDirection: 'column', gap: 2, maxWidth: 400, margin: '0 auto'}}>
                    <TextField
                        sx={{
                            background: 'white'
                        }}
                        label="First Name"
                        variant="outlined"
                        value={registerCredentials.firstName}
                        onChange={(e) => setRegisterCredentials((previous) => ({
                            ...previous,
                            firstName: e.target.value
                        }))}
                    />
                    <TextField
                        sx={{
                            background: 'white'
                        }}
                        label="Last Name"
                        variant="outlined"
                        value={registerCredentials.lastName}
                        onChange={(e) => setRegisterCredentials((previous) => ({
                            ...previous,
                            lastName: e.target.value
                        }))}
                    />
                    <TextField
                        sx={{
                            background: 'white'
                        }}
                        label="Email"
                        variant="outlined"
                        value={registerCredentials.email}
                        onChange={(e) => setRegisterCredentials((previous) => ({...previous, email: e.target.value}))}
                    />
                    <TextField
                        sx={{
                            background: 'white'
                        }}
                        label="Phone Number"
                        variant="outlined"
                        value={registerCredentials.phoneNumber}
                        onChange={(e) => setRegisterCredentials((previous) => ({
                            ...previous,
                            phoneNumber: e.target.value
                        }))}
                    />
                    <TextField
                        sx={{
                            background: 'white'
                        }}
                        label="Password"
                        type="password"
                        variant="outlined"
                        value={registerCredentials.password}
                        onChange={(e) => setRegisterCredentials((previous) => ({
                            ...previous,
                            password: e.target.value
                        }))}
                    />
                    <Button type="submit" variant="contained" color="primary"
                            sx={{
                                background: 'black'
                            }}>
                        Register
                    </Button>
                </Box>
            </form>
        </Box>
    );
};

export default RegisterPage;