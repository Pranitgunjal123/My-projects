// ===== DOM Elements =====
const mobileMenuBtn = document.getElementById('mobileMenuBtn');
const navLinks = document.getElementById('navLinks');
const navbar = document.getElementById('navbar');
const contactForm = document.getElementById('contactForm');
const filterBtns = document.querySelectorAll('.filter-btn');
const projectCards = document.querySelectorAll('.project-card');
const skillBars = document.querySelectorAll('.skill-fill');
const revealElements = document.querySelectorAll('.reveal');
const statNumbers = document.querySelectorAll('.stat-number');
const typedText = document.getElementById('typed-text');
const particlesContainer = document.getElementById('particles');

// ===== Mobile Menu Toggle =====
mobileMenuBtn.addEventListener('click', () => {
    navLinks.classList.toggle('active');
    mobileMenuBtn.innerHTML = navLinks.classList.contains('active') 
        ? '<i class="fas fa-times"></i>' 
        : '<i class="fas fa-bars"></i>';
});

// Close mobile menu when clicking on a link
document.querySelectorAll('.nav-links a').forEach(link => {
    link.addEventListener('click', () => {
        navLinks.classList.remove('active');
        mobileMenuBtn.innerHTML = '<i class="fas fa-bars"></i>';
    });
});

// ===== Sticky Navigation =====
window.addEventListener('scroll', () => {
    if (window.scrollY > 100) {
        navbar.classList.add('scrolled');
    } else {
        navbar.classList.remove('scrolled');
    }
});

// ===== Scroll Reveal Animation =====
const revealOnScroll = () => {
    revealElements.forEach(element => {
        const elementTop = element.getBoundingClientRect().top;
        const elementVisible = 150;
        
        if (elementTop < window.innerHeight - elementVisible) {
            element.classList.add('active');
            
            // Animate skill bars when skills section is visible
            if (element.classList.contains('skills-container')) {
                animateSkillBars();
            }
            
            // Animate stats when about section is visible
            if (element.classList.contains('about-content')) {
                animateStats();
            }
        }
    });
};

window.addEventListener('scroll', revealOnScroll);
// Initial check on page load
revealOnScroll();

// ===== Animate Skill Bars =====
function animateSkillBars() {
    skillBars.forEach(bar => {
        const width = bar.getAttribute('data-width');
        bar.style.width = `${width}%`;
    });
}

// ===== Animate Stats Counter =====
function animateStats() {
    statNumbers.forEach(stat => {
        const target = parseInt(stat.getAttribute('data-count'));
        const duration = 2000; // 2 seconds
        const increment = target / (duration / 16); // 60fps
        let current = 0;
        
        const timer = setInterval(() => {
            current += increment;
            if (current >= target) {
                current = target;
                clearInterval(timer);
            }
            stat.textContent = Math.floor(current);
        }, 16);
    });
}

// ===== Project Filtering =====
filterBtns.forEach(btn => {
    btn.addEventListener('click', () => {
        // Remove active class from all buttons
        filterBtns.forEach(b => b.classList.remove('active'));
        // Add active class to clicked button
        btn.classList.add('active');
        
        const filter = btn.getAttribute('data-filter');
        
        projectCards.forEach(card => {
            if (filter === 'all' || card.getAttribute('data-category') === filter) {
                card.style.display = 'block';
                setTimeout(() => {
                    card.style.opacity = '1';
                    card.style.transform = 'translateY(0)';
                }, 100);
            } else {
                card.style.opacity = '0';
                card.style.transform = 'translateY(20px)';
                setTimeout(() => {
                    card.style.display = 'none';
                }, 300);
            }
        });
    });
});

// ===== Timeline Item Animations =====
function animateTimelineItems() {
    const timelineItems = document.querySelectorAll('.timeline-item');
    
    timelineItems.forEach((item, index) => {
        const itemTop = item.getBoundingClientRect().top;
        const itemVisible = 100;
        
        if (itemTop < window.innerHeight - itemVisible) {
            // Add delay for sequential animation
            setTimeout(() => {
                item.classList.add('active');
                
                // Animate progress bar if exists
                const progressBar = item.querySelector('.progress-bar');
                if (progressBar) {
                    const width = progressBar.getAttribute('data-width');
                    setTimeout(() => {
                        progressBar.style.width = `${width}%`;
                    }, 300);
                }
            }, index * 200); // 200ms delay between each item
        }
    });
}

// Add this to your scroll event listener
window.addEventListener('scroll', () => {
    revealOnScroll();
    animateTimelineItems(); // Add this line
});

// Initial check on page load
document.addEventListener('DOMContentLoaded', () => {
    animateTimelineItems();
});

// ===== Smooth Scrolling for Anchor Links =====
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function(e) {
        e.preventDefault();
        
        const targetId = this.getAttribute('href');
        if (targetId === '#') return;
        
        const targetElement = document.querySelector(targetId);
        if (targetElement) {
            window.scrollTo({
                top: targetElement.offsetTop - 80,
                behavior: 'smooth'
            });
        }
    });
});

// ===== Contact Form Submission =====
contactForm.addEventListener('submit', function(e) {
    e.preventDefault();
    
    // Get form values
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const message = document.getElementById('message').value;
    
    // Simple validation
    if (!name || !email || !message) {
        createToast('Please fill in all fields!', 'error');
        return;
    }
    
    // Email validation
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        createToast('Please enter a valid email address!', 'error');
        return;
    }
    
    // In a real application, you would send this data to a server
    // For demo purposes, we'll simulate a successful submission
    createToast(`Thank you, ${name}! Your message has been sent. I'll get back to you soon.`, 'success');
    
    // Reset form
    contactForm.reset();
});

