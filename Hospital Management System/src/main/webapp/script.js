// ===== Initialize Chart =====
function initChart() {
    const ctx = document.getElementById('admissionsChart');
    if (!ctx) return;
    
    // Create gradient for chart
    const gradient = ctx.getContext('2d').createLinearGradient(0, 0, 0, 400);
    gradient.addColorStop(0, 'rgba(99, 102, 241, 0.6)');
    gradient.addColorStop(1, 'rgba(99, 102, 241, 0.1)');
    
    // Chart data
    const data = {
        labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'],
        datasets: [{
            label: 'Patient Admissions',
            data: [245, 288, 342, 388, 420, 385],
            backgroundColor: gradient,
            borderColor: 'rgba(99, 102, 241, 1)',
            borderWidth: 2,
            borderRadius: 8,
            borderSkipped: false,
            barPercentage: 0.6,
            categoryPercentage: 0.7,
        }]
    };
    
    // Chart configuration
    const config = {
        type: 'bar',
        data: data,
        options: {
            responsive: true,
            maintainAspectRatio: false,
            animation: {
                duration: 1500,
                easing: 'easeOutQuart'
            },
            plugins: {
                legend: {
                    display: false
                },
                tooltip: {
                    backgroundColor: 'rgba(255, 255, 255, 0.9)',
                    titleColor: '#111827',
                    bodyColor: '#6b7280',
                    borderColor: 'rgba(99, 102, 241, 0.2)',
                    borderWidth: 1,
                    cornerRadius: 8,
                    padding: 12,
                    displayColors: false,
                    callbacks: {
                        label: function(context) {
                            return `Patients: ${context.raw}`;
                        }
                    }
                }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    grid: {
                        color: 'rgba(0, 0, 0, 0.05)',
                        drawBorder: false
                    },
                    ticks: {
                        color: '#6b7280',
                        font: {
                            size: 12
                        },
                        stepSize: 100,
                        callback: function(value) {
                            return value;
                        }
                    }
                },
                x: {
                    grid: {
                        display: false
                    },
                    ticks: {
                        color: '#6b7280',
                        font: {
                            size: 13,
                            weight: '500'
                        }
                    }
                }
            }
        }
    };
    
    // Create chart
    new Chart(ctx, config);
}

// Initialize chart when DOM is loaded
document.addEventListener('DOMContentLoaded', function() {
    initChart();
    
    // Animate stats cards on scroll
    const statCards = document.querySelectorAll('.stat-card');
    const observer = new IntersectionObserver((entries) => {
        entries.forEach((entry, index) => {
            if (entry.isIntersecting) {
                setTimeout(() => {
                    entry.target.classList.add('animated');
                }, index * 200);
            }
        });
    }, { threshold: 0.1 });
    
    statCards.forEach(card => observer.observe(card));
});

// Add this to your existing scroll event listener
window.addEventListener('scroll', () => {
    // ... existing code ...
    
    // Check for stats section visibility
    const statsSection = document.querySelector('.stats-dashboard');
    if (statsSection) {
        const statsTop = statsSection.getBoundingClientRect().top;
        if (statsTop < window.innerHeight - 100) {
            // Add any additional animations for stats section
        }
    }
});/**
 * 
 */