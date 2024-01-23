const submitFormLogin = () => {
    const email = document.getElementById("inputEmail");
    const password = document.getElementById("inputPassword");
    const url = `/api/auth/login`;
    axios.post(url, {
        email: email.value,
        password: password.value
    }).then(function (response) {
        alert('Đăng nhập thành công');
        window.location.href = "/";
    }).catch(function (error) {
        console.log(error);
    });
}