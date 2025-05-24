-- SQL structure for if0_38846853_iwdproject.sql
-- This matches the structure needed for the current PHP files (users and employees tables)

CREATE DATABASE IF NOT EXISTS if0_38846853_iwdproject;
USE if0_38846853_iwdproject;

-- Users table for authentication
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Employees table for employee management
CREATE TABLE IF NOT EXISTS employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id VARCHAR(20) NOT NULL,
    employee_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    mobile VARCHAR(20) NOT NULL,
    bdate DATE NOT NULL,
    password VARCHAR(255) NOT NULL
); 