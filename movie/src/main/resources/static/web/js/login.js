const submitFormLogin = () => {
    const email = document.getElementById("inputEmail");
    const password = document.getElementById("inputPassword");
    const url = `/api/auth/login`;
    axios.post(url, {
        email: email.value,
        password: password.value
    }).then(function (response) {
        alert('Đăng nhập thành công');
        setTimeout(function () {
            window.location.href = "/";
        }, 1000);
    }).catch(function (error) {
        console.log(error);
    });
}