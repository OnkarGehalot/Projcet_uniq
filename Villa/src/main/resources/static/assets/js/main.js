(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner();
    
    
    // Initiate the wowjs
    new WOW().init();
    
    
    // Dropdown on mouse hover
    const $dropdown = $(".dropdown");
    const $dropdownToggle = $(".dropdown-toggle");
    const $dropdownMenu = $(".dropdown-menu");
    const showClass = "show";
    
    $(window).on("load resize", function() {
        if (this.matchMedia("(min-width: 992px)").matches) {
            $dropdown.hover(
            function() {
                const $this = $(this);
                $this.addClass(showClass);
                $this.find($dropdownToggle).attr("aria-expanded", "true");
                $this.find($dropdownMenu).addClass(showClass);
            },
            function() {
                const $this = $(this);
                $this.removeClass(showClass);
                $this.find($dropdownToggle).attr("aria-expanded", "false");
                $this.find($dropdownMenu).removeClass(showClass);
            }
            );
        } else {
            $dropdown.off("mouseenter mouseleave");
        }
    });
    
    
    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });


    // Facts counter
    $('[data-toggle="counter-up"]').counterUp({
        delay: 10,
        time: 2000
    });


    // Modal Video
    $(document).ready(function () {
        var $videoSrc;
        $('.btn-play').click(function () {
            $videoSrc = $(this).data("src");
        });
        console.log($videoSrc);

        $('#videoModal').on('shown.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc + "?autoplay=1&amp;modestbranding=1&amp;showinfo=0");
        })

        $('#videoModal').on('hide.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc);
        })
    });

    //--------------------------------------------------------//
    $(function () {
        // Initialize the Check-in datetimepicker
        $('#date1').datetimepicker({
            format: 'L', // Date format (MM/DD/YYYY or locale-specific)
            minDate: moment().startOf('day'), // Block past dates, allowing only future dates
            icons: {
                time: 'far fa-clock', 
                date: 'far fa-calendar',
                up: 'fas fa-arrow-up',
                down: 'fas fa-arrow-down',
                previous: 'fas fa-chevron-left',
                next: 'fas fa-chevron-right',
                today: 'far fa-calendar-check',
                clear: 'far fa-trash-alt',
                close: 'fas fa-times'
            }
        });
    
        // Initialize the Check-out datetimepicker
        $('#date2').datetimepicker({
            format: 'L', // Date format (MM/DD/YYYY or locale-specific)
            useCurrent: false, // Do not auto-set current date
            minDate: moment().startOf('day'), // Block past dates, allowing only future dates
            icons: {
                time: 'far fa-clock', 
                date: 'far fa-calendar',
                up: 'fas fa-arrow-up',
                down: 'fas fa-arrow-down',
                previous: 'fas fa-chevron-left',
                next: 'fas fa-chevron-right',
                today: 'far fa-calendar-check',
                clear: 'far fa-trash-alt',
                close: 'fas fa-times'
            }
        });
    
        // Update Check-out (date2) minDate based on Check-in (date1) selection
        $('#date1').on("change.datetimepicker", function (e) {
            // Set minDate of Check-out to the selected Check-in date
            $('#date2').datetimepicker('minDate', e.date);
        });
    });
    


    // ----------------villa bookn.html form js----------

    document.getElementById('checkin2').addEventListener('change', function () {
        var checkinDate = document.getElementById('checkin2').value;
        document.getElementById('checkout2').setAttribute('min', checkinDate);
    });
    
    // ----------------villa bookn.html form js----------


    document.querySelector('.form-control').addEventListener('input', function() {
        if (this.value < 0) {
            this.value = 0; // Set to 0 if a negative number is entered
        }
    });
//------------------------Phone no---------------------------------------//
document.getElementById('PhoneNumber').addEventListener('input', function (e) {
                                    this.value = this.value.replace(/[^0-9]/g, ''); // Replace non-numeric characters
                                });

//-----------------------------email-contact-us--------------------------------------------//


    // Testimonials carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1000,
        margin: 25,
        dots: false,
        loop: true,
        nav : true,
        navText : [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsive: {
            0:{
                items:1
            },
            768:{
                items:2
            }
        }
    });
    
})(jQuery);
                
//--------------------whatsappppp---------------
function sendwhatsapp(){
    var phonenumber = "+917709110116";

    var url = "https://wa.me/" + phonenumber + "?text="
    +"Get more Information about the Villa's !";

    window.open(url, '_blank').focus();
  }
