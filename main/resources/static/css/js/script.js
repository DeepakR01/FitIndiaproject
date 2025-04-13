// script.js
const darkModeToggle = document.getElementById('dark-mode-toggle');
const body = document.body;
const navbar = document.querySelector('.navbar');
const navLinks = document.querySelector('.nav-links');
const burger = document.querySelector('.burger');

// Check for saved theme preference in localStorage
const savedTheme = localStorage.getItem('theme');
if (savedTheme === 'dark') {
    enableDarkMode();
}

darkModeToggle.addEventListener('click', () => {
    body.classList.toggle('dark-mode');
    navbar.classList.toggle('dark-mode');
    navLinks.classList.toggle('dark-mode');

    // Save theme preference to localStorage
    if (body.classList.contains('dark-mode')) {
        localStorage.setItem('theme', 'dark');
    } else {
        localStorage.setItem('theme', 'light');
    }
});

function enableDarkMode() {
    body.classList.add('dark-mode');
    navbar.classList.add('dark-mode');
    navLinks.classList.add('dark-mode');
}


burger.addEventListener('click', () => {
    navLinks.classList.toggle('active');
    burger.classList.toggle('toggle');
});

// Close the navigation menu if a link is clicked (only in mobile view)
navLinks.querySelectorAll('a').forEach(link => {
    link.addEventListener('click', () => {
        if (window.innerWidth <= 768) { // Only apply in mobile view
            navLinks.classList.remove('active');
            burger.classList.remove('toggle');
        }
    });
});