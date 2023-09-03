import React from 'react';
import { render, screen, fireEvent , waitFor} from "@testing-library/react"
import Auth from "../Auth"

import server from "../../mock/server"


jest.setTimeout(7000)
// Establish API mocking before all tests.
beforeAll(() => server.listen())

// Reset any request handlers that we may add during the tests,
// so they don't affect other tests.
afterEach(() => server.resetHandlers())

// Clean up after the tests are finished.
afterAll(() => server.close())



test('should login success',  async () => {
    const view = render(<Auth option="login"/>);
    
    const emailInput = screen.getByPlaceholderText('Username')
    const passwordInput = screen.getByPlaceholderText('Password')
    const submitBtn = screen.getByRole('button', {name: 'LOGIN'})
    expect(submitBtn).toBeInTheDocument();


    fireEvent.change(emailInput, {target: {value: 'test1@gmail.com'}})
    window.sessionStorage.setItem('username', 'test1@gmail.com')
    fireEvent.change(passwordInput, {target: {value: '1234567'}})
    fireEvent.click(submitBtn)
    
    await waitFor(() => {
        const response = screen.getByText('login succeed');
        expect(response).toBeInTheDocument();
    });
    
})

test('should login failed', async () => { 
    const view = render(<Auth option="login"/>);
    
    const emailInput = screen.getByPlaceholderText('Username')
    const passwordInput = screen.getByPlaceholderText('Password')
    const submitBtn = screen.getByRole('button', {name: 'LOGIN'})
    expect(submitBtn).toBeInTheDocument();


    fireEvent.change(emailInput, {target: {value: 'test1@gmail.com'}})
    window.sessionStorage.removeItem('username')
    fireEvent.change(passwordInput, {target: {value: '1234567'}})
    fireEvent.click(submitBtn)
    
    await waitFor(() => {
        const response = screen.getByText('Not authenticated');
        expect(response).toBeInTheDocument();
    });
})


test('should registration succeed', async ()=> {
    const view = render(<Auth option="signup"/>);
    
    const emailInput = screen.getByPlaceholderText('Email')
    const passwordInput = screen.getByPlaceholderText('Password')
    const rePasswordInput = screen.getByPlaceholderText('Retype password')
    const firstName = screen.getByPlaceholderText('First Name')
    const lastName = screen.getByPlaceholderText('Last Name')

    const submitBtn = screen.getByRole('button', {name: 'SIGN UP'})
    expect(emailInput).toBeInTheDocument();
    expect(passwordInput).toBeInTheDocument();
    expect(rePasswordInput).toBeInTheDocument();
    expect(firstName).toBeInTheDocument();
    expect(lastName).toBeInTheDocument();


    fireEvent.change(emailInput, {target: {value: 'test1@gmail.com'}})
    fireEvent.change(passwordInput, {target: {value: 'dasdfasdf'}})
    fireEvent.change(rePasswordInput, {target: {value: 'dasdfasdf'}})
    fireEvent.change(firstName, {target: {value: 'vbb'}})
    fireEvent.change(lastName, {target: {value: 'asdf'}})

    window.sessionStorage.setItem('username', 'test1@gmail.com')

    fireEvent.click(submitBtn)
    
    await waitFor(() => {
        const response = screen.getByText('signup succeed. An activation Email has been send.');
        expect(response).toBeInTheDocument();
    });
})

test('should registration failed', async ()=> {
    const view = render(<Auth option="signup"/>);
    
    const emailInput = screen.getByPlaceholderText('Email')
    const passwordInput = screen.getByPlaceholderText('Password')
    const rePasswordInput = screen.getByPlaceholderText('Retype password')
    const firstName = screen.getByPlaceholderText('First Name')
    const lastName = screen.getByPlaceholderText('Last Name')

    const submitBtn = screen.getByRole('button', {name: 'SIGN UP'})
    expect(emailInput).toBeInTheDocument();
    expect(passwordInput).toBeInTheDocument();
    expect(rePasswordInput).toBeInTheDocument();
    expect(firstName).toBeInTheDocument();
    expect(lastName).toBeInTheDocument();


    fireEvent.change(emailInput, {target: {value: 'test1@gmail.com'}})
    fireEvent.change(passwordInput, {target: {value: 'dasdfasdf'}})
    fireEvent.change(rePasswordInput, {target: {value: 'dasdfasdf'}})
    fireEvent.change(firstName, {target: {value: 'vbb'}})
    fireEvent.change(lastName, {target: {value: 'asdf'}})

    window.sessionStorage.removeItem('username')

    fireEvent.click(submitBtn)
    
    await waitFor(() => {
        const response = screen.getByText('Network error');
        expect(response).toBeInTheDocument();
    });
})