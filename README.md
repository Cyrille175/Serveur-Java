# Serveur-Java

Serveur-Java is a lightweight HTTP server written in Java.  
The goal of this project is to understand how a web server works internally, without relying on frameworks like Spring Boot.

It handles HTTP requests using raw sockets and provides a simple but structured backend architecture with routing, JSON responses and static file serving.

---

## Overview

This project implements a minimal web server capable of:

- Accepting multiple client connections (thread pool)
- Parsing HTTP requests (method, path, headers, body)
- Routing requests to handlers
- Returning HTML, JSON or text responses
- Serving static files (HTML, CSS, JS)
- Handling query parameters
- Displaying results through a simple web interface

The application also includes a basic frontend interface to interact with the server through buttons instead of raw URLs.

---

## Features

- Multithreaded server using `ExecutorService`
- Manual HTTP request parsing
- Routing system (similar to lightweight frameworks)
- JSON responses without external libraries
- Static file serving from a public directory
- Custom 404 and error responses
- Interactive frontend interface (HTML + JS)
- Clean project structure using Maven

---

## Available Endpoints

| Endpoint | Description |
|----------|------------|
| `/` | Main interface |
| `/health` | Returns server status (JSON) |
| `/time` | Returns server time (JSON) |
| `/echo?message=...` | Echoes a message |
| `/hello` | Simple HTML response |
| `/index.html` | Static HTML page |
| `/style.css` | Static CSS |
| `/app.js` | Frontend logic |

---

## Project Structure