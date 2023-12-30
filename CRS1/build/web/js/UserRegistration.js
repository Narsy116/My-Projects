/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var UserRegistration = function () {
    
    var self = this;

    this.validateUserForm = function () {
        debugger;
        var name = document.getElementById('name').value;
        var email = document.getElementById('email').value;
        var phone = document.getElementById('phone').value;
        var password = document.getElementById('password').value;

        // Check if any field is empty
        if (name === '' || email === '' || phone === '' || password === '') {
            alert('All fields are mandatory and cannot be empty.');
            return false;
        }

        // Check email format
        if (!/^[A-Za-z0-9._%+-]+@(?:gmail|yahoo)\.com$/i.test(email)) {
            alert('Please enter a valid email address ending with @gmail or @yahoo.');
            return false;
        }

        // Check phone number format
        if (!/^\d{10}$/.test(phone)) {
            alert('Please enter a 10-digit phone number.');
            return false;
        }

        // Check password format (minimum 8 characters with alphanumeric and special characters)
        if (!/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/.test(password)) {
            alert('Please enter a valid password (minimum 8 characters, with at least one letter, one number, and one special character).');
            return false;
        }
        
        return true;
    }
}

var userForm = new UserRegistration();