// setupTests.js
import { setupServer } from 'msw/node';
import { loginHandler } from './LoginHandler'; // Import your request handlers

// Create an MSW server and start it before your tests run
const server = setupServer(...loginHandler);

export default server