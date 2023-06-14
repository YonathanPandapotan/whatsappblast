window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

    $(document).ready(function() {
        if($('#templatename').value == 'employee_request_adv_submit'){
            $(".approovername").css("display", "none");
            $(".requestername").css("display", "block");
        }else{
            $(".approovername").css("display", "block");
            $(".requestername").css("display", "none");
        }

      $('#templatename').on('change', function() {
        if(this.value == 'employee_request_adv_submit'){
            $(".approovername").css("display", "none");
            $(".requestername").css("display", "block");
        }else{
            $(".approovername").css("display", "block");
            $(".requestername").css("display", "none");
        }
      });
    });

    (function () {
      'use strict';
      const forms = document.querySelectorAll('.requires-validation');
      Array.from(forms).
      forEach(function (form) {
        form.addEventListener('submit', function (event) {
          if (!form.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
          }

          form.classList.add('was-validated');
        }, false);
      });
    })();



});