// ===== Toast Notification Function =====
function createToast(message, type = 'success') {
    const toast = document.createElement('div');
    toast.textContent = message;
    toast.className = 'toast-notification';
    
    // Style based on type
    const bgColor = type === 'success' ? 'var(--primary)' : '#ef4444';
    
    toast.style.cssText = `
        position: fixed;
        bottom: 20px;
        right: 20px;
        background-color: ${bgColor};
        color: white;
        padding: 1rem 1.5rem;
        border-radius: var(--radius);
        box-shadow: var(--shadow-lg);
        z-index: 10000;
        transform: translateY(100px);
        opacity: 0;
        transition: transform 0.3s ease, opacity 0.3s ease;
        max-width: 300px;
        font-weight: 500;
    `;
    
    document.body.appendChild(toast);
    
    // Show toast
    setTimeout(() => {
        toast.style.transform = 'translateY(0)';
        toast.style.opacity = '1';
    }, 10);
    
    // Hide and remove toast after 4 seconds
    setTimeout(() => {
        toast.style.transform = 'translateY(100px)';
        toast.style.opacity = '0';
        
        setTimeout(() => {
            if (toast.parentNode) {
                document.body.removeChild(toast);
            }
        }, 300);
    }, 4000);
}

// ===== Typing Effect for Hero Section =====
function initTypingEffect() {
    const texts = [
        "Pranit Gunjal",
        "Full-Stack Java Developer",
        "UI/UX Designer",
        "Problem Solver"
    ];
    
    let textIndex = 0;
    let charIndex = 0;
    let isDeleting = false;
    let isPaused = false;
    
    function type() {
        const currentText = texts[textIndex];
        
        if (isDeleting) {
            // Deleting text
            typedText.textContent = currentText.substring(0, charIndex - 1);
            charIndex--;
        } else {
            // Typing text
            typedText.textContent = currentText.substring(0, charIndex + 1);
            charIndex++;
        }
        
        // Determine typing speed
        let typeSpeed = 100;
        
        if (isDeleting) {
            typeSpeed /= 2;
        }
        
        // Pause at the end of typing
        if (!isDeleting && charIndex === currentText.length) {
            isPaused = true;
            typeSpeed = 2000; // Pause for 2 seconds
            isDeleting = true;
        } else if (isDeleting && charIndex === 0) {
            isDeleting = false;
            textIndex++;
            if (textIndex >= texts.length) {
                textIndex = 0;
            }
        }
        
        // If we're paused, wait before starting to delete
        if (isPaused) {
            isPaused = false;
        }
        
        setTimeout(type, typeSpeed);
    }
    
    // Start typing effect after a short delay
    setTimeout(type, 1000);
}

// ===== Particle Background Effect =====
function createParticles() {
    for (let i = 0; i < 50; i++) {
        const particle = document.createElement('div');
        particle.style.cssText = `
            position: absolute;
            width: ${Math.random() * 5 + 2}px;
            height: ${Math.random() * 5 + 2}px;
            background: rgba(99, 102, 241, ${Math.random() * 0.3});
            border-radius: 50%;
            top: ${Math.random() * 100}%;
            left: ${Math.random() * 100}%;
            animation: particle-float ${Math.random() * 10 + 10}s infinite ease-in-out;
            animation-delay: ${Math.random() * 5}s;
        `;
        particlesContainer.appendChild(particle);
    }
}

// ===== Form Input Focus Effects =====
const formInputs = document.querySelectorAll('.form-control');
formInputs.forEach(input => {
    const parent = input.parentElement;
    
    input.addEventListener('focus', () => {
        parent.classList.add('focused');
    });
    
    input.addEventListener('blur', () => {
        if (!input.value) {
            parent.classList.remove('focused');
        }
    });
});

// ===== Initialize Everything =====
document.addEventListener('DOMContentLoaded', () => {
    // Trigger initial reveal for elements already in viewport
    revealOnScroll();
    
    // Initialize typing effect
    initTypingEffect();
    
    // Create particle background
    createParticles();
    
    // Add CSS for focused form inputs
    const style = document.createElement('style');
    style.textContent = `
        .form-group.focused label {
            color: var(--primary);
            transform: translateY(-5px);
            font-size: 0.9rem;
        }
        
        @keyframes particle-float {
            0% {
                transform: translateY(0) rotate(0deg);
                opacity: 0;
            }
            10% {
                opacity: 1;
            }
            90% {
                opacity: 1;
            }
            100% {
                transform: translateY(-100px) rotate(360deg);
                opacity: 0;
            }
        }
    `;
    document.head.appendChild(style);
});

// ===== Add active class to nav links based on scroll position =====
window.addEventListener('scroll', () => {
    const sections = document.querySelectorAll('section');
    const navLinks = document.querySelectorAll('.nav-links a');
    
    let current = '';
    
    sections.forEach(section => {
        const sectionTop = section.offsetTop;
        const sectionHeight = section.clientHeight;
        
        if (scrollY >= sectionTop - 100) {
            current = section.getAttribute('id');
        }
    });
    
    navLinks.forEach(link => {
        link.classList.remove('active');
        if (link.getAttribute('href') === `#${current}`) {
            link.classList.add('active');
        }
    });
});