document.addEventListener('DOMContentLoaded', function () {
    const checkbox = document.getElementById('acepto_alma');
    const button = document.getElementById('btn_enviar');

    if (checkbox && button) {
        checkbox.addEventListener('change', function () {
            if (checkbox.checked) {
                button.disabled = false;
                button.classList.add('active');
            } else {
                button.disabled = true;
                button.classList.remove('active');
            }
        });
    }
});
