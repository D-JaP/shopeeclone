import React from 'react';
import { render, screen, fireEvent } from "@testing-library/react"
import Auth from "./pages/Auth"

test('should login success', () => {
    const container = render(<Auth option="login"/>);
    
    const emailInput = screen.getByPlaceholderText('Username')
    const passwordInput = screen.getByPlaceholderText('Password')
    const submitBtn = screen.getByRole('button', {name: /sign up|login/i})
    expect(submitBtn).toBeInTheDocument();


    fireEvent.change(emailInput, {target: {value: 'test1@gmail.com'}})
    fireEvent.change(passwordInput, {target: {value: '1234567'}})
    fireEvent.click(submitBtn)
    
 })