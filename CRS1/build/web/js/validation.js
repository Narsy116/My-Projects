/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Validation functions for Campus Recruitment System

// Validate email format
function isValidEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

// Validate contact number format
function isValidContact(contact) {
    const contactRegex = /^[0-9]{10}$/;
    return contactRegex.test(contact);
}

// Validate password length
function isValidPassword(password) {
    return password.length >= 6;
}

// Form validation for student registration
function validateStudentRegistration() {
    const name = document.getElementById('name').value.trim();
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value;
    const contact = document.getElementById('contact').value.trim();

    let isValid = true;

    if (name === '') {
        isValid = false;
        document.getElementById('nameError').innerText = 'Please enter your name.';
    } else {
        document.getElementById('nameError').innerText = '';
    }

    if (email === '') {
        isValid = false;
        document.getElementById('emailError').innerText = 'Please enter your email.';
    } else if (!isValidEmail(email)) {
        isValid = false;
        document.getElementById('emailError').innerText = 'Please enter a valid email address.';
    } else {
        document.getElementById('emailError').innerText = '';
    }

    if (password === '') {
        isValid = false;
        document.getElementById('passwordError').innerText = 'Please enter a password.';
    } else if (!isValidPassword(password)) {
        isValid = false;
        document.getElementById('passwordError').innerText = 'Password must be at least 6 characters long.';
    } else {
        document.getElementById('passwordError').innerText = '';
    }

    if (contact === '') {
        isValid = false;
        document.getElementById('contactError').innerText = 'Please enter your contact number.';
    } else if (!isValidContact(contact)) {
        isValid = false;
        document.getElementById('contactError').innerText = 'Please enter a valid 10-digit contact number.';
    } else {
        document.getElementById('contactError').innerText = '';
    }

    return isValid;
}

