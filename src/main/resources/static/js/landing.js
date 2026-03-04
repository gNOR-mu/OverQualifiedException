document.addEventListener('DOMContentLoaded', function () {

    // Initialize Bootstrap tooltips
    const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
    const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))

    // --- 2. FOMO Pop-ups via Bootstrap Toasts ---
    const fomoContainer = document.getElementById('fomo-popups-container');

    if (fomoContainer) {
        const fomoMessages = [
            { icon: '🚀', text: '¡Un reclutador IT está mirando tu perfil... y acaba de cerrar la pestaña!' },
            { icon: '💸', text: '¡Gabriel en otra dimensión acaba de comprar 5 años de experiencia en Spring Boot!' },
            { icon: '⏳', text: '¡Solo queda 1 vacante para el puesto de Junior con sueldo de practicante! ¡Cómprala antes de que te ganen!' },
            { icon: '🔥', text: '¡Alguien acaba de aceptar mantener código legacy en Cobol por visibilidad!' },
            { icon: '🤡', text: '¡Tu ex-compañero acaba de ser promovido por robarse tu idea!' }
        ];

        function createFomoToast() {
            const randomMsg = fomoMessages[Math.floor(Math.random() * fomoMessages.length)];

            const toastEl = document.createElement('div');
            // Bootstrap Toast classes (Vaporware Dark Mode)
            toastEl.className = 'toast align-items-center border-0 glass-panel border-metric shadow-lg mb-3';
            toastEl.setAttribute('role', 'alert');
            toastEl.setAttribute('aria-live', 'assertive');
            toastEl.setAttribute('aria-atomic', 'true');

            toastEl.innerHTML = `
                <div class="d-flex bg-transparent">
                    <div class="toast-body d-flex align-items-center w-100 text-white">
                        <span class="fs-3 me-3">${randomMsg.icon}</span>
                        <div class="flex-grow-1">
                            <div class="fw-medium text-white" style="font-size: 0.9rem; line-height: 1.4;">${randomMsg.text}</div>
                            <small class="text-metric mt-1 d-block">Hace unos segundos</small>
                        </div>
                    </div>
                    <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
            `;

            fomoContainer.appendChild(toastEl);

            const toast = new bootstrap.Toast(toastEl, { delay: 5000 + Math.random() * 3000 });
            toast.show();

            toastEl.addEventListener('hidden.bs.toast', () => {
                toastEl.remove();
            });

            // Limit to 2 toasts at a time
            const activeToasts = fomoContainer.querySelectorAll('.toast');
            if (activeToasts.length > 2) {
                const oldestToast = bootstrap.Toast.getInstance(activeToasts[0]);
                if (oldestToast) oldestToast.hide();
            }

            scheduleNextFomo();
        }

        function scheduleNextFomo() {
            const nextFomoTimeout = Math.floor(Math.random() * 12000) + 8000;
            setTimeout(createFomoToast, nextFomoTimeout);
        }

        setTimeout(createFomoToast, 3000);
    }
});
