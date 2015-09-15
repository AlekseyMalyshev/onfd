/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function highlightError(id) {
    id.focus();
    id.style.borderColor = "red";
    id.onchange = function() {
        id.style.borderColor = "initial";
    };
}

function validateUser(form) {
    if (form.email.value === "") {
        alert("E-mail name is empty.");
        highlightError(form.email);
    }
    else {
        // TODO: validate password complexity here
        form.submit();
    }
}

function validateDetails(form) {
    if (form.login.value === "") {
        alert("Login name is empty.");
        highlightError(form.login);
    }
    else if (form.password.value === "") {
        alert("Password cannot be empty.");
        highlightError(form.pass);
    }
    else if (form.password.value !== form.pass_.value) {
        alert("Password does not match.");
        highlightError(form.pass);
    }
    else if (form.email.value === "") {
        alert("E-mail name is empty.");
        highlightError(form.email);
    }
    else {
        // TODO: validate password complexity here
        form.submit();
    }
}

function updateManufName(form) {
    var o = form.id.options;
    form.name.value = o[o.selectedIndex].text;
}

function checkLogin(login) {
    
}

function formatNumber(phone) {
    phone = phone.replace(/[^0-9]/g, '');
    if (phone.length === 11) {
        // International format
        return phone.replace(/(\d{1})(\d{3})(\d{3})(\d{4})/, "+$1 ($2) $3 $4");
    }
    else if (phone.length === 10 ){
        // National format
        return phone.replace(/(\d{3})(\d{3})(\d{4})/, "($1) $2 $3");
    }
    else {
        return phone;
    }
}

function changeSize(id, size) {
	window.location.replace("/vendor/product/size/" + id + "/" + size);
}

function changeFit(product, fit) {
	alert(product + "/" + fit);
}

