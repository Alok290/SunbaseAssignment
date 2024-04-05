document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('login-form');
    const signupForm = document.getElementById('signup-form');
    const customerListTableBody = document.getElementById('customer-list-table-body');
    const syncButton = document.getElementById('sync-button');
    const addCustomerForm = document.getElementById('add-customer-form');

    loginForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        // Call API to login
        fetch('http://localhost:8080/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username,
                password
            })
        })
        .then(response => response.json())
        .then(data => {
            // Handle login response
            console.log(data);
           alert("succesfully logged");
        })
        .catch(error => {
            // Handle error
            console.error(error);
        });
    });

    signupForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const firstName = document.getElementById('first-name').value;
        const lastName = document.getElementById('last-name').value;
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        // Call API to signup
        fetch('http://localhost:8080/auth/sign-up', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                firstName,
                lastName,
                username,
                password
            })
        })
        .then(response => response.json())
        .then(data => {
            // Handle signup response
            console.log(data);
            alert("succesfully add user");
        })
        .catch(error => {
            // Handle error
            console.error(error);
        });
    });

    syncButton.addEventListener('click', () => {
        // Call API to sync customer list
        fetch('http://localhost:8080/customer/sync', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            }
        })
        .then(response => response.json())
        .then(data => {
            // Handle sync response
            console.log(data);
        })
        .catch(error => {
            // Handle error
            console.error(error);
        });
    });

    addCustomerForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const firstName = document.getElementById('first-name').value;
        const lastName = document.getElementById('last-name').value;
        const street = document.getElementById('street').value;
        const address = document.getElementById('address').value;
        const city = document.getElementById('city').value;
        const state = document.getElementById('state').value;
        const email = document.getElementById('email').value;
        const phone = document.getElementById('phone').value;
        // Call API to add customer
        const form = document.getElementById('add-customer-form');
        form.addEventListener('submit', (e) => {
          e.preventDefault();
          const formData = new FormData(form);
          fetch('http://localhost:8080/customer/create', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams(formData)
          })
         .then(response => response.json())
         .then(data => {
            console.log(data);
          })
         .catch(error => {
            console.error(error);
          });
        });
});
});