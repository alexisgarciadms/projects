//Navigating links within the website
//Title 
const title = document.querySelector('#branding');
const redirectToBathroom = document.getElementById('redirectToBathroom');
const redirectToKitchen = document.getElementById('redirectToKitchen');
const redirectToReflooring = document.getElementById('redirectToReflooring');
const redirectToAdditions = document.getElementById('redirectToAdditions');

title.addEventListener('click', onHeaderClick);
function onHeaderClick(e){
    e.preventDefault();
    window.location.href='index.html';
}

redirectToBathroom.addEventListener('click', redirectBathroom);
function redirectBathroom(e){
    e.preventDefault();
    window.location.href='bathroomremodeling.html';
}

redirectToKitchen.addEventListener('click', redirectKitchen);
function redirectKitchen(e){
    e.preventDefault();
    window.location.href='kitchenremodeling.html';
}

redirectToReflooring.addEventListener('click', redirectReflooring);
function redirectReflooring(e){
    e.preventDefault();
    window.location.href='reflooring.html';
}

redirectToAdditions.addEventListener('click', redirectAdditions);
function redirectAdditions(e){
    e.preventDefault();
    window.location.href='additions.html';
}


//Opening and closing mondals
//Side buttons 
const openButton1 = document.getElementById('side-button1');
const openButton2 = document.getElementById('side-button2');
const openButton3 = document.getElementById('side-button3');
const modal = document.getElementById('modal');
const modal2 = document.getElementById('modal2');
const overlay = document.getElementById('overlay');
const close = document.getElementById('close-button');
const close2 = document.getElementById('close-button2');

openButton1.addEventListener('click', () => {
    modal.classList.add('active');
    overlay.classList.add('active');
});

close.addEventListener('click', () => {
    modal.classList.remove('active');
    overlay.classList.remove('active');
});

openButton2.addEventListener('click', () => {
    modal2.classList.add('active');
    overlay.classList.add('active');
});

close2.addEventListener('click', () => {
    modal2.classList.remove('active');
    overlay.classList.remove('active');
});

openButton3.addEventListener('click', () => {
    modal.classList.add('active');
    overlay.classList.add('active');
});


